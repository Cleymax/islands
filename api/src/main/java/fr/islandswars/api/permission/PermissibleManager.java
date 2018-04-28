package fr.islandswars.api.permission;

import fr.islandswars.api.player.IslandsPlayer;

/**
 * File <b>PermissibleManager</b> located on fr.islandswars.api.permission
 * PermissibleManager is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 11:52
 * @since 0.2.6
 * <p>
 * Are the player allowed to perform or not
 */
public interface PermissibleManager {

    /**
     * Is the given player belong to the staff crew ?
     *
     * @param player a player to test
     * @return true or not
     */
    boolean isStaff(IslandsPlayer player);

    /**
     * Is the given player paid for something ? (VIP access or something like that) ?
     *
     * @param player a player to test
     * @return if true or not
     */
    boolean hasPayingRank(IslandsPlayer player);

    /**
     * Is the given player admin (simply check if name == Xharos || name == Vinetos of course) ?
     *
     * @param player a player to test
     * @return if true or not
     */
    boolean isAdmin(IslandsPlayer player);

    /**
     * Test weither or not this player is allowed to do this action
     *
     * @param player a player to test
     * @param action an action to do
     * @return if allowed or not
     */
    boolean isAllowed(IslandsPlayer player, Action action);
}
