package fr.islandswars.api.i18n;

import fr.islandswars.api.IslandsApi;

import javax.annotation.concurrent.ThreadSafe;

/**
 * File <b>Locale</b> located on fr.islandswars.api.i18n
 * Locale is a part of Islands Wars - Api.
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
 * Created the 09/12/2017 at 15:27
 * @since 0.1.1
 * <p>
 * Player selected language
 */
@ThreadSafe
public enum Locale {

	FRENCH("fr-FR"),
	ENGLISH("en-GB");

	private final String i18nName;

	Locale(String i18nName) {
		this.i18nName = i18nName;
	}

	/**
	 * @return this lang id
	 */
	public String getI18nName() {
		return i18nName;
	}

	/**
	 * Get a String according to this language and format with the given parameters
	 *
	 * @param key        the property key
	 * @param parameters the properties value to format with
	 * @return a {@link String#format(String, Object...)}, or else the key
	 * @see Translatable#format(Locale, String, Object...)
	 */
	public String format(String key, Object... parameters) {
		return IslandsApi.getInstance().getTranslatable().format(this, key, parameters);
	}
}
