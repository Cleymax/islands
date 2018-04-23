package fr.islandswars.core;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.BarManager;
import fr.islandswars.api.cmd.CommandManager;
import fr.islandswars.api.i18n.I18nLoader;
import fr.islandswars.api.i18n.Translatable;
import fr.islandswars.api.infra.ServiceManager;
import fr.islandswars.api.log.InfraLogger;
import fr.islandswars.api.log.internal.Server;
import fr.islandswars.api.log.internal.ServerLog;
import fr.islandswars.api.log.internal.Status;
import fr.islandswars.api.module.Module;
import fr.islandswars.api.net.ProtocolManager;
import fr.islandswars.api.permission.PermissibleManager;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.scoreboard.ScoreboardManager;
import fr.islandswars.api.server.ServerType;
import fr.islandswars.api.task.UpdaterManager;
import fr.islandswars.core.bukkit.command.BukkitCommandInjector;
import fr.islandswars.core.internal.command.PingCommand;
import fr.islandswars.core.internal.i18n.LocaleTranslatable;
import fr.islandswars.core.internal.listener.PlayerListener;
import fr.islandswars.core.internal.log.InternalLogger;
import fr.islandswars.core.player.InternalPlayer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.bukkit.entity.Player;

/**
 * File <b>IslandsCore</b> located on fr.islandswars.core
 * IslandsCore is a part of Islands Wars - Api.
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
 * Created the 06/03/2018 at 13:16
 * @since 0.0.1
 */
public class IslandsCore extends IslandsApi {

	private final InternalLogger                      logger;
	private final BukkitCommandInjector               commandmanager;
	private final LocaleTranslatable                  translatable;
	private final CopyOnWriteArrayList<IslandsPlayer> players;

	public IslandsCore() {
		this.logger = new InternalLogger();
		this.commandmanager = new BukkitCommandInjector();
		this.translatable = new LocaleTranslatable();
		this.players = new CopyOnWriteArrayList<>();
	}

	public void addPlayer(Player p) {
		players.add(new InternalPlayer(p));
	}

	@Override
	public BarManager getBarManager() {
		return null;
	}

	@Override
	public CommandManager getCommandManager() {
		return commandmanager;
	}

	@Override
	public ServerType getCurrentServerType() {
		return null;
	}

	@Override
	public I18nLoader getI18nLoader() {
		return translatable.getLoader();
	}

	@Override
	public InfraLogger getInfraLogger() {
		return logger;
	}

	@Override
	public PermissibleManager getPermissionManager() {
		return null;
	}

	@Override
	public Optional<IslandsPlayer> getPlayer(UUID playerId) {
		return players.stream().filter(p -> p.getCraftPlayer().getUniqueId().equals(playerId)).findFirst();
	}

	@Override
	public List<? extends IslandsPlayer> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	@Override
	public List<IslandsPlayer> getPlayers(Predicate<IslandsPlayer> predicate) {
		return Collections.unmodifiableList(players.stream().filter(predicate).collect(Collectors.toList()));
	}

	@Override
	public ProtocolManager getProtocolManager() {
		return null;
	}

	@Override
	public ScoreboardManager getScoreboardManager() {
		return null;
	}

	@Override
	public ServiceManager getServiceManager() {
		return null;
	}

	@Override
	public Translatable getTranslatable() {
		return translatable;
	}

	@Override
	public UpdaterManager getUpdaterManager() {
		return null;
	}

	@Override
	public void onLoad() {
		getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Loading server...").setServer(new Server(Status.LOAD, ServerType.HUB)).log();
		translatable.getLoader().registerCustomProperties(this);
	}

	@Override
	public void onDisable() {
		getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Disabling server...").setServer(new Server(Status.DISABLE, ServerType.HUB)).log();
	}

	@Override
	public void onEnable() {
		getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Enable server in %s ms.").setServer(new Server(Status.ENABLE, ServerType.HUB)).log();
		try {
			getCommandManager().registerCommand(PingCommand.class);

			new PlayerListener(this);
		} catch (Exception e) {
			getInfraLogger().logError(e);
		}
	}

	@Override
	public <T extends Module> T registerModule(Class<T> module) {
		return null;
	}

	@Override
	public <T extends Module> void unregisterModule(Class<T> module) {

	}

	@Override
	public <T extends Module> Optional<T> getModule(Class<T> module) {
		return Optional.empty();
	}

	public void removePlayer(Player p) {
		IslandsPlayer player = getPlayer(p.getUniqueId()).orElseThrow(() -> new NullPointerException("Given player is null"));
		players.remove(player);
	}
}
