package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import fr.islandswars.api.scoreboard.Scoreboard.ScoreAction;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore.EnumScoreboardAction;

import static fr.islandswars.api.net.PacketType.Play.Server.SCORE;

/**
 * File <b>ScoreboardScorePacket</b> located on fr.islandswars.api.net.packet.play.server
 * ScoreboardScorePacket is a part of Islands Wars - Api.
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
 * Created the 30/12/2017 at 13:04
 * @since 0.2.3
 */
public class ScoreboardScorePacket extends GamePacket<PacketPlayOutScoreboardScore> {

	protected ScoreboardScorePacket(PacketPlayOutScoreboardScore handle) {
		super(handle);
	}

	public String getName() {
		return (String) getHandleValue("a");
	}

	public void setName(String name) {
		setHandleValue("a", name);
	}

	public String getObjectiveName() {
		return (String) getHandleValue("b");
	}

	public void setObjectiveName(String name) {
		setHandleValue("b", name);
	}

	public int getLineNumber() {
		return (int) getHandleValue("c");
	}

	public void setLineNumber(int lineNumber) {
		setHandleValue("c", lineNumber);
	}

	public ScoreAction getAction() {
		return ScoreAction.getAction((EnumScoreboardAction) getHandleValue("d"));
	}

	public void setAction(ScoreAction action) {
		setHandleValue("d", action.getAction());
	}


	@Override
	public PacketType getType() {
		return SCORE;
	}
}
