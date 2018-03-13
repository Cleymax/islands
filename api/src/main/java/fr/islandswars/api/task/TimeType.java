package fr.islandswars.api.task;

/**
 * File <b>TimeType</b> located on fr.islandswars.api.task
 * TimeType is a part of Islands Wars - Api.
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
 * Created the 02/01/2018 at 17:21
 * @since 0.2.4
 * <p>
 * Represent a predefined time, use {@link Updater#delta()} for custom value
 */
public enum TimeType {

	TICK(1),
	SECOND(20),
	TWO_SECONDS(SECOND.timeInTick * 2),
	THIRTY_SECONDS(SECOND.timeInTick * 30),
	MINUT(SECOND.timeInTick * 60),
	HOUR(MINUT.timeInTick * 60),
	NONE(-1);

	int timeInTick;

	TimeType(int timeInTick) {
		this.timeInTick = timeInTick;
	}

	/**
	 * @return the time (in tick)
	 */
	public int getTimeInTick() {
		return timeInTick;
	}
}
