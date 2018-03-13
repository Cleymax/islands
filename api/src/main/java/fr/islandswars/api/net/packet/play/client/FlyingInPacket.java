package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketPlayInFlying;

/**
 * File <b>FlyingInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * FlyingInPacket is a part of Islands Wars - Api.
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
 * @author Valentin Chassignol (Vinetos), {@literal <vinetos@islandswars.fr>}
 * Created the 07/10/2017 at 10:46
 * @since 0.1
 */
public class FlyingInPacket extends GamePacket<PacketPlayInFlying> {

	public FlyingInPacket(PacketPlayInFlying handle) {
		super(handle);
	}

	public double getX() {
		return (double) getHandleValue("x");
	}

	public void setX(double x) {
		setHandleValue("x", x);
	}

	public double getY() {
		return (double) getHandleValue("y");
	}

	public void setY(double y) {
		setHandleValue("y", y);
	}

	public double getZ() {
		return (double) getHandleValue("z");
	}

	public void setZ(double z) {
		setHandleValue("z", z);
	}

	public float getYaw() {
		return (float) getHandleValue("yaw");
	}

	public void setYaw(float yaw) {
		setHandleValue("yaw", yaw);
	}

	public float getPitch() {
		return (float) getHandleValue("pitch");
	}

	public void setPitch(float pitch) {
		setHandleValue("pitch", pitch);
	}

	public boolean isOnGround() {
		return (boolean) getHandleValue("f");
	}

	public void setOnGround(boolean onGround) {
		setHandleValue("f", onGround);
	}

	public boolean hasPositionPacket() {
		return (boolean) getHandleValue("hasPos");
	}

	public void setHasPositionPacket(boolean hasPositionPacket) {
		setHandleValue("hasPos", hasPositionPacket);
	}

	public boolean hasLookPacket() {
		return (boolean) getHandleValue("hasLook");
	}

	public void setHasLookPacket(boolean hasLookPacket) {
		setHandleValue("hasLook", hasLookPacket);
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Client.FLYING;
	}
}
