package fr.islandswars.core.bukkit.command.wrapper;

import org.bukkit.command.CommandSender;

/**
 * File <b>LabelDispatcher</b> located on fr.islandswars.core.bukkit.command.wrapper
 * LabelDispatcher is a part of Islands Wars - Api.
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
 * Created the 17/03/2018 at 00:08
 * @since 0.2.9
 */
public abstract class LabelDispatcher {

	private final String[] aliases;
	private       String   label;
	String help, description;

	LabelDispatcher(String label, String[] aliases) {
		this.aliases = aliases;
		this.label = label;
	}

	public abstract void dispatch(CommandSender player, String[] args, int count) throws ReflectiveOperationException;

	public String[] getAliases() {
		return aliases;
	}

	public String getHelp() {
		return help;
	}

	public String getLabel() {
		return label;
	}
}
