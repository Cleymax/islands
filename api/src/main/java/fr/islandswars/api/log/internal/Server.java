package fr.islandswars.api.log.internal;

import com.google.gson.annotations.SerializedName;
import fr.islandswars.api.server.ServerType;

/**
 * File <b>Server</b> located on fr.islandswars.api.log.internal
 * Server is a part of Islands Wars - Api.
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
 * Created the 13/03/2018 at 16:28
 * @since 0.2.9
 * <p>
 * Server status (start, stop, ...) log
 */
public class Server {

	@SerializedName("server-type")
	private String serverType;
	private String status;

	public Server(Status status, ServerType type) {
		this.status = status.toString();
		this.serverType = type.getTypeName();
	}
}
