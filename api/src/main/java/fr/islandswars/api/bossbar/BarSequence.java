package fr.islandswars.api.bossbar;

import fr.islandswars.api.player.IslandsPlayer;
import java.util.stream.Stream;

/**
 * File <b>BarSequence</b> located on fr.islandswars.api.bossbar
 * BarSequence is a part of Islands Wars - Api.
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
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 26/12/2017 at 21:58
 * @since 0.2.3
 * <p>
 * An array of bossbar to display in the given order
 */
public interface BarSequence {

	/**
	 * @return all bars stocked in this sequence
	 */
	Stream<Bar> getBars();

	/**
	 * Get (if exist) the currently displayed bossbar
	 *
	 * @return the current displayed bossbar
	 */
	Bar getCurrentBar();

	/**
	 * @return this bossbar viewers
	 */
	Stream<IslandsPlayer> getViewers();

	/**
	 * Will call {@link IslandsPlayer#hideBar(Bar)} on each player
	 * and free all resources
	 */
	void shutdownSequence();

}
