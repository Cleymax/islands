package fr.islandswars.core.bukkit.bossbar;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarSequence;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * File <b>InternalbarSequence</b> located on fr.islandswars.core.bukkit.bossbar
 * InternalbarSequence is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 16:13
 * @since 0.2.9
 */
public class InternalBarSequence implements BarSequence {

    private List<IslandsPlayer> viewers;
    private List<InternalBar> sequence;
    private int currentBar;

    public InternalBarSequence(List<InternalBar> bars) {
        this.sequence = bars;
        this.viewers = new ArrayList<>();
        this.currentBar = 0;
    }

    public void addPlayer(IslandsPlayer player) {
        Preconditions.checkNotNull(player);

        sequence.get(currentBar).addPlayer(player);
        viewers.add(player);

        if (viewers.size() == 1)
            lazyRegister();

    }

    public InternalBar getNextbar() {
        if (++currentBar == sequence.size() - 1)
            currentBar = 0;
        return sequence.get(0);
    }

    public void removePlayer(IslandsPlayer player) {
        Preconditions.checkNotNull(player);

        sequence.get(currentBar).removePlayer(player);
        viewers.remove(player);

        if (viewers.size() == 0) {
            lazyUnregister();
            currentBar = 0;
        }
    }

    @Override
    public void shutdownSequence() {
        viewers.forEach(this::removePlayer);
        sequence.clear();
    }

    @Override
    public Bar getCurrentBar() {
        return sequence.get(currentBar);
    }

    @Override
    public Stream<IslandsPlayer> getViewers() {
        return viewers.stream();
    }

    @Override
    public Stream<Bar> getBars() {
        return sequence.stream().map(Bar.class::cast);
    }

    private void lazyRegister() {
        ((BukkitBarManager) IslandsApi.getInstance().getBarManager()).scheduleSequence(this);
    }

    private void lazyUnregister() {
        ((BukkitBarManager) IslandsApi.getInstance().getBarManager()).removeSequence(this);
    }
}

