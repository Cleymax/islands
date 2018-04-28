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
import fr.islandswars.api.storage.StorageManager;
import fr.islandswars.api.task.UpdaterManager;
import fr.islandswars.api.utils.NMSReflectionUtil;
import fr.islandswars.core.bukkit.bossbar.BukkitBarManager;
import fr.islandswars.core.bukkit.command.BukkitCommandInjector;
import fr.islandswars.core.bukkit.net.PacketHandlerManager;
import fr.islandswars.core.bukkit.net.PacketInterceptor;
import fr.islandswars.core.bukkit.storage.StorageFactory;
import fr.islandswars.core.bukkit.task.TaskManager;
import fr.islandswars.core.internal.command.PingCommand;
import fr.islandswars.core.internal.i18n.LocaleTranslatable;
import fr.islandswars.core.internal.listener.ItemListener;
import fr.islandswars.core.internal.listener.PlayerListener;
import fr.islandswars.core.internal.listener.net.SetSlotHandler;
import fr.islandswars.core.internal.listener.net.WindowItemHandler;
import fr.islandswars.core.internal.log.InternalLogger;
import fr.islandswars.core.player.InternalPlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;

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

    private final BukkitCommandInjector commandmanager;
    private final UpdaterManager updaterManager;
    private final StorageFactory storageManager;
    private final PacketHandlerManager packetManager;
    private final LocaleTranslatable translatable;
    private final CopyOnWriteArrayList<IslandsPlayer> players;
    private final List<Module> modules;
    private final InternalLogger logger;
    private final BukkitBarManager barManager;

    public IslandsCore() {
        this.logger = new InternalLogger();
        this.commandmanager = new BukkitCommandInjector();
        this.packetManager = new PacketHandlerManager();
        this.translatable = new LocaleTranslatable();
        this.players = new CopyOnWriteArrayList<>();
        this.storageManager = new StorageFactory();
        this.updaterManager = new TaskManager();
        this.modules = new ArrayList<>();

        this.barManager = registerModule(BukkitBarManager.class);
    }

    public void addPlayer(Player p) {
        players.add(new InternalPlayer(p));
    }

    @Override
    public BarManager getBarManager() {
        return barManager;
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
        return packetManager;
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
    public StorageManager getStorageManager() {
        return storageManager;
    }

    @Override
    public Translatable getTranslatable() {
        return translatable;
    }

    @Override
    public UpdaterManager getUpdaterManager() {
        return updaterManager;
    }

    @Override
    public void onLoad() {
        getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Loading server...").setServer(new Server(Status.LOAD, ServerType.HUB)).log();

        translatable.getLoader().registerCustomProperties(this);
        modules.forEach(Module::onLoad);
    }

    @Override
    public void onDisable() {
        getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Disabling server...").setServer(new Server(Status.DISABLE, ServerType.HUB)).log();

        modules.forEach(Module::onDisable);
        PacketInterceptor.clean();
    }

    @Override
    public void onEnable() {
        getInfraLogger().createCustomLog(ServerLog.class, Level.INFO, "Enable server in %s ms.").setServer(new Server(Status.ENABLE, ServerType.HUB)).log();
        PacketInterceptor.inject();
        modules.forEach(Module::onEnable);
        try {
            getCommandManager().registerCommand(PingCommand.class);

            new PlayerListener(this);
            new ItemListener(this);

            getProtocolManager().subscribeHandler(new SetSlotHandler(this));
            getProtocolManager().subscribeHandler(new WindowItemHandler(this));
        } catch (Exception e) {
            getInfraLogger().logError(e);
        }
    }

    @Override
    public <T extends Module> T registerModule(Class<T> module) {
        try {
            T mod = NMSReflectionUtil.getConstructorAccessor(module, IslandsApi.class).newInstance(IslandsApi.getInstance());
            if (isEnabled()) {
                mod.onLoad();
                mod.onEnable();
            }
            modules.add(mod);
            return mod;
        } catch (Exception e) {
            getInfraLogger().logError(e);
            return null;
        }

    }

    @Override
    public <T extends Module> void unregisterModule(Class<T> module) {
        Optional<? extends Module> optionalModule = modules.stream().filter(m -> m.getClass().equals(module)).findFirst();
        optionalModule.ifPresent(mod -> {
            mod.onDisable();
            modules.remove(mod);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Module> Optional<T> getModule(Class<T> module) {
        return (Optional<T>) modules.stream().filter(m -> m.getClass().equals(module)).findFirst();
    }

    public void removePlayer(IslandsPlayer player) {
        player.disconnect();
        players.remove(player);
    }
}
