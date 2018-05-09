package fr.islandswars.core.bukkit.scoreboard;

import fr.islandswars.api.scoreboard.ScoreboardLine;
import fr.islandswars.api.utils.Preconditions;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * File <b>InternalScoreboardLine</b> located on fr.islandswars.core.bukkit.scoreboard
 * InternalScoreboardLine is a part of islands.
 * <p>
 * Copyright (c) 2017 - 2018 Islands Wars.
 * <p>
 * islands is free software: you can redistribute it and/or modify
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
 * @author Vinetos, {@literal <vinetos@islandswars.fr>}
 * Created the 09/05/2018 at 09:06
 * @since 0.3.0
 */
public class InternalScoreboardLine<T> implements ScoreboardLine<T> {

	private String                text;
	private Supplier<Object[]>    globalParameters;
	private Function<T, Object[]> translatableFunction;

	public InternalScoreboardLine(String text) {
		this.text = text;
	}

	@Override
	public Optional<Supplier<Object[]>> getGlobalParameters() {
		return Optional.ofNullable(globalParameters);
	}

	@Override
	public Optional<Supplier<Object[]>> getPersonalParameters(T genericKey) {
		Preconditions.checkNotNull(genericKey);

		return Optional.ofNullable(() -> translatableFunction == null ? null : translatableFunction.apply(genericKey));
	}


	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setGlobalParameters(Supplier<Object[]> parameters) {
		Preconditions.checkNotNull(parameters);

		this.globalParameters = parameters;
	}

	@Override
	public void setPersonalParameters(Function<T, Object[]> translatableFunction) {
		Preconditions.checkNotNull(translatableFunction);

		this.translatableFunction = translatableFunction;
	}

	@Override
	public void setText(String key) {
		Preconditions.checkNotNull(key);
		this.text = key;
	}
}
