package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import fr.islandswars.api.utils.Preconditions;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;

/**
 * File <b>TitlePacket</b> located on fr.islandswars.api.net.packet.play.server
 * TitlePacket is a part of Islands Wars - Api.
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
 * Created the 13/01/2018 at 13:04
 * @since 0.2.8.5
 */
public class TitlePacket extends GamePacket<PacketPlayOutTitle> {

	protected TitlePacket(PacketPlayOutTitle handle) {
		super(handle);
	}

	public TitlePacket(String message, PacketPlayOutTitle.EnumTitleAction action, int fadeIn, int stay, int fadeOut) {
		super(new PacketPlayOutTitle(action, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), fadeIn, stay, fadeOut));
	}

	public PacketPlayOutTitle.EnumTitleAction getAction() {
		return (PacketPlayOutTitle.EnumTitleAction) getHandleValue("a");
	}

	public int getFadeIn() {
		return (int) getHandleValue("c");
	}

	public int getFadeOut() {
		return (int) getHandleValue("e");
	}

	public IChatBaseComponent getMessage() {
		return (IChatBaseComponent) getHandleValue("b");
	}

	public int getStay() {
		return (int) getHandleValue("d");
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.TITLE;
	}

	public void setAction(PacketPlayOutTitle.EnumTitleAction action) {
		setHandleValue("a", action);
	}

	public void setFadeIn(int fadeIn) {
		Preconditions.checkState(fadeIn, ref -> ref > 0);

		setHandleValue("c", fadeIn);
	}

	public void setFadeOut(int fadeOut) {
		Preconditions.checkState(fadeOut, ref -> ref > 0);

		setHandleValue("e", fadeOut);
	}

	public void setMessage(String message) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		setHandleValue("b", chat);
	}

	public void setStay(int stay) {
		Preconditions.checkState(stay, ref -> ref > 0);

		setHandleValue("d", stay);
	}
}
