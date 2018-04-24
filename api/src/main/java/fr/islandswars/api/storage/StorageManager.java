package fr.islandswars.api.storage;

import fr.islandswars.api.player.IslandsPlayer;

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
 * Created the 24/04/2018 at 16:45
 * @since 0.2.9
 */
public interface StorageManager {

	/**
	 * Open the given storage to this player
	 *
	 * @param target  a player to open this storage, this action will close any active storage
	 * @param storage a storage to open
	 */
	void openStorage(IslandsPlayer target, Storage storage);

	/**
	 * Open the given storage to this player with active translation
	 *
	 * @param target     a player to open this storage, this action will close any active storage
	 * @param storage    a storage to open
	 * @param parameters some parameters to format the title
	 */
	void openStorage(IslandsPlayer target, Storage storage, Object... parameters);

	/**
	 * Get a new storage according to the given options supplied in the builder
	 *
	 * @param builder some options
	 * @return a new storage
	 */
	Storage newStorage(StorageBuilder builder);

}
