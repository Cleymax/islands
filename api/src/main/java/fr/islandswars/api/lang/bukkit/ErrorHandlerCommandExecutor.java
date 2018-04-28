package fr.islandswars.api.lang.bukkit;

import fr.islandswars.api.utils.ErrorHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * File <b>ErrorHandlerCommandExecutor</b> located on fr.islandswars.api.lang.bukkit
 * ErrorHandlerCommandExecutor is a part of Islands Wars - Api.
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
 * @author DeltaEvo
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 10/04/2018 at 16:48
 * @since 0.2.9
 */
public class ErrorHandlerCommandExecutor implements CommandExecutor {

    private final CommandExecutor owner;
    private final ErrorHandler handler;

    public ErrorHandlerCommandExecutor(CommandExecutor owner, ErrorHandler handler) {
        this.owner = owner;
        this.handler = handler;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            return owner.onCommand(commandSender, command, s, strings);
        } catch (Throwable t) {
            handler.handle(t);
            return false;
        }
    }
}
