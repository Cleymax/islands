package fr.islandswars.api.scoreboard;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * File <b>ScoreboardLine</b> located on fr.islandswars.api.scoreboard
 * ScoreboardLine is a part of Islands Wars - Api.
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
 * Created the 30/12/2017 at 18:01
 * @since 0.2.3
 * <p>
 * Represent a line on the sidebar scoreboard
 */
public interface ScoreboardLine<T> {

    /**
     * Return the specified text, if active
     *
     * @return specified text, or null
     */
    String getText();

    /**
     * Set the line content (or new line key, if translatable line is active)
     *
     * @param key captain obvious
     */
    void setText(String key);

    /**
     * Return the global parameters if specified
     *
     * @return wrapped parameters, or {@link Optional#empty()}
     */
    Optional<Supplier<Object[]>> getGlobalParameters();

    /**
     * Set the global translation parameters
     *
     * @param parameters captain obvious
     */
    void setGlobalParameters(Supplier<Object[]> parameters);

    /**
     * Will return wrapped i18n parameters if it's a personnal line and this player is
     * an actual viewers of this scoreboard
     *
     * @param genericKey the generic key to invoke {@link Function#apply(Object)}
     * @return wrapped parameters, or {@link Optional#empty()}
     */
    Optional<Supplier<Object[]>> getPersonnalParameters(T genericKey);

    /**
     * Set the personnal translation parameters
     *
     * @param translatableFunction a function to convert type to Object[]
     */
    void setPersonnalParameters(Function<T, Object[]> translatableFunction);
}
