package fr.islandswars.api.item;

import net.minecraft.server.v1_12_R1.NBTBase;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

/**
 * File <b>ItemProperties</b> located on fr.islandswars.api.item
 * ItemProperties is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 14:37
 * @since 0.2.9
 * <p>
 * Provide Item properties through {@link net.minecraft.server.v1_12_R1.NBTTagCompound}
 */
public interface ItemProperties {

	/**
	 * Add an enchantment to this item
	 *
	 * @param enchantment an enchantment to set
	 * @param level       this enchantment level
	 * @return this item builder properties
	 */
	ItemProperties addEnchantment(Enchantment enchantment, int level);

	/**
	 * Add an item flag to this item
	 *
	 * @param flags some flags to set
	 * @return this item builder properties
	 */
	ItemProperties addItemFlags(ItemFlag... flags);

	/**
	 * Add an tag to this item
	 *
	 * @param key    this tag key
	 * @param nbtTag a tag to set
	 * @return this builder properties
	 */
	ItemProperties addNBTTag(String key, NBTBase nbtTag);

	/**
	 * Duplicate tjhe internal {@link net.minecraft.server.v1_12_R1.NBTTagCompound} used for storage
	 *
	 * @return a new ItemProperties based on this ItemType and "tag" properties
	 */
	ItemProperties clone();

	/**
	 * @return item amount
	 */
	int getAmount();

	/**
	 * @return this item durability
	 */
	short getDurability();

	/**
	 * @return this item wrapped material
	 */
	Material getMaterial();

	/**
	 * Update this item amount
	 *
	 * @param amount a new amount to set according to maxStackSize
	 * @return this item builder properties
	 */
	ItemProperties setAmount(int amount);

	/**
	 * Update this item durability
	 *
	 * @param durability new durability to set
	 * @return thjis item builder properties
	 */
	ItemProperties setDurability(short durability);

	/**
	 * Set glowing effect
	 *
	 * @param value active or not
	 * @return this builder properties
	 */
	ItemProperties setGlowing(boolean value);

	/**
	 * Update this item material
	 *
	 * @param material new material to set
	 * @return this item builder properties
	 */
	ItemProperties setMaterial(Material material);

	/**
	 * Set infinit durability
	 *
	 * @param value active or not
	 * @return this builder properties
	 */
	ItemProperties setUnbreakable(boolean value);

	/**
	 * Will only work on {@link Material#SKULL_ITEM} with data equals {@link org.bukkit.SkullType#PLAYER}
	 * It will use only display viewer's head
	 *
	 * @return this builder properties
	 */
	ItemProperties usePlayerHead();
}
