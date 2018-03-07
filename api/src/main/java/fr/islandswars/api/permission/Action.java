package fr.islandswars.api.permission;

/**
 * File <b>Action</b> located on fr.islandswars.api.permission
 * Action is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 11:56
 * @since 0.2.6
 * <p>
 * Represent action that player can perform or not on the given server
 * TODO maybe links an array of {@link Action} to {@link fr.islandswars.api.server.ServerType}
 */
public enum Action {

	/**
	 * Can the player pos / break / drop block ?
	 */
	BUILD(),
	/**
	 * Can the given player send message in chat ?
	 */
	SEND_MESSAGE(),
	/**
	 * Is this player affected by slow mode chat ?
	 */
	SLOW_MODE()
}
