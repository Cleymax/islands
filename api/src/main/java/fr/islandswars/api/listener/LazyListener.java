package fr.islandswars.api.listener;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.player.IslandsPlayer;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * File <b>LazyListener</b> located on fr.islandswars.api.listener
 * LazyListener is a part of Islands Wars - Api.
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
 * Created the 03/01/2018 at 16:22
 * @since 0.2.4.2
 * <p>
 * Auto registered listener
 */
public abstract class LazyListener implements Listener {

	protected final IslandsApi api;

	public LazyListener(IslandsApi api) {
		this.api = api;
		api.registerEvent(this);
	}

	/**
	 * Get this IslandsPlayer (if exist) from a Bukkit player (via {@link IslandsApi#getPlayer(UUID)}
	 *
	 * @param player a bukkit player
	 * @return an IslandsPlayer if uuid matches
	 */
	protected IslandsPlayer getFromPlayer(Player player) {
		return api.getPlayer(player.getUniqueId()).orElseThrow(NullPointerException::new);
	}
}
