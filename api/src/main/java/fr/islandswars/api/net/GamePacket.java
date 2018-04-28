package fr.islandswars.api.net;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.net.wrapper.Wrapper;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

/**
 * File <b>GamePacket</b> located on fr.islandswars.api.net
 * GamePacket is a part of IslandsApi Wars - Api.
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
 * Created the 30/09/2017 at 23:13
 * @since 0.1
 * <p>
 * An abstract layer to deal with reflection and bukkit packets
 */
public abstract class GamePacket<T extends Packet> extends Wrapper<T> {

    protected T handle;

    protected GamePacket(T handle) {
        super(handle);
        this.handle = handle;
    }

    /**
     * @return the associed packet type to this packet
     */
    public abstract PacketType getType();

    /**
     * Represent the default NMS packet
     *
     * @return a NMS wrapped packet
     */
    public final T getNMSPacket() {
        return this.handle;
    }

    /**
     * Queued this packet to the given player
     *
     * @param receiver a connected bukkit player
     */
    public final void sendToPlayer(Player receiver) {
        IslandsApi.getInstance().getProtocolManager().sendPacket(receiver, this);
    }

    /**
     * Queued this packet to the given player
     *
     * @param receivers a connected stream of bukkit player
     */
    public final void sendToPlayer(Stream<Player> receivers) {
        receivers.forEach(this::sendToPlayer);
    }
}

