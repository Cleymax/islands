package fr.islandswars.api.bossbar;

import fr.islandswars.api.i18n.viewers.I18nParameters;
import fr.islandswars.api.player.IslandsPlayer;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * File <b>Bar</b> located on fr.islandswars.api.bossbar
 * Bar is a part of Islands Wars - Api.
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
 * Created the 26/12/2017 at 19:36
 * @since 0.2.3
 * <p>
 * Wrapped a bukkit boss bossbar to deal with i18n
 */
public interface Bar extends I18nParameters<IslandsPlayer> {

    /**
     * Add some properties such as timer, etc to this bossbar
     *
     * @param properties bossbar's properties to deal with
     * @param erase      update properties, even if it's already set
     */
    void provideProperties(BarProperties properties, boolean erase);

    /**
     * Register some parameters that will be used for all players
     *
     * @param parameters translation parameters
     */
    void setGlobalParameter(Supplier<Object[]> parameters);

    /**
     * Hide or display the current boss bossbar
     *
     * @param active hide or display bossbar
     */
    void setActive(boolean active);

    /**
     * Update this bossbar progress, must be inside 0 to 1 range
     *
     * @param progress a new progress
     */
    void setProgress(float progress);

    /**
     * Active darken sky properties
     *
     * @param darkenSky hide or display
     */
    void setDarkenSky(boolean darkenSky);

    /**
     * Play minecraft ender end music
     *
     * @param playMusic play or mute
     */
    void setPlayMusic(boolean playMusic);

    /**
     * Display fog
     *
     * @param createFog hide or display
     */
    void setCreateFog(boolean createFog);

    /**
     * @return this bossbar viewers
     */
    Stream<IslandsPlayer> getViewers();

}
