package fr.islandswars.api.scoreboard.objective;

import fr.islandswars.api.scoreboard.objective.Objective.ObjectiveDisplayType;
import fr.islandswars.api.scoreboard.objective.Objective.ObjectiveSlot;

import java.util.Set;
import java.util.function.Supplier;

/**
 * File <b>ObjectiveManager</b> located on fr.islandswars.api.scoreboard.objective
 * ObjectiveManager is a part of Islands Wars - Api.
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
 * Created the 30/12/2017 at 12:14
 * @since 0.2.3
 * <p>
 * Deal with all your custom {@link Objective}
 */
public interface ObjectiveManager {

	/**
	 * Create an objective
	 *
	 * @param name The unique name of the objective (can't be changed after)
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, Supplier<Object[]> parameters);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displayType The display type of the objective (like heart for life)
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveDisplayType displayType);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displayType The display type of the objective (like heart for life)
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveDisplayType displayType, Supplier<Object[]> parameters);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displaySlot The slot where will be display the objective
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveSlot displaySlot);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displaySlot The slot where will be display the objective
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveSlot displaySlot, Supplier<Object[]> parameters);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displayType The display type of the objective (like heart for life)
	 * @param displaySlot The slot where will be display the objective
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveDisplayType displayType, ObjectiveSlot displaySlot);

	/**
	 * Create an objective
	 *
	 * @param name        The unique name of the objective (can't be changed after)
	 * @param displayName The display name of the objective
	 * @param displayType The display type of the objective (like heart for life)
	 * @param displaySlot The slot where will be display the objective
	 * @param parameters  some translation parameters
	 * @return A Bukkit wrapped Objective
	 */
	Objective createObjective(String name, String displayName, ObjectiveDisplayType displayType, ObjectiveSlot displaySlot, Supplier<Object[]> parameters);

	/**
	 * Get all created objectives
	 *
	 * @return A {@link Set} of {@link Objective}
	 */
	Set<Objective> getRegisterObjectives();

}
