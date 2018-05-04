package fr.islandswars.core.internal.listener;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.listener.LazyListener;
import fr.islandswars.api.log.internal.Action;
import fr.islandswars.api.log.internal.PlayerLog;
import fr.islandswars.api.player.ChatType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageBuilder;
import fr.islandswars.api.storage.StorageType;
import fr.islandswars.core.IslandsCore;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.logging.Level;

/**
 * File <b>PlayerListener</b> located on fr.islandswars.core.internal.listener
 * PlayerListener is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 15:35
 * @since 0.2.9
 */
public class PlayerListener extends LazyListener {

    private Storage storage;

    public PlayerListener(IslandsApi api) {
        super(api);
        Item glass = api.getStorageManager().newItem(new ItemType(Material.STAINED_GLASS_PANE, (byte) 9));
        api.getInfraLogger().createDefaultLog("Is item null ? " + (glass == null));
        StorageBuilder builder = StorageBuilder.build("core.inventory.global.name", 6 * 9, StorageType.GLOBAL).withPattern(
                "CCCCCCCCC" +
                        "C       C" +
                        "C       C" +
                        "C       C" +
                        "C       C" +
                        "CCCCCCCCC").supplyPattern('C', glass);
        this.storage = api.getStorageManager().createStorage(builder);
        storage.setItem(4, 8, storage.newItem(new ItemType(Material.SKULL_ITEM, (byte) SkullType.PLAYER.ordinal())).onClick((player, event) -> {
            event.setCancelled(true);
            player.sendTranslatedMessage(ChatType.CHAT, "coucou toi");
        }), null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onConnect(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        ((IslandsCore) api).addPlayer(p);
        api.getInfraLogger().createCustomLog(PlayerLog.class, Level.INFO, "Player " + p.getName() + " joined the game.").setPlayer(p, Action.CONNECT).log();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent event) {
        IslandsPlayer player = getFromPlayer(event.getPlayer());
        ((IslandsCore) api).removePlayer(player);
        api.getInfraLogger().createCustomLog(PlayerLog.class, Level.INFO, "Player " + player.getCraftPlayer().getName() + " leaved the game.").setPlayer(player, Action.LEAVE).log();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSneak(PlayerToggleSneakEvent event) {
        getFromPlayer(event.getPlayer()).openStorage(storage);
    }
}
