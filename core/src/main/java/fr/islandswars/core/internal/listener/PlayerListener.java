package fr.islandswars.core.internal.listener;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.listener.LazyListener;
import fr.islandswars.api.log.internal.Action;
import fr.islandswars.api.log.internal.PlayerLog;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageBuilder;
import fr.islandswars.core.IslandsCore;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

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
		this.storage = api.getStorageManager().newStorage(StorageBuilder.build("Test", 9 * 1));
		storage.addItem(api.getItemManager().createItem(new ItemType(Material.BEACON)));
	}

	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		((IslandsCore) api).addPlayer(p);
		api.getInfraLogger().createCustomLog(PlayerLog.class, Level.INFO, "Player " + p.getName() + " joined the game.").setPlayer(p, Action.CONNECT).log();
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		api.getStorageManager().openStorage(getFromPlayer(event.getPlayer()), storage);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		IslandsPlayer player = getFromPlayer(event.getPlayer());
		((IslandsCore) api).removePlayer(player);
		api.getInfraLogger().createCustomLog(PlayerLog.class, Level.INFO, "Player " + player.getCraftPlayer().getName() + " leaved the game.").setPlayer(player, Action.LEAVE).log();
	}
}
