package fr.islandswars.api.net;

import fr.islandswars.api.net.packet.handshake.server.HandShakePacket;
import fr.islandswars.api.net.packet.login.client.EncryptionResponsePacket;
import fr.islandswars.api.net.packet.login.client.LoginStartPacket;
import fr.islandswars.api.net.packet.login.server.DisconnectPacket;
import fr.islandswars.api.net.packet.login.server.EncryptionRequestPacket;
import fr.islandswars.api.net.packet.login.server.SetCompressionPacket;
import fr.islandswars.api.net.packet.login.server.SuccessPacket;
import fr.islandswars.api.net.packet.play.client.*;
import fr.islandswars.api.net.packet.play.server.*;
import fr.islandswars.api.net.packet.status.client.PingPacket;
import fr.islandswars.api.net.packet.status.client.StartPacket;
import fr.islandswars.api.net.packet.status.server.PongPacket;
import fr.islandswars.api.net.packet.status.server.ServerInfoPacket;
import net.minecraft.server.v1_12_R1.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static fr.islandswars.api.net.PacketType.Bound.IN;
import static fr.islandswars.api.net.PacketType.Bound.OUT;
import static fr.islandswars.api.net.PacketType.Protocol.*;

/**
 * File <b>PacketHandler</b> located on fr.islandswars.api.net
 * PacketHandler is a part of Islands Wars - Api.
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
 * Layer that link bukkit and islands wars packets
 */
public class PacketType<T extends GamePacket> {

	private static final Map<Class<? extends Packet>, Class<? extends GamePacket>> packetList = new HashMap<>();
	private static Map<Class<? extends Packet>, Class<? extends GamePacket>> unmodifiablePacketList;
	private final  Class<T>                                                  packet;
	private final  Class<? extends Packet>                                   nmsPacket;
	private final  Protocol                                                  protocol;
	private final  Bound                                                     bound;

	public PacketType(Class<T> packet, Class<? extends Packet> nmsPacket, Protocol protocol, Bound bound) {
		this.protocol = protocol;
		this.nmsPacket = nmsPacket;
		this.packet = packet;
		this.bound = bound;
		packetList.put(nmsPacket, packet);
	}

	/**
	 * @return an immutable Map access (read only)
	 * @see Collections#unmodifiableMap(Map)
	 */
	public static Map<Class<? extends Packet>, Class<? extends GamePacket>> getPacketList() {
		return unmodifiablePacketList == null ? unmodifiablePacketList = Collections.unmodifiableMap(packetList) : unmodifiablePacketList;
	}

	/**
	 * @return the packet wrapper's protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * @return the packet wrapper's class
	 */
	public Class<T> getPacket() {
		return packet;
	}

	/**
	 * @return this packet name
	 */
	public String getID() {
		return nmsPacket.getSimpleName();
	}

	/**
	 * @return the packet's bound
	 */
	public Bound getBound() {
		return bound;
	}

	public enum Protocol {
		/**
		 * Id: -1
		 */
		HANDSHAKE,
		/**
		 * Id: 0
		 */
		PLAY,
		/**
		 * Id: 1
		 */
		STATUS,
		/**
		 * Id: 2
		 */
		LOGIN
	}

	public enum Bound {
		/**
		 * Incomming buffer
		 */
		IN,

		/**
		 * Outcomming buffer
		 */
		OUT
	}

	public static final class Status {

		public static final class Client {
			public static final PacketType<PingPacket>  PING  = new PacketType<>(PingPacket.class, PacketStatusInPing.class, STATUS, IN);
			public static final PacketType<StartPacket> START = new PacketType<>(StartPacket.class, PacketStatusInStart.class, STATUS, IN);
		}

		public static final class Server {
			public static final PacketType<PongPacket>       PONG        = new PacketType<>(PongPacket.class, PacketStatusOutPong.class, STATUS, OUT);
			public static final PacketType<ServerInfoPacket> SERVER_INFO = new PacketType<>(ServerInfoPacket.class, PacketStatusOutServerInfo.class, STATUS, OUT);
		}

	}

	public static final class Login {

		public static final class Client {
			public static final PacketType<EncryptionResponsePacket> ENCRYPION_RESPONSE = new PacketType<>(EncryptionResponsePacket.class, PacketLoginInEncryptionBegin.class, LOGIN, IN);
			public static final PacketType<LoginStartPacket>         LOGIN_START        = new PacketType<>(LoginStartPacket.class, PacketLoginInStart.class, LOGIN, IN);
		}

		public static final class Server {
			public static final PacketType<DisconnectPacket>        DISCONNECT         = new PacketType<>(DisconnectPacket.class, PacketLoginOutDisconnect.class, LOGIN, OUT);
			public static final PacketType<EncryptionRequestPacket> ENCRYPTION_REQUEST = new PacketType<>(EncryptionRequestPacket.class, PacketLoginOutEncryptionBegin.class, LOGIN, OUT);
			public static final PacketType<SetCompressionPacket>    COMPRESSION        = new PacketType<>(SetCompressionPacket.class, PacketLoginOutSetCompression.class, LOGIN, OUT);
			public static final PacketType<SuccessPacket>           SUCCESS            = new PacketType<>(SuccessPacket.class, PacketLoginOutSuccess.class, LOGIN, OUT);
		}
	}

	public static final class Handshake {

		public static final class Server {
			public static final PacketType<HandShakePacket> HANDSHAKE = new PacketType<>(HandShakePacket.class, PacketHandshakingInSetProtocol.class, Protocol.HANDSHAKE, OUT);
		}

	}

	public static final class Play {

		public static final class Client {
			public static final PacketType<ChatInPacket>               CHAT                 = new PacketType<>(ChatInPacket.class, PacketPlayInChat.class, PLAY, IN);
			public static final PacketType<FlyingInPacket>             FLYING               = new PacketType<>(FlyingInPacket.class, PacketPlayInFlying.class, PLAY, IN);
			public static final PacketType<AbilitiesInPacket>          ABILITIES            = new PacketType<>(AbilitiesInPacket.class, PacketPlayInAbilities.class, PLAY, IN);
			public static final PacketType<WindowInPacket>             CLOSE_WINDOW         = new PacketType<>(WindowInPacket.class, PacketPlayInCloseWindow.class, PLAY, IN);
			public static final PacketType<EnchantItemInPacket>        ENCHANT_ITEM         = new PacketType<>(EnchantItemInPacket.class, PacketPlayInEnchantItem.class, PLAY, IN);
			public static final PacketType<ArmAnimationInPacket>       ARM_ANIMATION        = new PacketType<>(ArmAnimationInPacket.class, PacketPlayInArmAnimation.class, PLAY, IN);
			public static final PacketType<EntityActionInPacket>       ENTITY_ACTION        = new PacketType<>(EntityActionInPacket.class, PacketPlayInEntityAction.class, PLAY, IN);
			public static final PacketType<HeldItemSlotInPacket>       HELD_ITEM_SLOT       = new PacketType<>(HeldItemSlotInPacket.class, PacketPlayInHeldItemSlot.class, PLAY, IN);
			public static final PacketType<KeepAliveInPacket>          KEEP_ALIVE           = new PacketType<>(KeepAliveInPacket.class, PacketPlayInKeepAlive.class, PLAY, IN);
			public static final PacketType<ResourcePackStatusInPacket> RESOURCE_PACK_STATUS = new PacketType<>(ResourcePackStatusInPacket.class, PacketPlayInResourcePackStatus.class, PLAY, IN);
			public static final PacketType<SetCreativeSlotInPacket>    SET_CREATIVE_SLOT    = new PacketType<>(SetCreativeSlotInPacket.class, PacketPlayInSetCreativeSlot.class, PLAY, IN);
			public static final PacketType<TabCompleteInPacket>        TAB_COMPLETE         = new PacketType<>(TabCompleteInPacket.class, PacketPlayInTabComplete.class, PLAY, IN);
		}

		public static final class Server {
			public static final PacketType<WindowItemsPacket>                WINDOW_ITEMS      = new PacketType<>(WindowItemsPacket.class, PacketPlayOutWindowItems.class, PLAY, OUT);
			public static final PacketType<SetSlotPacket>                    SET_SLOT          = new PacketType<>(SetSlotPacket.class, PacketPlayOutSetSlot.class, PLAY, OUT);
			public static final PacketType<ChatOutPacket>                    CHAT_OUT          = new PacketType<>(ChatOutPacket.class, PacketPlayOutChat.class, PLAY, OUT);
			public static final PacketType<BossPacket>                       BOSS              = new PacketType<>(BossPacket.class, PacketPlayOutBoss.class, PLAY, OUT);
			public static final PacketType<ScoreboardTeamPacket>             TEAM              = new PacketType<>(ScoreboardTeamPacket.class, PacketPlayOutScoreboardTeam.class, PLAY, OUT);
			public static final PacketType<ScoreboardObjectivePacket>        OBJECTIVE         = new PacketType<>(ScoreboardObjectivePacket.class, PacketPlayOutScoreboardObjective.class, PLAY, OUT);
			public static final PacketType<ScoreboardDisplayObjectivePacket> DISPLAY_OBJECTIVE = new PacketType<>(ScoreboardDisplayObjectivePacket.class, PacketPlayOutScoreboardDisplayObjective.class, PLAY, OUT);
			public static final PacketType<ScoreboardScorePacket>            SCORE             = new PacketType<>(ScoreboardScorePacket.class, PacketPlayOutScoreboardDisplayObjective.class, PLAY, OUT);
			public static final PacketType<TabCompleteOutPacket>             TAB_COMPLETE      = new PacketType<>(TabCompleteOutPacket.class, PacketPlayOutTabComplete.class, PLAY, IN);
			public static final PacketType<WorldParticlesPacket>             WORLD_PARTICLES   = new PacketType<>(WorldParticlesPacket.class, PacketPlayOutWorldParticles.class, PLAY, IN);
			public static final PacketType<TitlePacket>                      TITLE             = new PacketType<>(TitlePacket.class, PacketPlayOutTitle.class, PLAY, IN);
		}
	}
}
