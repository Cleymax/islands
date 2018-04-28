package fr.islandswars.core.bukkit.command;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.cmd.CommandManager;
import fr.islandswars.api.cmd.lang.Command;
import fr.islandswars.api.utils.NMSReflectionUtil;
import fr.islandswars.core.bukkit.command.wrapper.CommandWrapper;
import fr.islandswars.core.bukkit.command.wrapper.LabelDispatcher;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * File <b>BukkitCommandInjector</b> located on fr.islandswars.core.bukkit.command
 * BukkitCommandInjector is a part of Islands Wars - Api.
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
 * Created the 16/03/2018 at 23:50
 * @since 0.2.9
 */
public class BukkitCommandInjector implements CommandManager {

    private static final NMSReflectionUtil.ConstructorAccessor<PluginCommand> PLUGIN_COMMAND_CONSTRUCTOR;
    private static final Map<PluginCommand, LabelDispatcher> BUKKIT_COMMANDS;
    private static final CommandExecutor COMMAND_EXECUTOR;
    private static final CommandMap COMMAND_MAP;

    static {
        COMMAND_MAP = (CommandMap) NMSReflectionUtil.getFieldAccessor(SimplePluginManager.class, "commandMap").get(Bukkit.getPluginManager());
        PLUGIN_COMMAND_CONSTRUCTOR = NMSReflectionUtil.getConstructorAccessor(PluginCommand.class, String.class, Plugin.class);
        COMMAND_EXECUTOR = BukkitCommandInjector::dispatchCommand;
        BUKKIT_COMMANDS = new HashMap<>();
    }

    private static boolean dispatchCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(command instanceof PluginCommand))
            throw new IllegalArgumentException("Cannot find command " + command);

        LabelDispatcher wrapper = BUKKIT_COMMANDS.get(command);

        if (wrapper == null)
            throw new IllegalArgumentException("Cannot find command " + command);
        try {
            wrapper.dispatch(sender, args, 0);
            return true;
        } catch (InvocationTargetException e) {
            throw new CommandDispatchException("Error while dispatching command " + command, e.getCause());
        } catch (ReflectiveOperationException e) {
            throw new CommandDispatchException("Error while dispatching command " + command, e);
        }
    }

    @Override
    public void registerCommand(Class<?> commandClass) throws Exception {
        Command command = commandClass.getAnnotation(Command.class);

        if (command != null) {
            String label = getlabel(command, commandClass);
            LabelDispatcher wrapper = new CommandWrapper(commandClass, command);
            register(wrapper);
        }
    }

    private String getlabel(Command command, Class<?> commandClass) {
        if (command.label().isEmpty())
            return commandClass.getSimpleName().toLowerCase();
        else return command.label().toLowerCase();
    }

    private void register(LabelDispatcher wrapper) {
        PluginCommand command = PLUGIN_COMMAND_CONSTRUCTOR.newInstance(wrapper.getLabel(), IslandsApi.getInstance());
        command.setAliases(Arrays.asList(wrapper.getAliases()));
        command.setExecutor(COMMAND_EXECUTOR);
        //command.setTabCompleter(null);
        COMMAND_MAP.register(IslandsApi.getInstance().getDescription().getName(), command);
        BUKKIT_COMMANDS.put(command, wrapper);
    }

}
