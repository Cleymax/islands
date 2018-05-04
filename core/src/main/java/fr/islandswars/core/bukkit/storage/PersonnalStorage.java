package fr.islandswars.core.bukkit.storage;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.player.IslandsPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;

/**
 * File <b>PersonnalStorage</b> located on fr.islandswars.core.bukkit.storage
 * PersonnalStorage is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 17:35
 * @since 0.2.9
 */
public class PersonnalStorage extends AbstractStorage {

	private final Map<UUID, CraftInventoryCustom> inventories;

	PersonnalStorage(int size, String titleKey) {
		super(size, titleKey);
		this.inventories = new HashMap<>();
	}

	@Override
	public Inventory getHandle(IslandsPlayer player) {
		return inventories.computeIfAbsent(player.getPlayerID(), (key) -> (CraftInventoryCustom) Bukkit.createInventory(null, 1, ""));
	}

	@Override
	public int getNextFreeSlot(IslandsPlayer player) {
		Inventory inventory = getHandle(player);
		for (int i = 0; i < getSize(); i++) {
			if (inventory.getItem(i) == null)
				return i;
		}
		return -1;
	}

	@Override
	public void remove(int slot, IslandsPlayer player) {
		getHandle(player).setItem(slot, null);
	}

	@Override
	public void set(int slot, Item item, IslandsPlayer player) {
		if (slot != -1)
			((CraftInventoryCustom) getHandle(player)).getInventory().setItem(slot, item.toNMSItem());
	}
}
