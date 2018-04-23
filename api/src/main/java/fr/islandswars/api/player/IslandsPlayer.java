package fr.islandswars.api.player;

import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarSequence;
import fr.islandswars.api.i18n.Locale;
import fr.islandswars.api.net.packet.play.server.TitlePacket;
import fr.islandswars.api.player.rank.IslandsRank;
import fr.islandswars.api.scoreboard.Scoreboard;
import fr.islandswars.api.utils.Preconditions;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

/**
 * File <b>IslandsPlayer</b> located on fr.islandswars.api.player
 * IslandsPlayer is a part of Islands Wars - Api.
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
 * @author Valentin Chassignol (Vinetos), {@literal <vinetos@islandswars.fr>}
 * Created the 09/12/2017 at 16:44
 * @since 0.1
 * <p>
 * Represent a database player, with all vars fulfill from redis storage
 */
public interface IslandsPlayer {

	/**
	 * Display the given bossbar to this player
	 *
	 * @param bar a bossbar to display
	 */
	void displayBar(Bar bar);

	/**
	 * Display the given sequence to this player
	 *
	 * @param sequence a sequence to display
	 */
	void displaySequence(BarSequence sequence);

	/**
	 * Display the given bossbar to this player, with i18n parameters
	 *
	 * @param bar            a bossbar to display
	 * @param i18nParameters some translation parameters
	 */
	void displaybar(Bar bar, Supplier<Object[]> i18nParameters);

	/**
	 * Display a particle to this player
	 *
	 * @param particle which particle to display
	 * @param x        bukkit coordinates
	 * @param y        bukkit coordinates
	 * @param z        bukkit coordinates
	 * @param count    a positive number of particles to spawn (spawn in a sphere according to gaussian offset.
	 *                 It will stack in this method since offsetX|Y|Z == 0)
	 * @see #displayparticles(Particle, float, float, float, float, float, float, int)
	 */
	default void displayparticles(Particle particle, float x, float y, float z, int count) {
		Preconditions.checkNotNull(particle);
		Preconditions.checkState(count, ref -> ref > 0);
		getCraftPlayer().spawnParticle(particle, x, y, z, count);
	}

	/**
	 * Display a particle to this player
	 *
	 * @param particle which particle to display
	 * @param x        bukkit coordinates
	 * @param y        bukkit coordinates
	 * @param z        bukkit coordinates
	 * @param count    a positive number of particles to spawn (spawn in a sphere according to gaussian offset)
	 * @param offSetX  x offset
	 * @param offSetY  y offset
	 * @param offSetZ  z offset
	 */
	default void displayparticles(Particle particle, float x, float y, float z, float offSetX, float offSetY, float offSetZ, int count) {
		Preconditions.checkNotNull(particle);
		Preconditions.checkState(count, ref -> ref > 0);
		getCraftPlayer().spawnParticle(particle, x, y, z, count, offSetX, offSetY, offSetZ);
	}

	/**
	 * @return all player ranks
	 */
	List<IslandsRank> getAllRanks();

	/**
	 * @return the associated player implementation
	 */
	CraftPlayer getCraftPlayer();

	/**
	 * @return current wrapped scoreboard if exist
	 */
	Optional<Scoreboard> getCurrentScoreboard();

	/**
	 * @return the bars that are actually displayed to this player
	 */
	List<Bar> getDisplayedBars();

	/**
	 * @return the rank with the highest level
	 */
	IslandsRank getDisplayedRank();

	/**
	 * @return the player unique id
	 */
	UUID getPlayerID();

	/**
	 * @return the current player language
	 */
	Locale getPlayerLocale();

	/**
	 * @return the personnal player storage to deal with i18n
	 */
	PlayerStorage getPlayerStorage();

	/**
	 * @return if this player is viewing a scoreboard
	 */
	default boolean hasScoreboardDisplayed() {
		return getCurrentScoreboard().isPresent();
	}

	/**
	 * hide this bossbar to the player
	 *
	 * @param bar a bossbar to remove this player
	 */
	void hideBar(Bar bar);

	/**
	 * Will attempt to hide the scoreboard (if exist)
	 */
	void hideCurrentScoreboard();

	/**
	 * hide this bossbar to the player
	 *
	 * @param sequence a bossbar to remove this player
	 */
	void hideSequence(BarSequence sequence);

	/**
	 * Send the current player to an available hub (except if the player is already on a hub)
	 */
	void sendToHub();

	/**
	 * Basic method to send one translated line to a player.
	 * It will use default label when using title or subtitle
	 *
	 * @param type       where to display the message
	 * @param key        an internal key
	 * @param parameters some translation parameters if needed
	 */
	void sendTranslatedMessage(ChatType type, String key, Object... parameters);

	/**
	 * A more concise method to deal with title | subtitle, can deal with null label
	 *
	 * @param title              a title key
	 * @param titleParameters    some translations parameters if needed
	 * @param subtitle           a subtitle key
	 * @param subtitleParameters some translations parameters if needed
	 */
	default void sendTranslatedTitle(String title, Object[] titleParameters, String subtitle, Object... subtitleParameters) {
		if (title != null) {
			String translatedTitle = getPlayerLocale().format(title, titleParameters);
			new TitlePacket(translatedTitle, PacketPlayOutTitle.EnumTitleAction.TITLE, 20, 60, 20).sendToPlayer(getCraftPlayer());
		}
		if (subtitle != null) {
			String translatedSubTitle = getPlayerLocale().format(subtitle, titleParameters);
			new TitlePacket(translatedSubTitle, PacketPlayOutTitle.EnumTitleAction.SUBTITLE, 20, 60, 20).sendToPlayer(getCraftPlayer());
		}
	}
}
