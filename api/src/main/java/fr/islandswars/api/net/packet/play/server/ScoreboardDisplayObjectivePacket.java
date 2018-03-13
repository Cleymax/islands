package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import fr.islandswars.api.scoreboard.objective.Objective.ObjectiveSlot;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardDisplayObjective;

import static fr.islandswars.api.net.PacketType.Play.Server.DISPLAY_OBJECTIVE;

/**
 * File <b>ScoreboardDisplayObjectivePacket</b> located on fr.islandswars.api.net.packet.play.server
 * ScoreboardDisplayObjectivePacket is a part of Islands Wars - Api.
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
 * Created the 29/12/2017 at 20:13
 * @since 0.2.3
 */
public class ScoreboardDisplayObjectivePacket extends GamePacket<PacketPlayOutScoreboardDisplayObjective> {

	protected ScoreboardDisplayObjectivePacket(PacketPlayOutScoreboardDisplayObjective handle) {
		super(handle);
	}

	public ObjectiveSlot getObjectiveSlot() {
		return ObjectiveSlot.getObjectiveSlot((int) getHandleValue("a"));
	}

	public void setObjectiveSlot(ObjectiveSlot slot) {
		setHandleValue("a", slot.getSlot());
	}

	public String getObjectiveName() {
		return (String) getHandleValue("b");
	}

	public void setObjectiveName(String objectiveName) {
		setHandleValue("b", objectiveName);
	}

	@Override
	public PacketType getType() {
		return DISPLAY_OBJECTIVE;
	}
}
