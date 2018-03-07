package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import fr.islandswars.api.scoreboard.team.Team.CollisionRule;
import fr.islandswars.api.scoreboard.team.Team.NameTagVisibility;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardTeam;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;

import static fr.islandswars.api.net.PacketType.Play.Server.TEAM;

/**
 * File <b>ScoreboardTeamPacket</b> located on fr.islandswars.api.net.packet.play.server
 * ScoreboardTeamPacket is a part of Islands Wars - Api.
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
 * Created the 27/12/2017 at 10:37
 * @since 0.2.3
 */
public class ScoreboardTeamPacket extends GamePacket<PacketPlayOutScoreboardTeam> {

	private int option = 0;

	protected ScoreboardTeamPacket(PacketPlayOutScoreboardTeam handle) {
		super(handle);
	}

	public void setTeamName(String name) {
		setHandleValue("a", name);
	}

	public String getTeamName() {
		return (String) getHandleValue("a");
	}

	public void setDisplayName(String displayName) {
		setHandleValue("b", displayName);
	}

	public String getDisplayName() {
		return (String) getHandleValue("b");
	}

	public void setPrefix(String prefix) {
		setHandleValue("c", prefix);
	}

	public String getPrefix() {
		return (String) getHandleValue("c");
	}

	public void setSuffix(String suffix) {
		setHandleValue("d", suffix);
	}

	public String getSuffix() {
		return (String) getHandleValue("d");
	}

	public void setNameTagVisibility(NameTagVisibility nameTagVisibility) {
		setHandleValue("e", nameTagVisibility.getNameTagVisibility());
	}

	@Nullable
	public NameTagVisibility getNameTagVisibility() {
		return NameTagVisibility.getNameTagVisibility((String) getHandleValue("e"));
	}

	public void setCollisionRule(CollisionRule collisionRule) {
		setHandleValue("f", collisionRule.getCollisionRule());
	}

	@Nullable
	public CollisionRule getCollisionRule() {
		return CollisionRule.getCollisionRule((String) getHandleValue("f"));
	}

	/**
	 * -1 by default
	 *
	 * @param chatColor the chatColor
	 */
	public void setChatColor(int chatColor) {
		setHandleValue("g", chatColor);
	}

	public int getChatColor(int chatColor) {
		return (int) getHandleValue("g");
	}

	public void setPlayers(Collection<String> players) {
		setHandleValue("h", players);
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getPlayers() {
		return (Collection<String>) getHandleValue("h");
	}

	public void setMode(TeamMode mode) {
		setHandleValue("i", mode.mode);
	}

	@Nullable
	public TeamMode getMode() {
		return TeamMode.getTeamMode((int) getHandleValue("i"));
	}

	public void allowFriendlyFire() {
		option |= 0b01;
		setHandleValue("j", (int) getHandleValue("j") | 0b01);
	}

	public void setCanSeeFriendlyInvisible() {
		option |= 0b10;
		setHandleValue("j", option);
	}

	public boolean hasAllowedFriendlyFire() {
		return ((int) getHandleValue("j") & 0b01) != 0;
	}

	public boolean canSeeFriendlyInvisible() {
		return ((int) getHandleValue("j") & 0b10) != 0;
	}

	@Override
	public PacketType getType() {
		return TEAM;
	}

	/**
	 * The mode of the team (when the client receive the packet, what action it have to do)
	 */
	public enum TeamMode {

		CREATE(0),
		REMOVE(1),
		UPDATE(2),
		ADD_PLAYER(3),
		REMOVE_PLAYER(4);

		private final int mode;

		TeamMode(final int mode) {
			this.mode = mode;
		}

		@Nullable
		public static TeamMode getTeamMode(int mode) {
			return Arrays.stream(values()).filter(teamMode -> teamMode.mode == mode).findFirst().orElse(null);
		}

	}

}
