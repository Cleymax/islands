package fr.islandswars.api;

import fr.islandswars.api.bossbar.BarManager;
import fr.islandswars.api.cmd.CommandManager;
import fr.islandswars.api.i18n.I18nLoader;
import fr.islandswars.api.i18n.Translatable;
import fr.islandswars.api.infra.ServiceManager;
import fr.islandswars.api.item.ItemManager;
import fr.islandswars.api.lang.bukkit.ErrorHandlerCommandExecutor;
import fr.islandswars.api.lang.bukkit.ErrorHandlerRegisteredListener;
import fr.islandswars.api.lang.bukkit.ErrorHandlerRunnable;
import fr.islandswars.api.log.InfraLogger;
import fr.islandswars.api.log.internal.ErrorLog;
import fr.islandswars.api.module.ModuleManager;
import fr.islandswars.api.net.ProtocolManager;
import fr.islandswars.api.permission.PermissibleManager;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.scoreboard.ScoreboardManager;
import fr.islandswars.api.server.ServerType;
import fr.islandswars.api.storage.StorageManager;
import fr.islandswars.api.task.UpdaterManager;
import fr.islandswars.api.utils.ErrorHandler;
import fr.islandswars.api.utils.Preconditions;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * File <b>IslandsApi</b> located on fr.islandswars.api
 * IslandsApi is a part of Islands Wars - Api.
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
 * @author Valentin Chassignol (Vinetos), {@literal <vinetos@islandswars.fr>}
 * Created the 24/09/2017 at 11:52
 * @since 0.1
 * <p>
 * Represent the main entry of this bukkit api, give access to each manager
 */
public abstract class IslandsApi extends JavaPlugin implements ModuleManager {

	private static IslandsApi   instance;
	private final  ErrorHandler handler;
	private final ServerType type     = null;
	private final int        serverId = 0;

	protected IslandsApi() {
		if (instance == null)
			instance = this;
		handler = (t) -> getInfraLogger().createCustomLog(ErrorLog.class, Level.SEVERE, (t.getMessage() == null || t.getMessage().isEmpty()) ? "Error thrown" : t.getMessage()).supplyStacktrace(t).log();

		//TODO this.type = ServerType.valueOf(System.getenv("SERVER_TYPE"));
		//this.serverId = Integer.valueOf(System.getenv("SERVER_ID"));
	}

	/**
	 * @return this API unique instance
	 */
	public static IslandsApi getInstance() {
		return instance;
	}

	/**
	 * Interface to easily create complex boss bossbar pattern
	 *
	 * @return an abstract nms layer to easily deal with boss bossbar packet
	 */
	public abstract BarManager getBarManager();

	/**
	 * Method invocation based command pattern
	 *
	 * @return a manager to register custom command compound
	 */
	public abstract CommandManager getCommandManager();

	/**
	 * An enum representing the server, and some static properties
	 *
	 * @return the running server type
	 */
	public abstract ServerType getCurrentServerType();

	/**
	 * Interface to register and instanciates translation
	 *
	 * @return a String storage according to i18n format
	 */
	public abstract I18nLoader getI18nLoader();

	/**
	 * @return an abstract logger to output custom log
	 */
	public abstract InfraLogger getInfraLogger();

	/**
	 * Create item layer to deal with translation
	 *
	 * @return an ItemStack factory
	 */
	public abstract ItemManager getItemManager();

	/**
	 * See if player's ranks match conditions
	 *
	 * @return a way to check {@link IslandsPlayer#getAllRanks()}
	 */
	public abstract PermissibleManager getPermissionManager();

	/**
	 * Retrieve an IslandsPlayer
	 *
	 * @param playerId this playerId
	 * @return a wrapped player if online, or else null
	 */
	public abstract Optional<IslandsPlayer> getPlayer(UUID playerId);

	/**
	 * Retrieve all IslandsPlayer
	 *
	 * @return all connected players
	 */
	public abstract List<? extends IslandsPlayer> getPlayers();

	/**
	 * Retrieve all IslandsPlayer matching the predicate
	 *
	 * @param predicate a filter predicate
	 * @return all connected players
	 */
	public abstract List<IslandsPlayer> getPlayers(Predicate<IslandsPlayer> predicate);

	/**
	 * Interface to easily listen and write for in/outcoming packet
	 *
	 * @return a protocol manager for this minecraft server version
	 */
	public abstract ProtocolManager getProtocolManager();

	/**
	 * Interface to manager all scoreboard components easily
	 *
	 * @return an abstract nms layer to easily deal with scoreboard's packets
	 */
	public abstract ScoreboardManager getScoreboardManager();

	/**
	 * @return this server infra identifier
	 */
	public String getServerIdentifier() {
		return type.getTypeName() + "-" + serverId;
	}

	/**
	 * Interface to deal with restricted access to a service
	 *
	 * @return an interface to interact with redis / rabbitmq
	 */
	public abstract ServiceManager getServiceManager();

	/**
	 * Interface to create wrapped inventory to deal with translations
	 *
	 * @return a storage factory
	 */
	public abstract StorageManager getStorageManager();

	/**
	 * Interface to format key to a valid String, according to user preferences
	 *
	 * @return a way to format message according to a given language
	 */
	public abstract Translatable getTranslatable();

	/**
	 * @return this server type
	 */
	public ServerType getType() {
		return type;
	}

	/**
	 * Interface to register and schedule task from method annotations
	 *
	 * @return an interface to register and run task
	 */
	public abstract UpdaterManager getUpdaterManager();

	@Override
	public abstract void onLoad();

	@Override
	public abstract void onDisable();

	@Override
	public abstract void onEnable();

	public void registerEvent(Listener listener) {
		Preconditions.checkNotNull(listener);

		getPluginLoader().createRegisteredListeners(listener, this).forEach((e, l) -> getHandlerList(e).registerAll(l.stream().map(r -> new ErrorHandlerRegisteredListener(r, handler)).collect(Collectors.toList())));
	}

	public BukkitTask runTask(Runnable task) {
		return Bukkit.getScheduler().runTask(this, new ErrorHandlerRunnable(task, handler));
	}

	public BukkitTask runTaskAsynchronously(Runnable task) {
		return Bukkit.getScheduler().runTaskAsynchronously(this, new ErrorHandlerRunnable(task, handler));
	}

	public void setExecutor(PluginCommand command, CommandExecutor executor) {
		command.setExecutor(new ErrorHandlerCommandExecutor(executor, handler));
	}

	private HandlerList getHandlerList(Class<? extends Event> event) {
		while (event.getSuperclass() != null && Event.class.isAssignableFrom(event.getSuperclass())) {
			try {
				Method method = event.getDeclaredMethod("getHandlerList");
				method.setAccessible(true);
				return (HandlerList) method.invoke(null);
			} catch (ReflectiveOperationException e) {
				event = event.getSuperclass().asSubclass(Event.class);
			}
		}
		throw new IllegalStateException("No HandlerList for " + event.getName());
	}

}
