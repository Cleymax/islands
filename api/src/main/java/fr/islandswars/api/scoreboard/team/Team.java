package fr.islandswars.api.scoreboard.team;

import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.scoreboard.ScoreboardComponent;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.bukkit.DyeColor;

/**
 * File <b>Team</b> located on fr.islandswars.api.scoreboard.team
 * Team is a part of Islands Wars - Api.
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
 * Created the 29/12/2017 at 16:55
 * @since 0.2.3
 * <p>
 * A class which represent a Minecraft Team
 */
public interface Team extends ScoreboardComponent {

	/**
	 * Add a player to the team
	 *
	 * @param player the player who will be added
	 */
	void addPlayer(IslandsPlayer player);

	/**
	 * Add a player who can see the team
	 *
	 * @param player who can see the team
	 */
	void addViewer(IslandsPlayer player);

	/**
	 * Check if the team allow the friendly fire
	 *
	 * @return <code>true</code> if the team allow the friendly fire
	 */
	boolean allowFriendlyFire();

	/**
	 * Check if the team can see her members invisible
	 *
	 * @return <code>true</code> if can see her members invisible
	 */
	boolean canSeeFriendlyInvisible();

	/**
	 * Get the collision rule
	 *
	 * @return A {@link CollisionRule}
	 */
	CollisionRule getCollisionRule();

	/**
	 * Get the name tag visibility
	 *
	 * @return A {@link NameTagVisibility}
	 */
	NameTagVisibility getNameTagVisibility();

	/**
	 * Get player in the team
	 *
	 * @return A {@link Stream} of {@link IslandsPlayer}
	 */
	Stream<IslandsPlayer> getPlayers();

	/**
	 * Get player in the viewers of the team
	 *
	 * @return A {@link Stream} of {@link IslandsPlayer}
	 */
	Stream<IslandsPlayer> getViewers();

	/**
	 * Get the prefix of a team
	 *
	 * @return the prefix of the team
	 */
	String getPrefix();

	/**
	 * Get the suffix of a team
	 *
	 * @return the suffix of the team
	 */
	String getSuffix();

	/**
	 * Get the color of a team
	 *
	 * @return THe {@link DyeColor} of the team
	 */
	DyeColor getTeamColor();

	/**
	 * Check if a player is in a team
	 *
	 * @param player the player who will be checked
	 * @return <code>true</code> if the player is in the team
	 */
	boolean hasPlayer(IslandsPlayer player);

	/**
	 * Remove a player from a team
	 *
	 * @param player the name player will who will be removed
	 */
	void removePlayer(IslandsPlayer player);

	/**
	 * Remove a player from a team's viewers
	 *
	 * @param player the name player who will be removed
	 */
	void removeViewer(IslandsPlayer player);

	/**
	 * Set the collision rule
	 *
	 * @param collisionRule {@link CollisionRule}
	 */
	void setCollisionRule(CollisionRule collisionRule);

	/**
	 * Set if the team allow the friendly fire
	 *
	 * @param friendlyFire the new label of friendly fire
	 */
	void setFriendlyFire(boolean friendlyFire);

	/**
	 * Set the name tag visibility
	 *
	 * @param nameTagVisibility {@link NameTagVisibility}
	 */
	void setNameTagVisibility(NameTagVisibility nameTagVisibility);

	/**
	 * Set the prefix of a team
	 *
	 * @param prefix the new prefix
	 */
	void setPrefix(String prefix);

	/**
	 * Set the prefix of a team
	 *
	 * @param prefix     the new prefix
	 * @param parameters some translation parameters
	 */
	void setPrefix(String prefix, Supplier<Object[]> parameters);

	/**
	 * Set if the team can see her members invisible
	 *
	 * @param seeFriendlyInvisible the new label of see members invisible
	 */
	void setSeeFriendlyInvisible(boolean seeFriendlyInvisible);

	/**
	 * Set the suffix of a team
	 *
	 * @param suffix the new suffix
	 */
	void setSuffix(String suffix);

	/**
	 * Set the suffix of a team
	 *
	 * @param suffix     the new suffix
	 * @param parameters some translation parameters
	 */
	void setSuffix(String suffix, Supplier<Object[]> parameters);

	/**
	 * Set the color for a team
	 *
	 * @param teamColor the new color of the team
	 */
	void setTeamColor(DyeColor teamColor);

	/**
	 * The CollisionRule parameter for the team
	 */
	enum CollisionRule {
		ALWAYS("always"),
		NEVER("never"),
		FOR_OTHER_TEAMS("pushOtherTeams"),
		FOR_OWN_TEAM("pushOwnTeam");

		private final String collisionRule;

		CollisionRule(String collisionRule) {
			this.collisionRule = collisionRule;
		}

		@Nullable
		public static CollisionRule getCollisionRule(String rule) {
			return Arrays.stream(values()).filter(collisionRule -> collisionRule.collisionRule.equalsIgnoreCase(rule)).findFirst().orElse(null);
		}

		public String getCollisionRule() {
			return collisionRule;
		}
	}

	/**
	 * The NameTagVisibility Parameter for the team
	 */
	enum NameTagVisibility {
		ALWAYS("always"),
		NEVER("never"),
		FOR_OTHER_TEAMS("hideForOtherTeams"),
		FOR_OWN_TEAM("hideForOwnTeam");

		private final String nameTagVisibility;

		NameTagVisibility(String nameTagVisibility) {
			this.nameTagVisibility = nameTagVisibility;
		}

		@Nullable
		public static NameTagVisibility getNameTagVisibility(String nameTag) {
			return Arrays.stream(values()).filter(nameTagVisibility -> nameTagVisibility.nameTagVisibility.equalsIgnoreCase(nameTag)).findFirst().orElse(null);
		}

		public String getNameTagVisibility() {
			return nameTagVisibility;
		}

	}

}
