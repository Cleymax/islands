package fr.islandswars.api.i18n;

import javax.annotation.concurrent.ThreadSafe;

/**
 * File <b>Translatable</b> located on fr.islandswars.api.i18n
 * Translatable is a part of Islands Wars - Api.
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
 * Created the 09/12/2017 at 15:37
 * @see String#format(String, Object...)
 * @since 0.1.1
 * <p>
 * Format the given key with paramaters
 */
@ThreadSafe
public interface Translatable {

	/**
	 * Get a String according to the default language (French) and format with the given parameters
	 *
	 * @param key        the property key
	 * @param parameters the properties label to format with
	 * @return a {@link String#format(String, Object...)}, or else the key
	 * @see #format(Locale, String, Object...)
	 */
	String format(String key, Object... parameters);

	/**
	 * Get a String according to the given language and format with the given parameters
	 *
	 * @param locale     a language to use
	 * @param key        the property key
	 * @param parameters the properties label to format with
	 * @return a {@link String#format(String, Object...)}, or else the key
	 */
	String format(Locale locale, String key, Object... parameters);
}
