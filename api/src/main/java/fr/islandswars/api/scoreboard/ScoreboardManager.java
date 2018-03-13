package fr.islandswars.api.scoreboard;

import fr.islandswars.api.scoreboard.Scoreboard.ScoreboardSlot;
import fr.islandswars.api.scoreboard.objective.ObjectiveManager;
import fr.islandswars.api.scoreboard.team.TeamManager;

/**
 * File <b>ScoreboardManager</b> located on fr.islandswars.api.scoreboard
 * ScoreboardManager is a part of Islands Wars - Api.
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
 * Created the 30/12/2017 at 12:37
 * @since 0.2.3
 * <p>
 * Access to the scoreboard manager ap
 */
public interface ScoreboardManager {

	/**
	 * Interface to register and instantiates objective
	 *
	 * @return an abstract nms layer to easily deal with objective packets
	 */
	ObjectiveManager getObjectiveManager();

	/**
	 * Interface to register and instantiates teams
	 *
	 * @return an abstract nms layer to easily deal with team packet
	 */
	TeamManager getTeamManager();

	/**
	 * Create a scoreboard
	 *
	 * @param name           the unique name of the scoreboard
	 * @param scoreboardSlot The slot where will be displayed the scoreboard
	 * @return a Bukkit wrapped scoreboard
	 */
	Scoreboard createScoreboard(String name, ScoreboardSlot scoreboardSlot);

}
