package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.BossBattle;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutBoss;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;

import java.util.UUID;

/**
 * File <b>BossPacket</b> located on fr.islandswars.api.net.packet.play.server
 * BossPacket is a part of Islands Wars - Api.
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
 * Created the 26/12/2017 at 18:15
 * @since 0.2.3
 */
public class BossPacket extends GamePacket<PacketPlayOutBoss> {

	protected BossPacket(PacketPlayOutBoss handle) {
		super(handle);
	}

	public BossPacket(PacketPlayOutBoss.Action action, BossBattle bar) {
		this(new PacketPlayOutBoss(action, bar));
	}

	public UUID getBarId() {
		return (UUID) getHandleValue("a");
	}

	public void setBarId(UUID id) {
		setHandleValue("a", id);
	}

	public PacketPlayOutBoss.Action getAction() {
		return (PacketPlayOutBoss.Action) getHandleValue("b");
	}

	public void setAction(PacketPlayOutBoss.Action action) {
		setHandleValue("b", action);
	}

	public IChatBaseComponent getTitle() {
		return (IChatBaseComponent) getHandleValue("c");
	}

	public void setTitle(String title) {
		setHandleValue("c", CraftChatMessage.fromString(title, true)[0]);
	}

	public float getBarHealth() {
		return (float) getHandleValue("d");
	}

	public void setHealth(float health) {
		setHandleValue("d", health);
	}

	public BossBattle.BarColor getBarColor() {
		return (BossBattle.BarColor) getHandleValue("e");
	}

	public void setBarColor(BossBattle.BarColor color) {
		setHandleValue("e", color);
	}

	public BossBattle.BarStyle getBarStyle() {
		return (BossBattle.BarStyle) getHandleValue("f");
	}

	public void setBarStyle(BossBattle.BarStyle style) {
		setHandleValue("f", style);
	}

	public boolean isDarkenSky() {
		return (boolean) getHandleValue("g");
	}

	public void setDarkenSky(boolean darkenSky) {
		setHandleValue("g", darkenSky);
	}

	public boolean isPlayingMusic() {
		return (boolean) getHandleValue("h");
	}

	public void setPlayMusic(boolean playMusic) {
		setHandleValue("h", playMusic);
	}

	public boolean isFogActive() {
		return (boolean) getHandleValue("i");
	}

	public void setFogActive(boolean fog) {
		setHandleValue("i", fog);
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.BOSS;
	}
}
