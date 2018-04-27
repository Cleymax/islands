package fr.islandswars.core.internal.listener;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.listener.LazyListener;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.core.bukkit.item.InternalItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * File <b>ItemListener</b> located on fr.islandswars.core.internal.listener
 * ItemListener is a part of Islands Wars - Api.
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
 * Created the 26/04/2018 at 09:28
 * @since 0.2.9
 */
public class ItemListener extends LazyListener {

	public ItemListener(IslandsApi api) {
		super(api);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getWhoClicked() == null || !(event.getWhoClicked() instanceof Player) || event.getCurrentItem() == null)
			return;

		IslandsPlayer player = getFromPlayer((Player) event.getWhoClicked());
		IslandsApi.getInstance().getStorageManager().getItem(event.getCurrentItem()).ifPresent(item -> ((InternalItem) item).callEvent(player, event));
	}
}
