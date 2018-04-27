package fr.islandswars.api.player.rank;

import org.bukkit.ChatColor;

/**
 * File <b>IslandsRank</b> located on fr.islandswars.api.player.rank
 * IslandsRank is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 11:47
 * @since 0.2.6
 * <p>
 * An abstraction for ranking since player's ranks is evolutive
 */
public interface IslandsRank {

	/**
	 * @return this complete rank name
	 */
	String getDisplayName();

	/**
	 * @return the bukkit color associated to this rank
	 */
	ChatColor getRankColor();

	/**
	 * @return this rank level (use for permission)
	 */
	int getRankLevel();

	/**
	 * @return a short identifier for this rank (useful in tab for example)
	 */
	String getShortName();

}
