package fr.islandswars.core.bukkit.storage;

import fr.islandswars.api.storage.StorageIndex;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;

/**
 * File <b>BukkitStorageWrapper</b> located on fr.islandswars.core.bukkit.storage
 * BukkitStorageWrapper is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 17:03
 * @since 0.2.9
 */
public class BukkitStorageWrapper implements IInventory {

	private final List<HumanEntity>      viewers;
	private final StorageWrapper         storage;
	private final NonNullList<ItemStack> items;

	BukkitStorageWrapper(StorageWrapper storage) {
		this.viewers = new ArrayList<>();
		this.storage = storage;
		this.items = NonNullList.a(getSize(), ItemStack.a);
	}

	@Override
	public String getName() {
		return storage.name;
	}

	@Override
	public boolean hasCustomName() {
		return getName() != null;
	}

	@Override
	public IChatBaseComponent getScoreboardDisplayName() {
		return CraftChatMessage.fromString(getName())[0];
	}

	@Override
	public int getSize() {
		return storage.getSize();
	}

	@Override
	public boolean x_() {
		for (int i = 0; i < storage.getSize() - 1; i++) {
			StorageIndex storageIndex = storage.getSlots()[i];
			if (storageIndex != null)
				if (storageIndex.getItem() != null)
					return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int i) {
		//detect();
		return items.get(i);
	}

	@Override
	public ItemStack splitStack(int i, int j) {
		ItemStack stack = this.getItem(i);
		if (stack == ItemStack.a) {
			return stack;
		} else {
			ItemStack result;
			if (stack.getCount() <= j) {
				this.setItem(i, ItemStack.a);
				result = stack;
			} else {
				result = CraftItemStack.copyNMSStack(stack, j);
				stack.subtract(j);
			}

			this.update();
			return result;
		}
	}

	@Override
	public ItemStack splitWithoutUpdate(int i) {
		ItemStack stack = this.getItem(i);
		if (stack == ItemStack.a) {
			return stack;
		} else {
			ItemStack result;
			if (stack.getCount() <= 1) {
				this.setItem(i, null);
				result = stack;
			} else {
				result = CraftItemStack.copyNMSStack(stack, 1);
				stack.subtract(1);
			}

			return result;
		}
	}

	@Override
	public void setItem(int i, ItemStack itemStack) {
		this.items.set(i, itemStack);
		if (itemStack != ItemStack.a && this.getMaxStackSize() > 0 && itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public int getMaxStackSize() {
		return 0; //TODO What is the maximum allowed? :/
	}

	@Override
	public void update() {
		//Unused see InventoryWrapper#update();
	}

	@Override
	public boolean a(EntityHuman entityHuman) {
		return true; //see InventoryWrapper#a(EntityHuman);
	}

	@Override
	public void startOpen(EntityHuman entityHuman) {
		//Unused see InventoryWrapper#openContainer(EntityHuman);
	}

	@Override
	public void closeContainer(EntityHuman entityHuman) {
		//Unused see InventoryWrapper#closeContainer(EntityHuman);
	}

	@Override
	public boolean b(int i, ItemStack itemStack) {
		return true; //see InventoryWrapper#b(int, ItemStack);
	}

	@Override
	public int getProperty(int i) {
		return 0; //see InventoryWrapper#getProperty(int);
	}

	@Override
	public void setProperty(int i, int i1) {
		//Unused see InventoryWrapper#setProperty(int, int);
	}

	@Override
	public int h() {
		return 0; //see InventoryWrapper#h();
	}

	@Override
	public void clear() {
		storage.clear();
	}

	@Override
	public List<ItemStack> getContents() {
		return items;
	}

	@Override
	public void onOpen(CraftHumanEntity viewer) {
		this.viewers.add(viewer);
	}

	@Override
	public void onClose(CraftHumanEntity viewer) {
		this.viewers.remove(viewer);
	}

	@Override
	public List<HumanEntity> getViewers() {
		return viewers;
	}

	@Override
	public InventoryHolder getOwner() {
		return null; //see InventoryWrapper#getOwner();
	}

	@Override
	public void setMaxStackSize(int i) {
		//unused, always set to max
	}

	@Override
	public Location getLocation() {
		return null;
	}

	void detect() {
		//TODO optimise
		StorageIndex[] indexs = storage.getSlots();
		for (int i = 0; i < indexs.length; i++) {
			StorageIndex index = indexs[i];
			if (index != null && index.getItem().isPresent())
				setItem(i, index.getItem().get().asNMSItemStack());
		}
	}
}
