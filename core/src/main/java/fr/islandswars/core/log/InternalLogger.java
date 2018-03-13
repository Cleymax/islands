package fr.islandswars.core.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.islandswars.api.log.InfraLogger;
import fr.islandswars.api.log.Log;
import fr.islandswars.api.log.internal.ErrorLog;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;

/**
 * File <b>InternalLogger</b> located on fr.islandswars.core.log
 * InternalLogger is a part of Islands Wars - Api.
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
 * Created the 10/03/2018 at 17:12
 * @since 0.2.9
 */
public class InternalLogger implements InfraLogger {

	private final Gson gson;

	public InternalLogger() {
		this.gson = new GsonBuilder().create();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
			createCustomLog(ErrorLog.class, Level.SEVERE, throwable.getMessage()).supplyStacktrace(throwable).log();
		});
	}

	@Override
	public void log(Log object) {
		System.out.println(gson.toJson(object));
	}

}
