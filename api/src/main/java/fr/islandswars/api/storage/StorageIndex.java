package fr.islandswars.api.storage;

import fr.islandswars.api.item.Item;
import java.util.Optional;

/**
 * File <b>StorageIndex</b> located on fr.islandswars.api.storage
 * StorageIndex is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:47
 * @since 0.2.9
 */
public interface StorageIndex {

	/**
	 * Get the item associated to this index
	 *
	 * @return an item if present, can be null
	 */
	Optional<Item> getItem();

	/**
	 * Store the given item to this index position
	 *
	 * @param item an item to stock
	 */
	void setItem(Item item);

}

