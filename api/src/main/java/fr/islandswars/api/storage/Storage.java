package fr.islandswars.api.storage;

import fr.islandswars.api.item.Item;
import java.util.Optional;

/**
 * File <b>Storage</b> located on fr.islandswars.api.storage
 * Storage is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:46
 * @since 0.2.9
 */
public interface Storage {

	/**
	 * Add an item to the next unused index
	 *
	 * @param item the item to set
	 */
	void addItem(Item item);

	/**
	 * Get a column according to minecraft storing system
	 *
	 * @param index an integer representing a slot in an storage
	 * @return a column position
	 */
	default int getColumn(int index) {
		return index - getRow(index) * 9 + 1;
	}

	/**
	 * Get the index associated to this item
	 *
	 * @param key an item
	 * @return this item's index, -1 if not found
	 */
	default int getItemIndex(Item key) {
		for (int i = 0; i < getSize(); i++) {
			Optional<Item> item = getSlots()[i].getItem();
			if (item.isPresent() && item.get().equals(key))
				return i;
		}
		return -1;
	}

	/**
	 * Get a row according to minecraft storing system
	 *
	 * @param index an integer representing a slot in an storage
	 * @return a row position
	 */
	default int getRow(int index) {
		return index / 9;
	}

	/**
	 * @return this storage size, must be a multiple of 9
	 */
	int getSize();

	/**
	 * @return all {@link StorageIndex} in this storage
	 */
	StorageIndex[] getSlots();

	/**
	 * Remove item (if exists) at this slot
	 *
	 * @param row    the index row, start to 0 from limit
	 * @param column the index column, must be in [1;9]
	 */
	void removeItem(int row, int column);

	/**
	 * Remove item (if exists) at this slot
	 *
	 * @param index the storage index, start to 0 from limit
	 */
	default void removeItem(int index) {
		removeItem(getRow(index), getColumn(index));
	}

	/**
	 * Set an item to the storage at the given index
	 *
	 * @param row    the index row, start to 0 from limit
	 * @param column the index column, must be in [1;9]
	 * @param item   the item to set in this slot
	 */
	void setItem(int row, int column, Item item);

	/**
	 * Set an item to the storage at the given index
	 *
	 * @param index the storage, start to 0 from limit
	 * @param item  the item to set in this index
	 */
	default void setItem(int index, Item item) {
		setItem(getRow(index), getColumn(index), item);
	}
}

