package fr.islandswars.test.bukkit;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.player.ChatType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageBuilder;
import fr.islandswars.api.storage.StorageManager;
import fr.islandswars.api.storage.StorageType;
import java.util.Optional;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;

/**
 * File <b>StorageTest</b> located on fr.islandswars.test.bukkit
 * StorageTest is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 18:48
 */
public class StorageTest {

	private final StorageManager manager;
	private final Item           delimiter;
	private final Storage        storage;

	public StorageTest() {
		this.manager = IslandsApi.getInstance().getStorageManager();
		delimiter = manager.newItem(new ItemType(Material.STAINED_GLASS_PANE, (byte) 10)).withProperties(props -> props.setGlowing(true));

		StorageBuilder builder = StorageBuilder.build("api.storage.test.name", 6 * 9, StorageType.GLOBAL).withPattern(
				"CCCCCCCCC" +
						"C       C" +
						"C       C" +
						"C       C" +
						"C       C" +
						"CCCCCCCCC").supplyPattern('C', delimiter);

		this.storage = manager.createStorage(builder);
		storage.addItem(manager.newItem(new ItemType(Material.SKULL_ITEM, (byte) SkullType.PLAYER.ordinal())).
				globalLoreParameters(() -> new Object[]{"test"}).
				globalNameParameters(() -> new Object[]{"1", 2}).
				withInternalisation("api.storage.test.head.name", "api.storage.test.head.lore").
				withProperties(props -> {
					props.usePlayerHead();
					props.setUnbreakable(true);
					props.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					props.addEnchantment(Enchantment.FROST_WALKER, 1);
				}).
				onClick((player, event) -> {
					event.setCancelled(true);
					player.sendTranslatedMessage(ChatType.CHAT, "api.chat.test", player.getCraftPlayer().getName());
				}), null);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Optional<IslandsPlayer> player = IslandsApi.getInstance().getPlayer(event.getPlayer().getUniqueId());
		player.ifPresent(p -> p.openStorage(storage));
	}

}
