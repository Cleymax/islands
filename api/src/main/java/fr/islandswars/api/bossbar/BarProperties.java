package fr.islandswars.api.bossbar;

/**
 * File <b>BarProperties</b> located on fr.islandswars.api.bossbar
 * BarProperties is a part of Islands Wars - Api.
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
 * Created the 26/12/2017 at 21:49
 * @since 0.2.3
 * <p>
 * Some custom and funny properties :)
 */
public class BarProperties {

	private final int tick, percent;
	private int repeat, titleDelta;
	private boolean useMagicColor, useReverse;

	private BarProperties(int tick, int percent) {
		this.tick = tick;
		this.percent = percent;
	}

	/**
	 * Supply some properties to the bar
	 *
	 * @param tick    the delta between each update (in tick, 1s == 20 ticks)
	 * @param percent will be added to the current progress each delta
	 * @return a bar properties builder
	 */
	public static BarProperties builder(int tick, int percent) {
		return new BarProperties(tick, percent);
	}

	/**
	 * Update current bar's color each 1/2 second to display crazy unicorn hack
	 *
	 * @param useMagicHack true YEAH BOY
	 * @return a bar properties builder
	 */
	public BarProperties withMagicColor(boolean useMagicHack) {
		this.useMagicColor = useMagicHack;
		return this;
	}

	/**
	 * Only active if {@link #percent} if sup than 0, the progress will
	 * decrease instead of increase
	 *
	 * @param useReverseOrder to reverse the display
	 * @return a bar properties builder
	 */
	public BarProperties withReverse(boolean useReverseOrder) {
		this.useReverse = useReverseOrder;
		return this;
	}

	/**
	 * Specify a limit, before ending this bar. Used in
	 * {@link BarSequence} where 0 means infinity
	 *
	 * @param time how many time before ending this sequence
	 * @return a bar properties builder
	 */
	public BarProperties withRepeat(int time) {
		this.repeat = time;
		return this;
	}

	/**
	 * Update this title according to specified time (in tick)
	 *
	 * @param time delta between each {@link net.minecraft.server.v1_12_R1.PacketPlayOutBoss.Action#UPDATE_NAME}
	 * @return a bar properties builder
	 */
	public BarProperties withTitleUpdate(int time) {
		this.titleDelta = time;
		return this;
	}

	/**
	 * Can be null (false)
	 *
	 * @return if use magic color or not
	 */
	public boolean useMagicColor() {
		return useMagicColor;
	}

	/**
	 * Can be null (false)
	 *
	 * @return if use reverse or not
	 */
	public boolean useReverse() {
		return useReverse;
	}

	/**
	 * @return the percent to increase
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @return the delta in tick
	 */
	public int getTick() {
		return tick;
	}

	/**
	 * @return the repeat tick can be null (0)
	 */
	public int getRepeat() {
		return repeat;
	}

	/**
	 * @return the title delta can be null (0)
	 */
	public int getTitleDelta() {
		return titleDelta;
	}
}