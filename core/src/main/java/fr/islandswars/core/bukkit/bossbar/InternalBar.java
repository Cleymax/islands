package fr.islandswars.core.bukkit.bossbar;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.bossbar.Bar;
import fr.islandswars.api.bossbar.BarProperties;
import fr.islandswars.api.net.packet.play.server.BossPacket;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.utils.Preconditions;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.server.v1_12_R1.BossBattle;
import net.minecraft.server.v1_12_R1.MathHelper;
import net.minecraft.server.v1_12_R1.PacketPlayOutBoss;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;

/**
 * File <b>InternalBar</b> located on fr.islandswars.core.bukkit.bossbar
 * InternalBar is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 16:11
 * @since 0.2.9
 */
public class InternalBar extends BossBattle implements Bar {

	private List<IslandsPlayer>           viewers;
	private boolean                       active;
	private Map<UUID, Supplier<Object[]>> parameters;
	private Supplier<Object[]>            titleParameters;
	BarProperties properties;
	int           lastTick, lastUpdateTitle, repeatCount;

	InternalBar(String titleKey, BarColor barColor, BarStyle barStyle) {
		super(MathHelper.a(), CraftChatMessage.fromString(titleKey, true)[0], barColor, barStyle);
		this.titleParameters = () -> new Object[0];
		this.parameters = new HashMap<>();
		this.viewers = new ArrayList<>();
		this.active = true;
		this.lastTick = 0;
		this.repeatCount = -1;
		this.lastUpdateTitle = 0;
	}

	public void addPlayer(IslandsPlayer player) {
		Preconditions.checkNotNull(player);

		if (viewers.add(player) && active) {
			BossPacket packet = getPacket(PacketPlayOutBoss.Action.ADD);
			packet.setTitle(player.getPlayerLocale().format(title.toPlainText(), getParameter(player)));
			packet.sendToPlayer(player.getCraftPlayer());
			if (viewers.size() == 1)
				lazyRegister();
		}
	}

	@Override
	public void provideProperties(BarProperties properties, boolean erase) {
		Preconditions.checkNotNull(properties);

		if (erase | this.properties == null) {
			this.properties = properties;
		} else
			this.properties
					.withReverse(properties.useReverse())
					.withMagicColor(properties.useMagicColor())
					.withRepeat(properties.getRepeat())
					.withTitleUpdate(properties.getTitleDelta());
		if (properties.useReverse())
			setProgress(1);
	}

	@Override
	public void setGlobalParameter(Supplier<Object[]> parameters) {
		Preconditions.checkNotNull(parameters);

		this.titleParameters = parameters;
	}

	@Override
	public void setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			update(active ? PacketPlayOutBoss.Action.ADD : PacketPlayOutBoss.Action.REMOVE);
		}
	}

	@Override
	public void setProgress(float progress) {
		Preconditions.checkState(progress, ref -> ref >= 0 && ref <= 1);

		if (progress != super.b) {
			super.a(progress);
			update(PacketPlayOutBoss.Action.UPDATE_PCT);
		}
	}

	@Override
	public void setDarkenSky(boolean darkenSky) {
		if (darkenSky != this.e) {
			super.a(darkenSky);
			update(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
		}
	}

	@Override
	public void setPlayMusic(boolean playMusic) {
		if (playMusic != this.f) {
			super.b(playMusic);
			update(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
		}
	}

	@Override
	public void setCreateFog(boolean createFog) {
		if (createFog != this.g) {
			super.c(createFog);
			update(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
		}
	}

	@Override
	public Stream<IslandsPlayer> getViewers() {
		return viewers.stream();
	}

	public void removePlayer(IslandsPlayer player) {
		Preconditions.checkNotNull(player);

		if (viewers.remove(player) && active)
			getPacket(PacketPlayOutBoss.Action.REMOVE).sendToPlayer(player.getCraftPlayer());
		if (viewers.size() == 0)
			lazyUnregister();

		parameters.remove(player.getPlayerID());
	}

	@Override
	public void supplyI18nParameters(IslandsPlayer key, Supplier<Object[]> i18nParameters) {
		Preconditions.checkNotNull(key);
		Preconditions.checkNotNull(i18nParameters);

		parameters.put(key.getPlayerID(), i18nParameters);
	}

	public void update(PacketPlayOutBoss.Action action) {
		Preconditions.checkNotNull(action);

		if (active) {
			switch (action) {
				case UPDATE_NAME:
					getViewers().forEach(player -> {
						BossPacket packet = getPacket(action);
						packet.setTitle(player.getPlayerLocale().format(title.getText(), getParameter(player)));
						packet.sendToPlayer(player.getCraftPlayer());
					});
					break;
				default:
					sendUpdate(action);
			}
		}
	}

	private BossPacket getPacket(PacketPlayOutBoss.Action action) {
		return new BossPacket(action, this);
	}

	private Object[] getParameter(IslandsPlayer player) {
		return parameters.getOrDefault(player.getCraftPlayer().getUniqueId(), titleParameters).get();
	}

	private void lazyRegister() {
		((BukkitBarManager) IslandsApi.getInstance().getBarManager()).scheduleBar(this);
	}

	private void lazyUnregister() {
		((BukkitBarManager) IslandsApi.getInstance().getBarManager()).removeBar(this);
	}

	private void sendUpdate(PacketPlayOutBoss.Action action) {
		BossPacket packet = getPacket(action);
		getViewers().forEach(player -> packet.sendToPlayer(player.getCraftPlayer()));
	}
}

