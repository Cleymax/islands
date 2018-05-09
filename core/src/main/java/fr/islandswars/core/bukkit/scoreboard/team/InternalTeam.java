package fr.islandswars.core.bukkit.scoreboard.team;

import fr.islandswars.api.net.packet.play.server.ScoreboardTeamPacket;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.scoreboard.Action;
import fr.islandswars.api.scoreboard.team.Team;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.DyeColor;
import static fr.islandswars.api.scoreboard.Action.*;

/**
 * File <b>InternalTeam</b> located on fr.islandswars.core.bukkit.scoreboard.team
 * InternalTeam is a part of islands.
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
 * Created the 09/05/2018 at 09:20
 * @since 0.3.0
 */
public class InternalTeam implements Team {

	private final String             name;
	private       String             prefix               = "";
	private       String             displayName          = "";
	private       String             suffix               = "";
	private       Set<IslandsPlayer> players              = new HashSet<>();
	private       Set<IslandsPlayer> viewers              = new HashSet<>();
	private       boolean            allowFriendlyFire    = false;
	private       boolean            seeFriendlyInvisible = false;
	private       DyeColor           teamColor;
	private       NameTagVisibility  nameTagVisibility    = NameTagVisibility.ALWAYS;
	private       CollisionRule      collisionRule        = CollisionRule.ALWAYS;

	public InternalTeam(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setGlobalTitleParameter(Supplier<Object[]> parameters) {
		// TODO: 09/05/2018 Support i18n
//		this.globalTitleParameter = parameters;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
		update(UPDATE);
	}

	@Override
	public void setPrefix(String prefix, Supplier<Object[]> parameters) {
		// TODO: 09/05/2018 Support i18n
//		this.prefixParameters = parameters;
		setPrefix(prefix);
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public void setDisplayName(String displayName, Supplier<Object[]> parameters) {
		// TODO: 09/05/2018 Support i18n
//		this.displayNameParameters = parameters;
		setDisplayName(displayName);
		update(UPDATE);
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void setSuffix(String suffix, Supplier<Object[]> parameters) {
		// TODO: 09/05/2018 Support i18n
//		this.suffixParameters = parameters;
		setPrefix(suffix);
		update(UPDATE);
	}

	@Override
	public boolean allowFriendlyFire() {
		return allowFriendlyFire;
	}

	@Override
	public void setFriendlyFire(boolean friendlyFire) {
		this.allowFriendlyFire = friendlyFire;
		update(UPDATE);
	}

	@Override
	public boolean canSeeFriendlyInvisible() {
		return seeFriendlyInvisible;
	}

	@Override
	public void setSeeFriendlyInvisible(boolean seeFriendlyInvisible) {
		this.seeFriendlyInvisible = seeFriendlyInvisible;
		update(UPDATE);
	}

	@Override
	public NameTagVisibility getNameTagVisibility() {
		return nameTagVisibility;
	}

	@Override
	public void setNameTagVisibility(NameTagVisibility nameTagVisibility) {
		this.nameTagVisibility = nameTagVisibility;
		update(UPDATE);
	}

	@Override
	public CollisionRule getCollisionRule() {
		return collisionRule;
	}

	@Override
	public void setCollisionRule(CollisionRule collisionRule) {
		this.collisionRule = collisionRule;
		update(UPDATE);
	}

	@Override
	public DyeColor getTeamColor() {
		return teamColor;
	}

	@Override
	public void setTeamColor(DyeColor teamColor) {
		this.teamColor = teamColor;
	}

	@Override
	public Stream<IslandsPlayer> getPlayers() {
		return Collections.unmodifiableSet(players).stream();
	}

	@Override
	public Stream<IslandsPlayer> getViewers() {
		return Collections.unmodifiableSet(viewers).stream();
	}

	@Override
	public void addPlayer(IslandsPlayer player) {
		players.add(player);
		update(ADD_PLAYER, player);
	}

	@Override
	public void addViewer(IslandsPlayer player) {
		viewers.add(player);
	}

	@Override
	public boolean hasPlayer(IslandsPlayer player) {
		return players.contains(player);
	}

	@Override
	public void removePlayer(IslandsPlayer player) {
		players.remove(player);
		update(REMOVE_PLAYER, player);
	}

	@Override
	public void removeViewer(IslandsPlayer player) {
		viewers.remove(player);
	}

	@Override
	public void update(Action action) {
		if (action == ADD_PLAYER || action == REMOVE_PLAYER)
			throw new IllegalArgumentException("You must pass a player in parameters");

		var packet = getPacket(action);
		if (action == REMOVE) {
			sendPacket(packet);
			return;
		}
		// Fill team option
		packet.setPrefix(prefix);
		packet.setDisplayName(displayName);
		packet.setSuffix(suffix);

		if (allowFriendlyFire)
			packet.allowFriendlyFire();

		if (seeFriendlyInvisible)
			packet.setCanSeeFriendlyInvisible();

		packet.setNameTagVisibility(nameTagVisibility);
		packet.setCollisionRule(collisionRule);

		// Add All players to the packet
		packet.setPlayers(
				getPlayers().map(islandsPlayer -> islandsPlayer.getCraftPlayer().getName())
						.collect(Collectors.toList())
		);

		sendPacket(packet);
	}

	private void update(Action action, IslandsPlayer playerUpdated) {
		var packet = getPacket(action);
		packet.setPlayers(List.of(playerUpdated.getCraftPlayer().getName()));
		sendPacket(packet);
	}

	private void sendPacket(ScoreboardTeamPacket packet) {
		packet.sendToPlayer(getViewers().map(IslandsPlayer::getCraftPlayer));
	}

	private ScoreboardTeamPacket getPacket(Action action) {
		var packet = new ScoreboardTeamPacket();
		packet.setTeamName(name);
		packet.setMode(action);
		return packet;
	}
}