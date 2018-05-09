package fr.islandswars.api.scoreboard;

import fr.islandswars.api.player.IslandsPlayer;
import java.util.function.Supplier;

/**
 * File <b>ScoreboardComponent</b> located on fr.islandswars.api.scoreboard
 * ScoreboardComponent is a part of Islands Wars - Api.
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
 * Created the 30/12/2017 at 13:08
 * @since 0.2.3
 * <p>
 * Represent a scoreboard component
 */
public interface ScoreboardComponent {

	/**
	 * Get the display name of the component
	 *
	 * @return the display name of the component
	 */
	String getDisplayName();

	/**
	 * Get the name of the component
	 *
	 * @return The name of the component
	 */
	String getName();

	/**
	 * Set the display name
	 *
	 * @param displayName The new display name
	 */
	void setDisplayName(String displayName);

	/**
	 * Set the display name
	 *
	 * @param displayName The new display name
	 * @param parameters  some translation parameters
	 */
	void setDisplayName(String displayName, Supplier<Object[]> parameters);

	/**
	 * Register some parameters that will be used for all players to format {@link #getDisplayName()}
	 *
	 * @param parameters translation parameters
	 */
	void setGlobalTitleParameter(Supplier<Object[]> parameters);

	/**
	 * Update this scoreboard with the given packet for all viewers according to this
	 * {@link Action label}
	 *
	 * @param action what to do with this scoreboard
	 */
	void update(Action action);

}
