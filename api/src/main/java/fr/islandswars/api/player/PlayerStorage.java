package fr.islandswars.api.player;

import fr.islandswars.api.utils.Preconditions;
import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File <b>PlayerStorage</b> located on fr.islandswars.api.player
 * PlayerStorage is a part of Islands Wars - Api.
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 04/01/2018 at 23:03
 * @since 0.2.4.5
 * <p>
 * TODO support crafting inventory if needed
 * This class represents a better way to deal with the player inventory,
 * cause they suck at mojang..
 */
public interface PlayerStorage {

	/**
	 * @return the player main hand item, or optional empty if not selected
	 */
	Optional<Item> getItemInMainHand();

	/**
	 * Set this item in the current player selected slots
	 *
	 * @param item an item to set
	 */
	default void setItemInMainHand(Item item) {
		Preconditions.checkNotNull(item);

		setItem(0, getSelectedColumn(), item);
	}

	/**
	 * @return the player off hand item, or optional empty if not selected
	 */
	Optional<Item> getItemInOffHand();

	/**
	 * Set this item in player off hand
	 *
	 * @param item an item to set
	 */
	void setItemInOffHand(Item item);

	/**
	 * @return the player helmet if set
	 */
	Optional<Item> getHelmet();

	/**
	 * Set this player armor's helmet
	 *
	 * @param item an item to set
	 */
	void setHelmet(Item item);

	/**
	 * @return the player chest plate if set
	 */
	Optional<Item> getChestplate();

	/**
	 * Set this player armor's chest plate
	 *
	 * @param item an item to set
	 */
	void setChestplate(Item item);

	/**
	 * @return the player leggings if set
	 */
	Optional<Item> getLeggings();

	/**
	 * Set this player armor's leggings
	 *
	 * @param item an item to set
	 */
	void setLeggings(Item item);

	/**
	 * @return the player boots if set
	 */
	Optional<Item> getBoots();

	/**
	 * Set this player armor's boots
	 *
	 * @param item an item to set
	 */
	void setBoots(Item item);

	/**
	 * @return this player armor contents
	 */
	default List<Item> getPlayerArmor() {
		return Stream.of(getHelmet(), getChestplate(), getLeggings(), getBoots()).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
	}

	/**
	 * Set an item to the given index, basically call {@link net.minecraft.server.v1_12_R1.PlayerInventory#setItem(int, ItemStack)}
	 *
	 * @param index an index to set this item to
	 * @param item  an item to set
	 */
	void setItem(int index, Item item);

	/**
	 * @return the selected hotbar's column index between [1;9]
	 */
	int getSelectedColumn();

	default int getSize() {
		return InventoryType.PLAYER.getDefaultSize();
	}

	/**
	 * Util method to set item with grid values instead of index. You can just set
	 * item in player storage and hotbar
	 *
	 * @param row    a row belong to [0;3]
	 * @param column a column belong to [1;9]
	 * @param item   an item to set
	 */
	default void setItem(int row, int column, Item item) {
		setItem(row * 9 + column - 1, item);
	}

	public interface Item {

	}
}
