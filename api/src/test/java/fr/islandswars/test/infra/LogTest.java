package fr.islandswars.test.infra;

import fr.islandswars.api.log.InfraLogger;
import fr.islandswars.api.log.Log;
import fr.islandswars.api.log.internal.DefaultLog;
import fr.islandswars.api.utils.NMSReflectionUtil;
import java.util.logging.Level;
import org.junit.Test;

/**
 * File <b>LogTest</b> located on fr.islandswars.test.infra
 * LogTest is a part of Islands Wars - Api.
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
 * Created the 09/03/2018 at 23:06
 */
public class LogTest {

	private final InfraLogger logger = new InfraLogger() {
		//Override since NMSReflectionUtil use Bukkit import that aren't initialized yet
		@Override
		public <T extends Log> T createCustomLog(Class<T> clazz, Level level, String message) {
			try {
				return clazz.getDeclaredConstructor(Level.class, String.class).newInstance(level, message);
			} catch (ReflectiveOperationException e) {
				throw new NMSReflectionUtil.ReflectionException(e);
			}
		}

		@Override
		public void log(Log object) {
		}
	};

	@Test(expected = NMSReflectionUtil.ReflectionException.class)
	public void testCustomLogInstanciation() {
		ErrorLog log = logger.createCustomLog(ErrorLog.class, Level.INFO, "should not work");
	}

	@Test(expected = NullPointerException.class)
	public void testLevelNotNull() {
		DefaultLog defaultLog = new DefaultLog(null, "msg");
		defaultLog.checkValue();
	}

	@Test(expected = NullPointerException.class)
	public void testMessageNotNull() {
		DefaultLog defaultLog = new DefaultLog(Level.INFO, null);
		defaultLog.checkValue();
	}

	public final class ErrorLog extends Log {

		public ErrorLog(Level level, String msg, String error) {
			super(level, msg);
		}

		@Override
		public void checkValue() {

		}
	}

}
