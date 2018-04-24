package fr.islandswars.core.player;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarSequence;
import fr.islandswars.api.i18n.Locale;
import fr.islandswars.api.player.ChatType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.player.PlayerStorage;
import fr.islandswars.api.player.rank.IslandsRank;
import fr.islandswars.api.scoreboard.Scoreboard;
import fr.islandswars.api.utils.Preconditions;
import fr.islandswars.core.bukkit.bossbar.InternalBar;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;
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

	private final           CraftPlayer               player;
	private final transient CopyOnWriteArrayList<Bar> bars;
	private final transient List<BarSequence>         barSequences;

	private Locale locale = Locale.FRENCH; //TODO debug purpose


	public InternalPlayer(Player player) {
		this.barSequences = new ArrayList<>();
		this.player = (CraftPlayer) player;
		this.bars = new CopyOnWriteArrayList<>();

	}

	@Override
	public void disconnect() {
		getDisplayedBars().forEach(this::hideBar);
	}

	@Override
	public void displayBar(Bar bar) {
		Preconditions.checkNotNull(bar);

		InternalBar internalBar = (InternalBar) bar;
		if (bar.getViewers().noneMatch(p -> p.equals(this))) {
			bars.add(bar);
			internalBar.addPlayer(this);
		}
	}

	@Override
	public void displaySequence(BarSequence sequence) {
		Preconditions.checkNotNull(sequence);

		barSequences.add(sequence);
		//TODO code
	}

	@Override
	public void displaybar(Bar bar, Supplier<Object[]> i18nParameters) {
		Preconditions.checkNotNull(bar);
		Preconditions.checkNotNull(i18nParameters);

		InternalBar internalBar = (InternalBar) bar;
		if (bar.getViewers().noneMatch(p -> p.equals(this))) {
			bars.add(bar);
			internalBar.supplyI18nParameters(this, i18nParameters);
			internalBar.addPlayer(this);
		}
	}

	@Override
	public List<IslandsRank> getAllRanks() {
		return null;
	}

	@Override
	public CraftPlayer getCraftPlayer() {
		return player;
	}

	@Override
	public Optional<Scoreboard> getCurrentScoreboard() {
		return Optional.empty();
	}

	@Override
	public List<Bar> getDisplayedBars() {
		return bars;
	}

	@Override
	public IslandsRank getDisplayedRank() {
		return null;
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
	public PlayerStorage getPlayerStorage() {
		return null;
	}

	@Override
	public void hideBar(Bar bar) {
		IslandsApi.getInstance().getInfraLogger().createDefaultLog(Level.INFO, "Remove player from bar");
		Preconditions.checkNotNull(bar);

		InternalBar internalBar = (InternalBar) bar;
		if (bar.getViewers().anyMatch(p -> p.equals(this))) {
			bars.remove(bar);
			internalBar.removePlayer(this);
		}
	}

	@Override
	public void hideCurrentScoreboard() {

	}

	@Override
	public void hideSequence(BarSequence sequence) {
		Preconditions.checkNotNull(sequence);

		barSequences.remove(sequence);
	}

	@Override
	public void sendToHub() {
		try {
			IslandsApi.getInstance().getServiceManager().getRabbitService().publishToAnOpenQueue(new StringBuffer("hub|").append(player.getUniqueId()), "funicular");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendTranslatedMessage(ChatType type, String key, Object... parameters) {
		Preconditions.checkNotNull(key);

		switch (type) {
			case CHAT:
				player.sendMessage(locale.format(key, parameters));
				break;
			case SUBTITLE:
				sendTranslatedTitle(null, null, key, parameters);
				break;
			case TITLE:
				sendTranslatedTitle(key, parameters, null);
				break;
		}
	}
}
