package fr.islandswars.api.log.internal;

import fr.islandswars.api.log.Log;

/**
 * File <b>DefaultLog</b> located on fr.islandswars.api.log.internal
 * DefaultLog is a part of Islands Wars - Api.
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
 * Created the 10/03/2018 at 16:16
 * @since 0.2.9
 * <p>
 * Default implementation for {@link fr.islandswars.api.log.Log}
 */
public class DefaultLog extends Log {

	public DefaultLog(String level, String msg) {
		super(level, msg);
	}
}