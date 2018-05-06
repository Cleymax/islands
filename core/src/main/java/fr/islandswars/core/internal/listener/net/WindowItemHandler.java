package fr.islandswars.core.internal.listener.net;

import fr.islandswars.api.net.PacketEvent;
import fr.islandswars.api.net.PacketHandler;
import fr.islandswars.api.net.PacketType;
import fr.islandswars.api.net.packet.play.server.WindowItemsPacket;
import fr.islandswars.core.IslandsCore;
import fr.islandswars.core.bukkit.storage.StorageFactory;

/**
 * File <b>WindowItemhandler</b> located on fr.islandswars.core.internal.listener.net
 * WindowItemhandler is a part of Islands Wars - Api.
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
 * Created the 26/04/2018 at 09:21
 * @since 0.2.9
 */
public class WindowItemHandler extends PacketHandler<WindowItemsPacket> {

	private final StorageFactory storageManager;

	public WindowItemHandler(IslandsCore core) {
		super(PacketType.Play.Server.WINDOW_ITEMS);
		this.storageManager = (StorageFactory) core.getStorageManager();
	}

	@Override
	public void handlePacket(PacketEvent<WindowItemsPacket> event) {
		var items  = event.getPacket().getItemStacks();
		var locale = event.getPlayer().getPlayerLocale();
		items.forEach(item -> {
			if (item == null || !item.hasTag() || !item.getTag().hasKeyOfType("id", 3))
				return;
			storageManager.translateItem(item, event.getPlayer());
		});
	}
}

