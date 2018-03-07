package fr.islandswars.api.net;

/**
 * File <b>PacketHandler</b> located on fr.islandswars.api.net
 * PacketHandler is a part of IslandsApi Wars - Api.
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
 * @author based on Gogume1er design and idea
 * Created the 30/09/2017 at 23:25
 * @since 0.1
 * <p>
 * Represent a packet handler
 */
public abstract class PacketHandler<T extends GamePacket> {

	private final PacketType<T> packetType;

	public PacketHandler(PacketType<T> packetType) {
		this.packetType = packetType;
	}

	/**
	 * Get the type of the handled packet
	 *
	 * @return the packet type handle by the packet handler
	 */
	public final PacketType<T> getPacketType() {
		return packetType;
	}

	/**
	 * Called when a packet with the specified packet type is received
	 *
	 * @param event an event to consume when receiving or emitting this packet type
	 */
	public abstract void handlePacket(PacketEvent<T> event);

}
