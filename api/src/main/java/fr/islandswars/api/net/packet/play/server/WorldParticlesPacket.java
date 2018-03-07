package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

/**
 * File <b>WorldParticlesPacket</b> located on fr.islandswars.api.net.packet.play.server
 * WorldParticlesPacket is a part of Islands Wars - Api.
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
 * Created the 09/01/2018 at 12:06
 * @since 0.2.8
 */
public class WorldParticlesPacket extends GamePacket<PacketPlayOutWorldParticles> {

	protected WorldParticlesPacket(PacketPlayOutWorldParticles handle) {
		super(handle);
	}

	public EnumParticle getParticle() {
		return (EnumParticle) getHandleValue("a");
	}

	public void setParticle(EnumParticle particle) {
		setHandleValue("a", particle);
	}

	public boolean useLongDistance() {
		return (boolean) getHandleValue("j");
	}

	public void setLongDistance(boolean value) {
		setHandleValue("j", value);
	}

	public int getCount() {
		return (int) getHandleValue("i");
	}

	public void setCount(int count) {
		setHandleValue("i", count);
	}

	public float getX() {
		return (float) getHandleValue("b");
	}

	public void setX(float newX) {
		setHandleValue("b", newX);
	}

	public float getY() {
		return (float) getHandleValue("c");
	}

	public void setY(float newY) {
		setHandleValue("c", newY);
	}

	public float getZ() {
		return (float) getHandleValue("d");
	}

	public void setZ(float newZ) {
		setHandleValue("d", newZ);
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.WORLD_PARTICLES;
	}
}
