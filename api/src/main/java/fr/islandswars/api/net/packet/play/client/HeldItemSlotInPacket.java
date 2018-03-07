package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketPlayInHeldItemSlot;

import static fr.islandswars.api.net.PacketType.Play.Client.HELD_ITEM_SLOT;

/**
 * File <b>HeldItemSlotInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * HeldItemSlotInPacket is a part of Islands Wars - Api.
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
 * Created the 20/10/17 at 20:37
 * @since 0.1
 */
public class HeldItemSlotInPacket extends GamePacket<PacketPlayInHeldItemSlot> {

	public HeldItemSlotInPacket(PacketPlayInHeldItemSlot handle) {
		super(handle);
	}

	public int getSlot() {
		return (int) getHandleValue("itemInHandIndex");
	}

	public void setSlot(int slot) {
		setHandleValue("itemInHandIndex", slot);
	}

	@Override
	public PacketType getType() {
		return HELD_ITEM_SLOT;
	}
}
