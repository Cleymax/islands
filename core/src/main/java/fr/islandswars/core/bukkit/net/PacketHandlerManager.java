package fr.islandswars.core.bukkit.net;

import fr.islandswars.api.net.*;
import fr.islandswars.api.utils.NMSReflectionUtil;
import fr.islandswars.api.utils.Preconditions;
import io.netty.channel.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;


/**
 * File <b>PacketHandlerManager</b> located on fr.islandswars.core.bukkit.net
 * PacketHandlerManager is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 16:38
 * @since 0.2.9
 */
public class PacketHandlerManager implements ProtocolManager {

	private final Map<String, List<PacketHandler>> handlers;

	public PacketHandlerManager() {
		new PacketInterceptor(this);
		this.handlers = new HashMap<>();
	}

	@Override
	public <T extends GamePacket> void sendPacket(Player target, T packet) {
		Preconditions.checkNotNull(target);
		Preconditions.checkNotNull(packet);

		if (packet.getType().getBound() == PacketType.Bound.OUT)
			if (packet.getType().getProtocol() == PacketType.Protocol.PLAY)
				((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet.getNMSPacket());
			else
				System.out.println("Cannot send " + packet.getType().getProtocol().name() + " to " + target.getName());
		else System.err.println("Try to send an input packet \"" + packet.getNMSPacket() + "\" to " + target.getName());
		//TODO remove debug message
	}

	@Override
	public void subscribeHandler(PacketHandler<? extends GamePacket> handler) {
		Preconditions.checkNotNull(handler);

		List<PacketHandler> packetHandler = handlers.computeIfAbsent(handler.getPacketType().getID(), k -> new ArrayList<>());
		packetHandler.add(handler);
	}

	@Override
	public void unsubscribeHandler(PacketHandler<? extends GamePacket> handler) {
		Preconditions.checkNotNull(handler);

		if (handlers.containsKey(handler.getPacketType().getID()))
			handlers.get(handler.getPacketType().getID()).remove(handler);
	}

	@SuppressWarnings("unchecked")
	private <T extends GamePacket> boolean post(PacketEvent<T> event, List<PacketHandler> handlers) {
		Preconditions.checkNotNull(event);
		Preconditions.checkNotNull(handlers);

		handlers.forEach(handler -> handler.handlePacket(event));
		return event.isCancelled();
	}

	boolean handlePacket(Packet packet, Channel playerChannel) {
		if (handlers.containsKey(packet.getClass().getSimpleName())) {
			Class<? extends GamePacket> reflectPacket = PacketType.getPacketList().get(packet.getClass());
			if (reflectPacket != null) {
				System.out.println(packet.getClass().getSimpleName());
				Player p = Bukkit.getOnlinePlayers().stream()
						.map(CraftPlayer.class::cast)
						.filter(cp -> cp.getHandle().playerConnection.networkManager.channel.equals(playerChannel))
						.findFirst()
						.orElse(null);
				PacketEvent<GamePacket> event = new PacketEvent<>(p, NMSReflectionUtil.getConstructorAccessor(reflectPacket, packet.getClass()).newInstance(packet));
				return post(event, handlers.get(packet.getClass().getSimpleName()));
			}
			return false;
		}
		return false;
	}
}

