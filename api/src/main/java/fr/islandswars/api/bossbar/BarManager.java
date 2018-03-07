package fr.islandswars.api.bossbar;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.module.Module;
import net.minecraft.server.v1_12_R1.BossBattle;
import org.bukkit.boss.BarFlag;

import java.util.function.Supplier;

/**
 * File <b>BarManager</b> located on fr.islandswars.api.bossbar
 * BarManager is a part of Islands Wars - Api.
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
 * Created the 26/12/2017 at 19:58
 * @since 0.2.3
 * <p>
 * Create easily bar and sequence
 */
public abstract class BarManager extends Module {

	public BarManager(IslandsApi api) {
		super(api);
	}

	/**
	 * Create a boss bar with the supplied parameters
	 *
	 * @param title bar's title (or translation key if used)
	 * @param color bar's color
	 * @param style bar's style
	 * @param flags bar's flags
	 * @return a bukkit wrapped boss bar
	 */
	public abstract Bar createBar(String title, BossBattle.BarColor color, BossBattle.BarStyle style, BarFlag... flags);

	/**
	 * Create a boss bar with the supplied parameters and title parameters
	 *
	 * @param title      title translation key
	 * @param color      bar's color
	 * @param style      bar's style
	 * @param parameters some translation parameters
	 * @param flags      bar's flags
	 * @return a bukkit wrapped boss bar
	 */
	public abstract Bar createBar(String title, BossBattle.BarColor color, BossBattle.BarStyle style, Supplier<Object[]> parameters, BarFlag... flags);

	/**
	 * Create a bar sequence to display multiples bars in a specific order
	 *
	 * @param properties bar properties, it will be set to each bar, except if
	 *                   supplied bars already have some properties set
	 * @param bars       minimum 1 bar to init sequence
	 * @return an util class to manage this sequence
	 */
	public abstract BarSequence createSequence(BarProperties properties, Bar... bars);

}