package fr.islandswars.api.log;

/**
 * File <b>InfraLogger</b> located on fr.islandswars.api.log
 * InfraLogger is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 14:35
 * @since 0.2.7
 * <p>
 * Output debug (if env PROD=false) and other logs in file for storage usage ;)
 */
public interface InfraLogger {

	/**
	 * Send this message to bukkit's {@link java.util.logging.Logger} only if
	 * PROD environments variables is set to false with a DEBUG level set to 850
	 *
	 * @param message an object to log (call {@link Object#toString()} by default)
	 */
	void debug(Object message);

	/**
	 * Send this message to bukkit's {@link java.util.logging.Logger} only if
	 * PROD environments variables is set to false
	 *
	 * @param message an object to log (call {@link Object#toString()} by default)
	 */
	void info(Object message);

}
