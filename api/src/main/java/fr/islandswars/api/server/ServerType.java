package fr.islandswars.api.server;

/**
 * File <b>ServerType</b> located on fr.islandswars.api.server
 * ServerType is a part of Islands Wars - Api.
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
 * Created the 26/12/2017 at 17:46
 * @since 0.2.4
 * <p>
 * All different servers configurations
 */
public enum ServerType {

	HUB(64, "hub-%d");

	private final int    maxPlayer;
	private final String name;

	ServerType(int maxPlayer, String name) {
		this.maxPlayer = maxPlayer;
		this.name = name;
	}

	/**
	 * @return the maximum player allowed on this server
	 */
	public int getMaxPlayer() {
		return maxPlayer;
	}

	/**
	 * @return the server type name
	 */
	public String getTypeName() {
		return name;
	}
}
