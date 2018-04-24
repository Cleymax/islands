package fr.islandswars.core.bukkit.storage;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemManager;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.net.packet.play.server.OpenWindowPacket;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageBuilder;
import fr.islandswars.api.storage.StorageManager;
import fr.islandswars.api.utils.Preconditions;
import fr.islandswars.core.bukkit.storage.item.TranslatableItem;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;

/**
 * File <b>StorageFactory</b> located on fr.islandswars.core.bukkit.storage
 * StorageFactory is a part of Islands Wars - Api.
 * <p>
 * Copyright (c) 2017 - 2018 Islands Wars.
 * <p>
 * Islands Wars - Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <a href="http://www.gnu.org/licenses/">GNU GPL license</a>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 24/04/2018 at 16:48
 * @since 0.2.9
 */
public class StorageFactory implements ItemManager, StorageManager {

	@Override
	public Item createItem(ItemType type) {
		Preconditions.checkNotNull(type);

		return new TranslatableItem(type);
	}

	@Override
	public ItemStack wrappToNMSItemStack(Item item) {
		Preconditions.checkNotNull(item);

		TranslatableItem translatableItem = (TranslatableItem) item;
		//translatableItem.getCompound().getCompound("tag").setInt("id", internalItem.getId());
		return new ItemStack(translatableItem.getCompound());

	}

	@Override
	public void openStorage(IslandsPlayer target, Storage storage) {
		Preconditions.checkNotNull(target);
		Preconditions.checkNotNull(storage);

		openInventory(target, storage, ((StorageWrapper) storage).name);

	}

	@Override
	public void openStorage(IslandsPlayer target, Storage storage, Object... parameters) {
		Preconditions.checkNotNull(target);
		Preconditions.checkNotNull(storage);

		openInventory(target, storage, target.getPlayerLocale().format(((StorageWrapper) storage).name, parameters));
	}

	@Override
	public Storage newStorage(StorageBuilder builder) {
		Preconditions.checkNotNull(builder);

		String name = builder.getName();
		int    size = builder.getSize();

		return new StorageWrapper(name, size);
		//TODO builder
	}


	private void openInventory(IslandsPlayer target, Storage storage, String title) {
		StorageWrapper       storageWrapper   = (StorageWrapper) storage;
		BukkitStorageWrapper inventoryWrapper = new BukkitStorageWrapper(storageWrapper);
		EntityPlayer         entityPlayer     = target.getCraftPlayer().getHandle();
		Container            container        = CraftEventFactory.callInventoryOpenEvent(entityPlayer, new ContainerChest(entityPlayer.inventory, inventoryWrapper, entityPlayer));

		if (container == null)
			return;

		if (entityPlayer.activeContainer != entityPlayer.defaultContainer)
			entityPlayer.closeInventory();

		int                     containerCounter = entityPlayer.nextContainerCounter();
		PacketPlayOutOpenWindow packet           = new PacketPlayOutOpenWindow(containerCounter, "minecraft:container", IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}"), inventoryWrapper.getSize());

		new OpenWindowPacket(packet).sendToPlayer(target.getCraftPlayer());

		inventoryWrapper.detect();
		entityPlayer.activeContainer = container;
		entityPlayer.activeContainer.windowId = containerCounter;
		entityPlayer.activeContainer.addSlotListener(entityPlayer);
	}

}
