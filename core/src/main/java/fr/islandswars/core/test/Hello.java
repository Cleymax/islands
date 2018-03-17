package fr.islandswars.core.test;

import fr.islandswars.api.cmd.lang.Command;
import fr.islandswars.api.cmd.lang.CommandExecutor;
import fr.islandswars.api.cmd.lang.Compound;
import fr.islandswars.api.player.IslandsPlayer;
import org.bukkit.command.CommandSender;

/**
 * File <b>Hello</b> located on fr.islandswars.core.test
 * Hello is a part of Islands Wars - Api.
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
 * Created the 17/03/2018 at 17:22
 * @since 0.2.9
 */
@Command(aliases = "h")
public class Hello {

	//Call if args.length == 0
	@CommandExecutor
	public static void simple(CommandSender sender) {
		sender.sendMessage("Type /hello");
	}

	@Compound()
	public static void world(CommandSender sender) {
		sender.sendMessage("Type /hello world");
	}

}
