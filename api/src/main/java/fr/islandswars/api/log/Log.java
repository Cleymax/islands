package fr.islandswars.api.log;

import com.google.gson.annotations.SerializedName;
import fr.islandswars.api.IslandsApi;

/**
 * File <b>Log</b> located on fr.islandswars.api.log
 * Log is a part of Islands Wars - Api.
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
 * Created the 10/03/2018 at 16:15
 * @since 0.2.9
 * <p>
 * Represent a loggable object with the two minimum information
 */
public abstract class Log {

	protected final String level;
	@SerializedName("message")
	protected final String msg;

	protected Log(String level, String msg) {
		this.level = level;
		this.msg = msg;
	}

	/**
	 * perform {@link fr.islandswars.api.utils.Preconditions#checkNotNull(Object)} on custom log
	 */
	public abstract void checkValue();

	/**
	 * @see InfraLogger#log(Log)
	 */
	public void log() {
		checkValue();

		IslandsApi.getInstance().getInfraLogger().log(this);
	}
}
