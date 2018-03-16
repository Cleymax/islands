package fr.islandswars.api.i18n;

import org.bukkit.plugin.Plugin;

import javax.annotation.concurrent.ThreadSafe;

/**
 * File <b>I18nLoader</b> located on fr.islandswars.api.i18n
 * I18nLoader is a part of Islands Wars - Api.
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
 * Created the 09/12/2017 at 15:39
 * @since 0.1.1
 * <p>
 * Load key:label to the associated {@link Locale}
 */
@ThreadSafe
public interface I18nLoader {

	/**
	 * It will load file located on resources/lang/ folder, each file must contains {@link Locale#i18nName}
	 * identifier to detect the locale to register to
	 *
	 * @param plugin the plugin to load, it will load only file according to the conventions
	 */
	void registerCustomProperties(Plugin plugin);

	/**
	 * Dynamically register a key:label inside the locale property map if not exist
	 *
	 * @param locale the language to fulfill with
	 * @param key    a unique key
	 * @param value  a label following the {@link String#format(String, Object...)} conventions
	 */
	void registerDynamicProperty(Locale locale, String key, String value);

}
