package fr.islandswars.api.scoreboard.objective;

import fr.islandswars.api.scoreboard.ScoreboardComponent;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * File <b>Objective</b> located on fr.islandswars.api.scoreboard.objective
 * Objective is a part of Islands Wars - Api.
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
 * Created the 29/12/2017 at 19:30
 * @since 0.2.3
 * <p>
 * Custom component that represents a bukkit scoreboard objective
 * TODO I18nviewers
 */
public interface Objective extends ScoreboardComponent {

	/**
	 * Get the type of the objective
	 *
	 * @return the type
	 */
	ObjectiveDisplayType getDisplayType();

	/**
	 * Sets the type of objective
	 *
	 * @param displayType The display type
	 */
	void setDisplayType(ObjectiveDisplayType displayType);

	/**
	 * Get the slot of the objective
	 *
	 * @return the slot
	 */
	ObjectiveSlot getDisplaySlot();

	/**
	 * Sets the slot of objective
	 *
	 * @param displaySlot The display slot
	 */
	void setDisplaySlot(ObjectiveSlot displaySlot);

	/**
	 * The slot where the objective will be displayed
	 */
	enum ObjectiveSlot {

		LIST(0),
		SIDEBAR(1),
		BELOW_NAME(2);

		private final int slot;

		ObjectiveSlot(final int slot) {
			this.slot = slot;
		}

		@Nullable
		public static ObjectiveSlot getObjectiveSlot(int slot) {
			return Arrays.stream(values()).filter(objectiveSlot -> objectiveSlot.slot == slot).findFirst().orElse(null);
		}

		public final int getSlot() {
			return this.slot;
		}

	}

	/**
	 * The type of objective to display
	 */
	enum ObjectiveDisplayType {
		INTEGER("integer"),
		HEARTS("hearts");

		private final String display;

		ObjectiveDisplayType(String display) {
			this.display = display;
		}

		public static ObjectiveDisplayType getHealthDisplay(String display) {
			return Arrays.stream(values()).filter(healthDisplay -> healthDisplay.display.equalsIgnoreCase(display)).findFirst().orElse(INTEGER);
		}

		public String getDisplay() {
			return this.display;
		}
	}

}
