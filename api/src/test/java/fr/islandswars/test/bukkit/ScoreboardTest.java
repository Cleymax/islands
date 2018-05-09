package fr.islandswars.test.bukkit;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.scoreboard.Scoreboard;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.v1_12_R1.BossBattle;

/**
 * File <b>ScoreboardTest</b> located on fr.islandswars.test.bukkit
 * ScoreboardTest is a part of Islands Wars - Api.
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
 * Created the 10/01/2018 at 18:35
 */
public class ScoreboardTest {

	private int        playerCount; //this var can be updated at any moment, the change will be displayed and send to viewers
	private Scoreboard scoreboard;

	public void init() {
		scoreboard = IslandsApi.getInstance().getScoreboardManager().createScoreboard("Islands - Wars", Scoreboard.ScoreboardSlot.SIDEBAR);
		build();
	}

	public void openTo(IslandsPlayer player) {
		Bar                  bar               = IslandsApi.getInstance().getBarManager().createBar("Bar text", BossBattle.BarColor.BLUE, BossBattle.BarStyle.NOTCHED_20);
		Map<Integer, Object> personalInstance = new HashMap<>();
		personalInstance.put(2, player);
		scoreboard.addPlayer(player, personalInstance);
	}

	private void build() {
		scoreboard.addLine("------ Basic text line -----");
		scoreboard.addGlobalI18nLine("hub.test.scoreboard.line2", () -> new Object[]{1});
		scoreboard.<IslandsPlayer>addPersonalI18nLine("hub.test.scoreboard.line3", (player) -> new Object[]{player.getDisplayedRank().getDisplayName()});
	}
}
