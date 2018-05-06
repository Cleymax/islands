package fr.islandswars.core.bukkit.command.wrapper;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.cmd.lang.Command;
import fr.islandswars.api.cmd.lang.CommandExecutor;
import fr.islandswars.api.cmd.lang.Compound;
import java.lang.reflect.Method;
import java.util.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

/**
 * File <b>CommandWrapper</b> located on fr.islandswars.core.bukkit.command.wrapper
 * CommandWrapper is a part of Islands Wars - Api.
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
 * Created the 27/03/2018 at 17:15
 * @since 0.2.9
 * <p>
 * Contains everything related to a certains command
 */
public class CommandWrapper extends LabelDispatcher {

	private final Map<List<String>, MethodCommandWrapper> compounds;
	private final String                                  description;
	private       Method                                  defaultExecutor;

	public CommandWrapper(Class<?> commandClass, Command command) {
		super(command.label().isEmpty() ? commandClass.getSimpleName().toLowerCase() : command.label(), command.aliases());
		this.description = command.description();
		this.compounds = new HashMap<>();
		searchCompounds(commandClass.getMethods());
		this.help = defaultExecutor == null ? null : " - type /" + getLabel() + " to trigger the default command";
	}

	@Override
	public void dispatch(CommandSender player, String[] args, int count) throws ReflectiveOperationException {
		if (args.length == 0)
			if (defaultExecutor != null)
				defaultExecutor.invoke(null, player);
			else
				showHelp(player);
		else {
			//There is at least one argument, so we can delegate to a compound
			String               compound = args[count];
			MethodCommandWrapper wrapper  = getCompound(compound);
			if (wrapper == null)
				showHelp(player);
			else wrapper.dispatch(player, args, ++count);
		}
	}

	private void addCompound(MethodCommandWrapper wrapper) {
		var compoundAliases = new ArrayList<String>(Arrays.asList(wrapper.getAliases()));
		compoundAliases.add(wrapper.getLabel());

		for (var compoundAlias : compoundAliases) {
			if (compounds.keySet().stream().anyMatch(aliases -> aliases.contains(compoundAlias)))
				throw new CommandException("Given aliases for method " + wrapper.getLabel() + " are already bind!");
		}
		compounds.put(compoundAliases, wrapper);
	}

	private MethodCommandWrapper getCompound(String label) {
		//TODO doesn't work
		var compoundAliases = compounds.keySet().stream().filter(aliases -> aliases.contains(label)).findFirst().orElse(null);
		if (compoundAliases != null)
			return compounds.get(compoundAliases);
		else return null;
	}

	private void searchCompounds(Method[] methods) {
		for (var method : methods) {
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
			Compound compound = method.getAnnotation(Compound.class);
			if (compound == null)
				continue;

			addCompound(new MethodCommandWrapper(getLabel(), method, compound));
		}
	}

	private void showHelp(CommandSender sender) {
		String help = getHelp();

		if (!description.isEmpty())
			sender.sendMessage(IslandsApi.getInstance().getTranslatable().format(description));
		else
			sender.sendMessage(IslandsApi.getInstance().getTranslatable().format("core.command.usage", getLabel()));

		if (help != null)
			sender.sendMessage(help);
		compounds.values().forEach(wrapper -> {
			sender.sendMessage(" - " + wrapper.getHelp());
		});
		sender.sendMessage("§8|§7--------------------------------------");
	}
}
