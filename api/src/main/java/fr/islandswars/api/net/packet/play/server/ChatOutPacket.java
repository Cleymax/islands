package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

/**
 * File <b>ChatOutPacket</b> located on fr.islandswars.api.net.packet.play.server
 * ChatOutPacket is a part of Islands Wars - Api.
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
 * Created the 22/12/2017 at 19:45
 * @since 0.2
 */
public class ChatOutPacket extends GamePacket<PacketPlayOutChat> {

	protected ChatOutPacket(PacketPlayOutChat handle) {
		super(handle);
	}

	public void setMessageType(ChatMessageType type) {
		setHandleValue("b", type);
	}

	public ChatMessageType getMessageType() {
		return handle.c();
	}

	public void setMessage(String message) {
		setHandleValue("a", IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"));
	}

	public IChatBaseComponent getMessage() {
		return (IChatBaseComponent) getHandleValue("a");
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.CHAT_OUT;
	}
}
