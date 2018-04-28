package fr.islandswars.api.cmd;

/**
 * File <b>CommandManager</b> located on fr.islandswars.api.cmd
 * CommandManager is a part of Islands Wars - Api.
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
 * Created the 16/03/2018 at 23:06
 * @since 0.2.9
 * <p>
 * Register custom command
 */
public interface CommandManager {

    /**
     * Attempt to inject this command into bukkit command map.
     *
     * @param commandClass a class that use {@link fr.islandswars.api.cmd.lang.Command} interface at least
     * @throws Exception if this class misses some annotations, or if some arguments are already registered
     */
    void registerCommand(Class<?> commandClass) throws Exception;

}
