package fr.islandswars.api.net.packet.play.client;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketPlayInAbilities;

/**
 * File <b>AbilitiesInPacket</b> located on fr.islandswars.api.net.packet.play.client
 * AbilitiesInPacket is a part of Islands Wars - Api.
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
 * Created the 18/10/17 at 13:42
 * @since 0.1
 */
public class AbilitiesInPacket extends GamePacket<PacketPlayInAbilities> {

    protected AbilitiesInPacket(PacketPlayInAbilities handle) {
        super(handle);
    }

    public boolean isInvulnerable() {
        return (boolean) getHandleValue("a");
    }

    public void setInvulnerable(boolean invulnerable) {
        setHandleValue("a", invulnerable);
    }

    public boolean isFlying() {
        return (boolean) getHandleValue("b");
    }

    public void setFlying(boolean flying) {
        setHandleValue("b", flying);
    }

    public boolean canFly() {
        return (boolean) getHandleValue("c");
    }

    public void setFly(boolean fly) {
        setHandleValue("c", fly);
    }

    public boolean canInstantlyBuild() {
        return (boolean) getHandleValue("d");
    }

    public void setInstantlyBuild(boolean instantlyBuild) {
        setHandleValue("d", instantlyBuild);
    }

    public float getFlySpeed() {
        return (float) getHandleValue("e");
    }

    public void setFlySpeed(float flySpeed) {
        setHandleValue("e", flySpeed);
    }

    public float getWalkSpeed() {
        return (float) getHandleValue("f");
    }

    public void setWalkSpeed(float walkSpeed) {
        setHandleValue("f", walkSpeed);
    }

    @Override
    public PacketType getType() {
        return PacketType.Play.Client.ABILITIES;
    }

}
