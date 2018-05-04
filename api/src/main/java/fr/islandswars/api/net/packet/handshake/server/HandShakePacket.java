package fr.islandswars.api.net.packet.handshake.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.EnumProtocol;
import net.minecraft.server.v1_12_R1.PacketHandshakingInSetProtocol;

/**
 * File <b>HandShakePacket</b> located on fr.islandswars.api.net.packet.handshake.server
 * HandShakePacket is a part of Islands Wars - Api.
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
 * Created the 22/10/17 at 15:36
 * @since 0.1
 */
public class HandShakePacket extends GamePacket<PacketHandshakingInSetProtocol> {

	protected HandShakePacket(PacketHandshakingInSetProtocol handle) {
		super(handle);
	}

	/**
	 * @return the next enum protocol state
	 */
	public EnumProtocol getEnumProtocol() {
		return (EnumProtocol) getHandleValue("d");
	}

	/**
	 * @return this server's host
	 */
	public String getHost() {
		return (String) getHandleValue("hostname");
	}

	/**
	 * @return this server's port
	 */
	public int getPort() {
		return (int) getHandleValue("port");
	}

	/**
	 * @return this current server implementation protocol version
	 */
	public int getProtocolVersion() {
		return (int) getHandleValue("a");
	}

	@Override
	public PacketType getType() {
		return PacketType.Handshake.Server.HANDSHAKE;
	}

	/**
	 * @param newHost a new host to send
	 */
	public void setHost(String newHost) {
		setHandleValue("hostname", newHost);
	}

	/**
	 * @param newPort a new port to send
	 */
	public void setPort(int newPort) {
		setHandleValue("port", newPort);
	}

	/**
	 * @param newProtocolversion a new version to send
	 * @deprecated the client will see this server outdated
	 */
	public void setProtocolVersion(int newProtocolversion) {
		setHandleValue("a", newProtocolversion);
	}
}
