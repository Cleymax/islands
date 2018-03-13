package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.PacketPlayOutWindowItems;

import java.util.List;

/**
 * File <b>WindowItemsPacket</b> located on fr.islandswars.api.net.packet.play.server
 * WindowItemsPacket is a part of Islands Wars - Api.
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
 * Created the 09/12/2017 at 17:26
 * @since 0.1.2
 */
public class WindowItemsPacket extends GamePacket<PacketPlayOutWindowItems> {

	protected WindowItemsPacket(PacketPlayOutWindowItems handle) {
		super(handle);
	}

	public WindowItemsPacket(int id, List<ItemStack> items) {
		this(new PacketPlayOutWindowItems());
		setWindowID(id);
		setItemStacks(items);
	}

	public int getWindowID() {
		return (int) getHandleValue("a");
	}

	public void setWindowID(int newId) {
		setHandleValue("a", newId);
	}

	@SuppressWarnings("unchecked")
	public List<ItemStack> getItemStacks() {
		return (List<ItemStack>) getHandleValue("b");
	}

	public void setItemStacks(List<ItemStack> newItems) {
		setHandleValue("b", newItems);
	}

	@Override
	public PacketType getType() {
		return PacketType.Play.Server.WINDOW_ITEMS;
	}
}
