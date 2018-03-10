package fr.islandswars.api.log;

import fr.islandswars.api.log.internal.DefaultLog;
import fr.islandswars.api.utils.NMSReflectionUtil;
import java.util.logging.Level;

/**
 * File <b>InfraLogger</b> located on fr.islandswars.api.log
 * InfraLogger is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 14:35
 * @since 0.2.7
 * <p>
 * Output json log to graylog through GELF UDP docker protocol
 */
public interface InfraLogger {

	/**
	 * Instanciate and retrieve a custom log, or else throw Exception if reflection failed
	 *
	 * @param clazz   a custom class to instanciate
	 * @param level   a log level
	 * @param message a log message
	 * @param <T>     log type
	 * @return a custom {@link Log} implementation
	 * @see fr.islandswars.api.utils.NMSReflectionUtil#getConstructorAccessor(String, Class[])
	 */
	default <T extends Log> T createCustomLog(Class<T> clazz, Level level, String message) {
		T customLog = NMSReflectionUtil.getConstructorAccessor(clazz, Level.class, String.class).newInstance(level, message);
		return customLog;
	}

	/**
	 * Create a default log with INFO level and a custom message
	 *
	 * @param message a message to log
	 * @see #createDefaultLog(Level, String)
	 */
	default void createDefaultLog(String message) {
		createDefaultLog(Level.INFO, message);
	}

	/**
	 * Helper method to create and directly log a simple log
	 *
	 * @param level a log level
	 * @param msg   a log message
	 */
	default void createDefaultLog(Level level, String msg) {
		new DefaultLog(level, msg).log();
	}

	/**
	 * Send this message to default docker sysout
	 *
	 * @param object an object to log, it will be serialize to json and sent to graylog through GELF
	 */
	void log(Log object);

}
