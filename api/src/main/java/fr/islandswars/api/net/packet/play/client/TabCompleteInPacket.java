package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.PacketPlayInTabComplete;

import javax.annotation.Nullable;

import static fr.islandswars.api.net.PacketType.Play.Client.TAB_COMPLETE;

/**
 * File <b>TabCompleteInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * TabCompleteInPacket is a part of Islands Wars - Api.
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
 * Created the 04/01/2018 at 20:43
 * @since 0.2.4.5
 */
public class TabCompleteInPacket extends GamePacket<PacketPlayInTabComplete> {

	protected TabCompleteInPacket(PacketPlayInTabComplete handle) {
		super(handle);
	}

	public String getStartedCommand() {
		return (String) getHandleValue("a");
	}

	public void setStartedCommand(String startedCommand) {
		setHandleValue("a", startedCommand);
	}

	public boolean isCommand() {
		return (boolean) getHandleValue("b");
	}

	public void setCommand(boolean command) {
		setHandleValue("b", command);
	}

	@Nullable
	public BlockPosition getBlockPosition() {
		return (BlockPosition) getHandleValue("c");
	}

	public void setBlockPosition(BlockPosition position) {
		setHandleValue("c", position);
	}

	@Override
	public PacketType getType() {
		return TAB_COMPLETE;
	}
}
