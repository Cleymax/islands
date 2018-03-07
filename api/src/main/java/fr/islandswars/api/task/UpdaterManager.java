package fr.islandswars.api.task;

import org.bukkit.scheduler.BukkitTask;

import javax.annotation.concurrent.ThreadSafe;

/**
 * File <b>UpdaterManager</b> located on fr.islandswars.api.task
 * UpdaterManager is a part of Islands Wars - Api.
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
 * Created the 02/01/2018 at 17:10
 * @since 0.2.4
 * <p>
 * Task manager to register and stop task
 */
@ThreadSafe
public interface UpdaterManager {

	/**
	 * Will look inside the class method to register every method which has
	 * a {@link Updater} annotation (and no method's parameters)
	 * The bukkit task {@link BukkitTask#getTaskId() id} will be sync from the hash of class + method name
	 *
	 * @param updatable a class to register
	 */
	void register(Object updatable);

	/**
	 * Will stop all task associated to this object, according to hash
	 *
	 * @param updatable a class to stop
	 */
	void stop(Object updatable);

}
