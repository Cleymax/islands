package fr.islandswars.api.log.internal;

import fr.islandswars.api.log.Log;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.utils.Preconditions;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * File <b>PlayerLog</b> located on fr.islandswars.api.log.internal
 * PlayerLog is a part of Islands Wars - Api.
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 10/03/2018 at 16:18
 * @since 0.2.9
 * <p>
 * Represent a player action log
 */
public class PlayerLog extends Log {

	protected PlayerAction action;

	public PlayerLog(String level, String msg) {
		super(level, msg);
	}

	@Override
	public void checkValue() {
		Preconditions.checkNotNull(action);
	}

	public PlayerLog setPlayer(IslandsPlayer player, Action action) {
		return setPlayer(player.getCraftPlayer(), action);
	}

	public PlayerLog setPlayer(Player player, Action action) {
		return setPlayer(player.getName(), player.getUniqueId(), action);
	}

	public PlayerLog setPlayer(String name, UUID id, Action action) {
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(id);
		Preconditions.checkNotNull(action);

		this.action = new PlayerAction(name, action, id);
		return this;
	}


}
