package fr.islandswars.core.bukkit.command.wrapper;

import fr.islandswars.api.cmd.lang.Command;
import fr.islandswars.api.cmd.lang.CommandExecutor;
import fr.islandswars.api.cmd.lang.Compound;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

/**
 * File <b>FullCommandWrapper</b> located on fr.islandswars.core.bukkit.command.wrapper
 * FullCommandWrapper is a part of Islands Wars - Api.
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
 * @author SkybeastMC
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 17/03/2018 at 17:07
 * @since 0.2.9
 */
public class FullCommandWrapper extends CommandWrapper {

	private final Map<String, CommandWrapper> compounds;
	private       Method                      defaultExecutor;

	public FullCommandWrapper(Class<?> commandClass, Command command) {
		super(command.label().isEmpty() ? commandClass.getSimpleName().toLowerCase() : command.label(), command.aliases());
		this.compounds = new HashMap<>();
		iterate(commandClass.getMethods());
	}

	@Override
	public boolean dispatch(CommandSender player, String[] cmd, int count) throws ReflectiveOperationException {
		if (cmd.length == 0 && defaultExecutor != null) {
			defaultExecutor.invoke(null, player);
			return true;
		} else {
			String         label   = cmd[count];
			CommandWrapper wrapper = compounds.get(label);
			if (wrapper != null)
				wrapper.dispatch(player, cmd, ++count);
			else {
				//show help
				player.sendMessage("Show help here");
			}
			return true;
		}

	}

	private void addCompound(MethodCommandWrapper wrapper) {
		if (compounds.keySet().stream().anyMatch(label -> label.equals(wrapper.getLabel())))
			throw new CommandException("Try to register method that uses already registered label!");
		else
			compounds.put(wrapper.getLabel(), wrapper);
	}

	private void invoke() {

	}

	private void iterate(Method[] methods) {
		for (Method method : methods) {
			//Default executor
			if (method.getAnnotation(CommandExecutor.class) != null) {
				if (defaultExecutor != null)
					throw new CommandException("Cannot have two CommandExecutor per command");

				if (method.getParameterCount() != 1)
					throw new CommandException("A CommandExecutor should only have one argument");

				defaultExecutor = method;
				if (!CommandSender.class.isAssignableFrom(method.getParameterTypes()[0]))
					throw new CommandException("Command sender type " + defaultExecutor.getParameterTypes()[0] + " isn't an IslandsPlayer");
				continue;
			}

			//Sub command
			Compound compound = method.getAnnotation(Compound.class);
			if (compound == null)
				continue;

			addCompound(new MethodCommandWrapper(method, compound));
		}
	}

}
