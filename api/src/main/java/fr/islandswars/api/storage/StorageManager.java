package fr.islandswars.api.storage;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemType;
import java.util.Optional;
import org.bukkit.inventory.ItemStack;

/**
 * File <b>StorageManager</b> located on fr.islandswars.api.storage
 * StorageManager is a part of Islands Wars - Api.
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
 * Created the 26/04/2018 at 13:45
 * @since 0.2.9
 */
public interface StorageManager {

	Storage createStorage(StorageBuilder builder);

	Optional<Item> getItem(ItemStack bukkitItem);

	Item newItem(ItemType type);

}