package fr.islandswars.api.net.packet.status.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketStatusOutPong;

/**
 * File <b>PongPacket</b> located on fr.islandswars.api.net.packet.status.server
 * PongPacket is a part of Islands Wars - Api.
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
 * Created the 30/09/2017 at 23:22
 * @since 0.1
 */
public class PongPacket extends GamePacket<PacketStatusOutPong> {

	protected PongPacket(PacketStatusOutPong handle) {
		super(handle);
	}

	public long getPayloadResponse() {
		return (long) getHandleValue("a");
	}

	@Override
	public PacketType getType() {
		return PacketType.Status.Server.PONG;
	}
}
