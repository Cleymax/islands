package fr.islandswars.api.net;

import org.bukkit.entity.Player;

/**
 * File <b>ProtocolManager</b> located on fr.islandswars.api.net
 * ProtocolManager is a part of IslandsApi Wars - Api.
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
 * @author based on Gogume1er design and idea
 * Created the 30/09/2017 at 23:09
 * @since 0.1
 * <p>
 * Util class to register handler when packet is emitting or receiving
 */
public interface ProtocolManager {

	/**
	 * Send this packet's wrapper to a Player, basically call :
	 * <code>
	 * ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	 * </code>
	 *
	 * @param target the player to send data
	 * @param packet a valid Minecraft {@link fr.islandswars.api.net.PacketType.Bound#OUT} packet
	 * @param <T>    a minecraft packet wrapper
	 */
	<T extends GamePacket> void sendPacket(Player target, T packet);

	/**
	 * Register the givan packet handler
	 *
	 * @param handler a custom handler for a {@link PacketType}
	 */
	void subscribeHandler(PacketHandler<? extends GamePacket> handler);

	/**
	 * Unregister the given handler, the {@link PacketHandler#handlePacket(PacketEvent)} won't be longer notified
	 *
	 * @param handler the handler to remove
	 */
	void unsubscribeHandler(PacketHandler<? extends GamePacket> handler);
}
