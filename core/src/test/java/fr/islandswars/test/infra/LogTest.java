package fr.islandswars.test.infra;

import com.google.gson.Gson;
import fr.islandswars.api.log.Log;
import fr.islandswars.api.log.internal.Action;
import fr.islandswars.api.log.internal.DefaultLog;
import fr.islandswars.api.log.internal.PlayerLog;
import fr.islandswars.core.log.InternalLogger;
import java.util.UUID;
import java.util.logging.Level;
import org.junit.Assert;
import org.junit.Before;
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
 * along with this program. If not, see <a href="http://www.gnu.org/licenses/">GNU GPL license</a>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 13/03/2018 at 13:14
 */
public class LogTest {

	private Gson           gson;
	private InternalLogger logger;

	@Before
	public void init() {
		this.logger = new InternalLogger();
		this.gson = new Gson();
	}

	@Test
	public void testIsJson() {
		Assert.assertEquals("{\"level\":\"INFO\",\"message\":\"test\"}", debug(new DefaultLog(Level.INFO, "test")));
	}

	@Test
	public void testPlayerLog() {
		Assert.assertEquals("{\"action\":{\"name\":\"Xharos\",\"action\":\"CONNECT\",\"uuid\":\"d74d02da-a825-47fa-9adc-8058abec4b80\"},\"level\":\"INFO\",\"message\":\"test\"}",
							debug(new PlayerLog(Level.INFO, "test").setPlayer("Xharos", UUID.fromString("d74d02da-a825-47fa-9adc-8058abec4b80"), Action.CONNECT)));
	}

	private String debug(Log object) {
		return gson.toJson(object);
	}
}
