package fr.islandswars.api.log.internal;

import fr.islandswars.api.log.Log;
import fr.islandswars.api.utils.Preconditions;
import java.util.logging.Level;

/**
 * File <b>ServerLog</b> located on fr.islandswars.api.log.internal
 * ServerLog is a part of Islands Wars - Api.
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
 * Created the 10/03/2018 at 16:33
 * @since 0.2.9
 * <p>
 * Represent a server status log
 */
public class ServerLog extends Log {

	private Server server;

	public ServerLog(Level level, String msg) {
		super(level, msg);
	}

	@Override
	public void checkValue() {
		Preconditions.checkNotNull(server);
	}

	public ServerLog setServer(Server server) {
		this.server = server;
		return this;
	}
}
