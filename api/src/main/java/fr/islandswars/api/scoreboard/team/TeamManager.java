package fr.islandswars.api.scoreboard.team;

import fr.islandswars.api.player.IslandsPlayer;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import org.bukkit.DyeColor;

/**
 * File <b>TeamManager</b> located on fr.islandswars.api.scoreboard.team
 * TeamManager is a part of Islands Wars - Api.
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
 * Created the 29/12/2017 at 18:11
 * @since 0.2.3
 * <p>
 * Deal with all your custom teams
 */
public interface TeamManager {

	/**
	 * Create a team
	 *
	 * @param name The name of the team (can't be changed after)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String displayName);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String displayName, Supplier<Object[]> parameters);

	/**
	 * Create a team
	 *
	 * @param name      The name of the team (can't be changed after)
	 * @param teamColor The {@link DyeColor} of the team (to make a GUI for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, DyeColor teamColor);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String prefix, String displayName, String suffix);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String prefix, String displayName, String suffix, Supplier<Object[]> parameters);

	/**
	 * Create a team
	 *
	 * @param name      The name of the team (can't be changed after)
	 * @param prefix    The prefix of the team (before the player's name)
	 * @param suffix    The suffix of the team (after the player's name)
	 * @param teamColor The {@link DyeColor} of the team (to make a GUI for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String prefix, String suffix, DyeColor teamColor);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param teamColor   The {@link DyeColor} of the team (to make a GUI for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, DyeColor teamColor);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param players     The players into the team
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, Set<IslandsPlayer> players);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param players     The players into the team
	 * @param teamColor   The {@link DyeColor} of the team (to make a GUI for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, Set<IslandsPlayer> players, DyeColor teamColor);

	/**
	 * Create a team
	 *
	 * @param name       The name of the team (can't be changed after)
	 * @param prefix     The prefix of the team (before the player's name)
	 * @param suffix     The suffix of the team (after the player's name)
	 * @param teamColor  The {@link DyeColor} of the team (to make a GUI for example)
	 * @param parameters some translation parameters
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String prefix, String suffix, DyeColor teamColor, Supplier<Object[]> parameters);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param teamColor   The {@link DyeColor} of the team (to make a GUI for example)
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, DyeColor teamColor, Supplier<Object[]> parameters);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param parameters  some translation parameters
	 * @param players     The players into the team
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, Supplier<Object[]> parameters, Set<IslandsPlayer> players);

	/**
	 * Create a team
	 *
	 * @param name        The name of the team (can't be changed after)
	 * @param suffix      The suffix of the team (after the player's name)
	 * @param displayName The display name of the team (on the right scoreboard for example)
	 * @param prefix      The prefix of the team (before the player's name)
	 * @param parameters  some translation parameters
	 * @param players     The players into the team
	 * @param teamColor   The {@link DyeColor} of the team (to make a GUI for example)
	 * @return A Bukkit wrapped Team
	 */
	Team createTeam(String name, String suffix, String displayName, String prefix, Supplier<Object[]> parameters, Set<IslandsPlayer> players, DyeColor teamColor);

	/**
	 * Get all created teams
	 *
	 * @return A {@link Set} of {@link Team}
	 */
	Set<Team> getRegisterTeams();

	/**
	 * Get the team for a player
	 *
	 * @param player who is in a team
	 * @return The {@link Team} of the player in an Optional
	 */
	Optional<Team> getTeamOfPlayer(IslandsPlayer player);
}
