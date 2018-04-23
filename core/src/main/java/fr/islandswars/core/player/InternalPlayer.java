package fr.islandswars.core.player;

import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarSequence;
import fr.islandswars.api.i18n.Locale;
import fr.islandswars.api.player.ChatType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.player.PlayerStorage;
import fr.islandswars.api.player.rank.IslandsRank;
import fr.islandswars.api.scoreboard.Scoreboard;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * File <b>InternalPlayer</b> located on fr.islandswars.core.player
 * InternalPlayer is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 14:54
 * @since 0.2.9
 */
public class InternalPlayer implements IslandsPlayer {

	private final           CraftPlayer       player;
	private final transient List<Bar>         bars;
	private final transient List<BarSequence> barSequences;

	private Locale locale = Locale.FRENCH; //TODO debug purpose


	public InternalPlayer(Player player) {
		this.barSequences = new ArrayList<>();
		this.player = (CraftPlayer) player;
		this.bars = new ArrayList<>();

	}

	@Override
	public UUID getPlayerID() {
		return player.getUniqueId();
	}

	@Override
	public Locale getPlayerLocale() {
		return locale;
	}

	@Override
	public CraftPlayer getCraftPlayer() {
		return player;
	}

	@Override
	public List<Bar> getDisplayedBars() {
		return null;
	}

	@Override
	public PlayerStorage getPlayerStorage() {
		return null;
	}

	@Override
	public IslandsRank getDisplayedRank() {
		return null;
	}

	@Override
	public List<IslandsRank> getAllRanks() {
		return null;
	}

	@Override
	public void displayBar(Bar bar) {

	}

	@Override
	public void displaySequence(BarSequence sequence) {

	}

	@Override
	public void displaybar(Bar bar, Supplier<Object[]> i18nParameters) {

	}

	@Override
	public void hideBar(Bar bar) {

	}

	@Override
	public void hideSequence(BarSequence sequence) {

	}

	@Override
	public void hideCurrentScoreboard() {

	}

	@Override
	public Optional<Scoreboard> getCurrentScoreboard() {
		return Optional.empty();
	}

	@Override
	public void sendTranslatedMessage(ChatType type, String key, Object... parameters) {

	}

	@Override
	public void sendToHub() {

	}
}
