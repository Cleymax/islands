package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketPlayInEntityAction;

import static fr.islandswars.api.net.PacketType.Play.Client.ENTITY_ACTION;

/**
 * File <b>EntityActionInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * EntityActionInPacket is a part of Islands Wars - Api.
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
 * @author Valentin Chassignol (Vinetos), {@literal <vinetos@islandswars.fr>}
 * Created the 20/10/17 at 20:04
 * @since 0.1
 */
public class EntityActionInPacket extends GamePacket<PacketPlayInEntityAction> {

	public EntityActionInPacket(PacketPlayInEntityAction handle) {
		super(handle);
	}

	public int getEntityId() {
		return (int) getHandleValue("a");
	}

	public void setEntityId(int entityId) {
		setHandleValue("a", entityId);
	}

	public int getHorseJumpHeight() {
		return (int) getHandleValue("c");
	}

	public void setHorseJumpHeight(int horseJumpHeight) {
		setHandleValue("c", horseJumpHeight);
	}

	public PacketPlayInEntityAction.EnumPlayerAction getAnimation() {
		return (PacketPlayInEntityAction.EnumPlayerAction) getHandleValue("animation");
	}

	public void setAnimation(PacketPlayInEntityAction.EnumPlayerAction animation) {
		setHandleValue("animation", animation);
	}

	@Override
	public PacketType getType() {
		return ENTITY_ACTION;
	}

}
