package fr.islandswars.api.utils;

/**
 * File <b>Disposable</b> located on fr.islandswars.api.utils
 * Disposable is a part of Islands Wars - Api.
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
 * Created the 03/01/2018 at 15:18
 * @since 0.2.4.2
 * <p>
 * Give a way to safely delete or GC this resource
 */
@FunctionalInterface
public interface Disposable<T> {

	/**
	 * Dispose the given resource
	 *
	 * @param resource an object to safely delete
	 */
	void dispose(T resource);

}