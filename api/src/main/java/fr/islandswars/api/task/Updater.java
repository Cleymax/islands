package fr.islandswars.api.task;

import java.lang.annotation.*;

/**
 * File <b>Updater</b> located on fr.islandswars.api.task
 * Updater is a part of Islands Wars - Api.
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
 * Created the 02/01/2018 at 17:15
 * @since 0.2.4
 * <p>
 * Show that this method needs to be scheduled, one or multiples time, in async or not, with delayed or not
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Updater {

	/**
	 * Set a time before run this task (in ticks where 1 seconds equals 20 ticks)
	 *
	 * @return the time in tick before run this task
	 */
	int delayed() default -1;

	/**
	 * Use this parameters when using a task that needs to be repeated
	 * and {@link TimeType} doesn't give you exact update time
	 *
	 * @return the time between each call
	 * @see #time()
	 */
	int delta() default -1;

	/**
	 * Set an unit  according to the {@link #delta()}
	 *
	 * @return the time type used
	 */
	TimeType time() default TimeType.NONE;

	/**
	 * All bukkit interactions need to be run inside the main thread, declared with the sync tag
	 *
	 * @return this task tag
	 */
	TaskType type();
}
