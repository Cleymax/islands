package fr.islandswars.test.bukkit;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarProperties;
import fr.islandswars.api.player.IslandsPlayer;
import net.minecraft.server.v1_12_R1.BossBattle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * File <b>BossBarTest</b> located on fr.islandswars.test.bukkit
 * BossBarTest is a part of Islands Wars - Api.
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
 * Created the 11/01/2018 at 19:06
 */
public class BossBarTest {

	private Bar welcome;

	public BossBarTest() {
		//hub.bar.welcome = "Welcome %s on Islands - Wars"
		this.welcome = IslandsApi.getInstance().getBarManager().createBar("hub.bar.welcome", BossBattle.BarColor.BLUE, BossBattle.BarStyle.NOTCHED_6);
		welcome.provideProperties(BarProperties.builder(-1, -1).withMagicColor(true), true);
	}

	@EventHandler
	public void onSendMessage(AsyncPlayerChatEvent event) {
		if (event.getMessage() != null && event.getMessage().contains("welcome")) {
			IslandsPlayer player = IslandsApi.getInstance().getPlayer(event.getPlayer().getUniqueId());
			player.displaybar(welcome, () -> new Object[]{player.getCraftPlayer().getName()});
		} else if (event.getMessage() != null && event.getMessage().contains("bye")) {
			IslandsPlayer player = IslandsApi.getInstance().getPlayer(event.getPlayer().getUniqueId());
			player.hideBar(welcome);
		}
	}
}
