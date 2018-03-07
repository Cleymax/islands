package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetSlot;

/**
 * File <b>SetSlotPacket</b> located on fr.islandswars.api.net.packet.play.server
 * SetSlotPacket is a part of Islands Wars - Api.
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
 * Created the 10/12/2017 at 18:26
 * @since 0.1.2
 */
public class SetSlotPacket extends GamePacket<PacketPlayOutSetSlot> {

	protected SetSlotPacket(PacketPlayOutSetSlot handle) {
		super(handle);
	}

	public SetSlotPacket(int windowID, int slot, ItemStack itemStack) {
		super(new PacketPlayOutSetSlot(windowID, slot, itemStack));
	}

	public void setWindowID(int windowID) {
		setHandleValue("a", windowID);
	}

	public int getWindowID() {
		return (int) getHandleValue("a");
	}

	public void setSlot(int slot) {
		setHandleValue("b", slot);
	}

	public int getSlot() {
		return (int) getHandleValue("b");
	}

	public void setItemStack(ItemStack itemStack) {
		setHandleValue("c", itemStack);
	}

	public ItemStack getItemStack() {
		return (ItemStack) getHandleValue("c");
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.SET_SLOT;
	}
}
