package fr.islandswars.api.net;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.player.IslandsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * File <b>PacketEvent</b> located on fr.islandswars.api.net
 * PacketEvent is a part of IslandsApi Wars - Api.
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
 * @author based on Gogume1er design and idea
 * Created the 30/09/2017 at 23:27
 * @since 0.1
 * <p>
 * Construct with basic and known infos when player or server send packet
 */
public class PacketEvent<T extends GamePacket> implements Cancellable {

    private final IslandsPlayer player;
    private final T packet;
    private boolean cancel;

    public PacketEvent(Player player, T packet) {
        this.player = IslandsApi.getInstance().getPlayer(player.getUniqueId()).orElseThrow(() -> new NullPointerException("Try to send a packet to a non-registered player!"));
        this.packet = packet;
        cancel = false;
    }

    /**
     * Return the packet wrapped in this event
     *
     * @return a wrapped packet
     */
    public T getPacket() {
        return packet;
    }

    /**
     * Return the player who receive or send the packet
     *
     * @return this packet's target
     */
    public IslandsPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
