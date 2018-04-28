package fr.islandswars.core.bukkit.command;

/**
 * File <b>CommandDispatchException</b> located on fr.islandswars.core.bukkit.command
 * CommandDispatchException is a part of Islands Wars - Api.
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
 * @author SkyBeastMC
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 17/03/2018 at 17:04
 * @since 0.2.9
 */
final class CommandDispatchException extends RuntimeException {

    CommandDispatchException() {
    }

    CommandDispatchException(String var1) {
        super(var1);
    }

    CommandDispatchException(String var1, Throwable var2) {
        super(var1, var2);
    }

    CommandDispatchException(Throwable var1) {
        super(var1);
    }
}
