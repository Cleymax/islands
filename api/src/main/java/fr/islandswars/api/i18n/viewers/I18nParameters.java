package fr.islandswars.api.i18n.viewers;

import java.util.function.Supplier;

/**
 * File <b>I18nParameters</b> located on fr.islandswars.api.i18n.viewers
 * I18nParameters is a part of Islands Wars - Api.
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
 * Created the 12/01/2018 at 17:19
 * @since 0.2.8.2
 * <p>
 * Allow to supply i18n object use for {@link String#format(String, Object...)} before display
 */
public interface I18nParameters<T> {

	/**
	 * Register this parameters with the generic key
	 *
	 * @param key            a generic key use to access this resource
	 * @param i18nParameters an array of Object use for formatting
	 */
	void supplyI18nParameters(T key, Supplier<Object[]> i18nParameters);

}
