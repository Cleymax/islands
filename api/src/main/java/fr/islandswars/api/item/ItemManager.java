package fr.islandswars.api.item;

import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;

/**
 * File <b>ItemManager</b> located on fr.islandswars.api.item
 * ItemManager is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:37
 * @since 0.2.9
 */
public interface ItemManager {

	/**
	 * Build a new Item from a given builder type
	 *
	 * @param type a builder type thanks to bukkit
	 * @return a new Item
	 */
	Item createItem(ItemType type);

	/**
	 * Wrapp the given Item to a bukkit ItemStack
	 *
	 * @param item an islands wars item to wrapp
	 * @return a valid bukkit itemstack
	 */
	default org.bukkit.inventory.ItemStack wrappToBukkitItemStack(Item item) {
		return CraftItemStack.asCraftMirror(wrappToNMSItemStack(item));
	}

	/**
	 * Wrapp the given Item to a minecraft server ItemStack
	 *
	 * @param item an islands wars item to wrapp
	 * @return a minecraft server itemstack
	 */
	ItemStack wrappToNMSItemStack(Item item);

}
