package fr.islandswars.api.item;

import fr.islandswars.api.utils.Preconditions;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;

/**
 * File <b>ItemType</b> located on fr.islandswars.api.item
 * ItemType is a part of Islands Wars - Api.
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
 * Created the 26/04/2018 at 13:29
 * @since 0.2.9
 */
public class ItemType {

	private final NBTTagCompound tag;

	private ItemType(NBTTagCompound compound) {
		this.tag = compound;
	}

	/**
	 * Build an item
	 *
	 * @param material item material
	 */
	public ItemType(Material material) {
		Preconditions.checkNotNull(material);

		tag = new NBTTagCompound();
		writeMaterial(material);
		writeAmount(1);
	}

	/**
	 * Build an item
	 *
	 * @param material item material
	 * @param data     material data (such as color)
	 */
	public ItemType(Material material, byte data) {
		Preconditions.checkNotNull(material);
		Preconditions.checkState(data, ref -> ref > 0);

		tag = new NBTTagCompound();
		writeMaterial(material);
		writeData(data);
		writeAmount(1);
	}

	/**
	 * Build an item
	 *
	 * @param material item material
	 * @param amount   the stack size [1;128[
	 * @param data     material data (such as color)
	 */
	public ItemType(Material material, int amount, short data) {
		Preconditions.checkNotNull(material);
		Preconditions.checkState(data, ref -> ref > 0);
		Preconditions.checkState(amount, ref -> ref >= 1 && ref <= 128);

		tag = new NBTTagCompound();
		writeMaterial(material);
		if (amount > 0 && amount < 128)
			writeAmount(amount);
		else
			writeAmount(1);
		writeData(data);
	}

	@Override
	public ItemType clone() throws CloneNotSupportedException {
		return new ItemType((NBTTagCompound) tag.clone());
	}

	/**
	 * Get this item's stack size stored in a {@link NBTTagCompound tag}
	 *
	 * @return item count (stack size)
	 */
	public int getAmount() {
		return tag.getByte("Count");
	}

	/**
	 * @return a compound representing this item
	 */
	public NBTTagCompound getCompound() {
		return tag;
	}

	/**
	 * Get this item's data stored in a {@link NBTTagCompound tag}
	 *
	 * @return item data (or durability)
	 */
	public short getData() {
		return tag.getShort("Damage");
	}

	/**
	 * Get this item's material stored in a {@link NBTTagCompound tag}
	 *
	 * @return a bukkit Material
	 */
	public Material getMaterial() {
		return Material.getMaterial(Integer.valueOf(tag.getString("id")));
	}

	/**
	 * Set the given amount
	 *
	 * @param amount this item stack size
	 * @return this builder item
	 */
	public ItemType writeAmount(int amount) {
		Preconditions.checkState(amount, ref -> ref >= 1 && ref <= 128);

		tag.setByte("Count", (byte) amount);
		return this;
	}

	/**
	 * Set the given data
	 *
	 * @param durability this item data
	 * @return this builder item
	 */
	public ItemType writeData(short durability) {
		Preconditions.checkState(durability, ref -> ref > 0);

		tag.setShort("Damage", durability);
		return this;
	}

	/**
	 * Set the given material
	 *
	 * @param material this item material
	 * @return this builder item
	 */
	public ItemType writeMaterial(Material material) {
		Preconditions.checkNotNull(material);

		tag.setString("id", String.valueOf(material.getId()));
		return this;
	}
}
