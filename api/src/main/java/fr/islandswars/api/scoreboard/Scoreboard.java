package fr.islandswars.api.scoreboard;

import fr.islandswars.api.player.IslandsPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * File <b>Scoreboard</b> located on fr.islandswars.api.scoreboard
 * Scoreboard is a part of Islands Wars - Api.
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
 * @author Valentin Chassignol (Vinetos), {@literal <vinetos@islandswars.fr>}
 * Created the 30/12/2017 at 13:02
 * @since 0.2.3
 * <p>
 * Wrapper for bukkit scoreboard to deal with i18n
 */
public interface Scoreboard extends ScoreboardComponent {

	/**
	 * @return this objective name
	 */
	String getObjectiveName();

	/**
	 * Gets a line into the scoreboard
	 *
	 * @param line the position of the line
	 * @param <T>  instance type
	 * @return a wrapped ScoreboardLine inside an Optional
	 */
	<T> Optional<ScoreboardLine<T>> getLine(int line);

	/**
	 * Append a new line to this scoreboard
	 *
	 * @param text the line content
	 * @return the current scoreboard
	 */
	Scoreboard addLine(String text);

	/**
	 * Set a line to the scoreboard
	 *
	 * @param line the line number
	 * @param text The line content
	 * @return the current scoreboard
	 */
	Scoreboard setLine(int line, String text);

	/**
	 * Append a new translatable line to this scoreboard
	 *
	 * @param path       the text path in .lang file
	 * @param parameters global i18n parameters
	 * @return the current scoreboard
	 */
	Scoreboard addGlobalI18nLine(String path, Supplier<Object[]> parameters);

	/**
	 * Set a translatable line to the scoreboard
	 *
	 * @param line       the line number
	 * @param path       the text path in .lang file
	 * @param parameters global i18n parameters
	 * @return the current scoreboard
	 */
	Scoreboard setGlobalI18nLine(int line, String path, Supplier<Object[]> parameters);

	/**
	 * Append a new personnal translatable line to this scoreboard
	 *
	 * @param path                 the text path in .lang file
	 * @param translatableFunction a per-user object resolver
	 * @param <T>                  instance type
	 * @return the current scoreboard
	 */
	<T> Scoreboard addPersonnalI18nLine(String path, Function<T, Object[]> translatableFunction);

	/**
	 * Set a personnal translatable line to the scoreboard
	 *
	 * @param line                 the line number
	 * @param path                 the text path in .lang file
	 * @param translatableFunction a per-user object resolver
	 * @param <T>                  instance type
	 * @return the current scoreboard
	 */
	<T> Scoreboard setPersonnalI18nLine(int line, String path, Function<T, Object[]> translatableFunction);

	/**
	 * Remove a line from the scoreboard
	 *
	 * @param line the line number to be removed
	 * @return the current scoreboard
	 */
	Scoreboard removeLine(int line);

	/**
	 * Create this scoreboard for this player, it will use the given map to get {@link Function#apply(Object)}
	 * parameters, according to each line generic type
	 *
	 * @param player                   a player to open this scoreboard
	 * @param translatableLineInstance reference line (int id) and instance
	 */
	void addPlayer(IslandsPlayer player, Map<Integer, Object> translatableLineInstance);

	/**
	 * Create this scoreboard for this player, it will use the given map to get {@link Function#apply(Object)}
	 * parameters, according to each line generic type
	 *
	 * @param player                   a player to open this scoreboard
	 * @param translatableLineInstance reference line (int id) and instance
	 * @param customParameters         title personnal parameters
	 */
	void addPlayer(IslandsPlayer player, Map<Integer, Object> translatableLineInstance, Supplier<Object[]> customParameters);

	/**
	 * Hide this scoreboard to the player
	 *
	 * @param player an IslandsPlayer to remove this scoreboard
	 */
	void hideToPlayer(IslandsPlayer player);

	/**
	 * The slot where the scoreboard will be displayed
	 */
	enum ScoreboardSlot {

		LIST(0),
		SIDEBAR(1),
		BELOW_NAME(2);

		private final int mode;

		ScoreboardSlot(final int mode) {
			this.mode = mode;
		}

		public final int getMode() {
			return this.mode;
		}

	}

	/**
	 * The mode of the score
	 */
	enum ScoreAction {

		CREATE_OR_CHANGE(PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE),
		REMOVE(PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE);

		private final PacketPlayOutScoreboardScore.EnumScoreboardAction action;

		ScoreAction(final PacketPlayOutScoreboardScore.EnumScoreboardAction action) {
			this.action = action;
		}

		@Nullable
		public static ScoreAction getAction(PacketPlayOutScoreboardScore.EnumScoreboardAction eAction) {
			return Arrays.stream(values()).filter(scoreAction -> scoreAction.action == eAction).findFirst().orElse(null);
		}

		public final PacketPlayOutScoreboardScore.EnumScoreboardAction getAction() {
			return this.action;
		}

	}

}
