package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.PacketPlayInSetCreativeSlot;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import static fr.islandswars.api.net.PacketType.Play.Client.SET_CREATIVE_SLOT;

/**
 * File <b>SetCreativeSlotInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * SetCreativeSlotInPacket is a part of Islands Wars - Api.
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
 * Created the 21/10/17 at 10:21
 * @since 0.1
 */
public class SetCreativeSlotInPacket extends GamePacket<PacketPlayInSetCreativeSlot> {

	public SetCreativeSlotInPacket(PacketPlayInSetCreativeSlot handle) {
		super(handle);
	}

	public org.bukkit.inventory.ItemStack getItemStack() {
		return CraftItemStack.asBukkitCopy((ItemStack) getHandleValue("b"));
	}

	public int getSlot() {
		return (int) getHandleValue("slot");
	}

	@Override
	public PacketType getType() {
		return SET_CREATIVE_SLOT;
	}

	public void setItemStack(org.bukkit.inventory.ItemStack itemStack) {
		setHandleValue("b", CraftItemStack.asNMSCopy(itemStack));
	}

	public void setSlot(int slot) {
		setHandleValue("slot", slot);
	}

}
