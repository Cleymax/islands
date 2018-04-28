package fr.islandswars.api.net.packet.play.server;

import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutOpenWindow;

/**
 * File <b>OpenWindowPacket</b> located on fr.islandswars.api.net.packet.play.server
 * OpenWindowPacket is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 17:12
 * @since 0.2.9
 */
public class OpenWindowPacket extends GamePacket<PacketPlayOutOpenWindow> {

    public OpenWindowPacket(PacketPlayOutOpenWindow handle) {
        super(handle);
    }

    public int getHorseEntityID() {
        if (getWindowType().equals("EntityHorse"))
            return (int) getHandleValue("e");
        else return 0;
    }

    public void setHorseEntityID(int entityID) {
        if (getWindowType().equals("EntityHorse"))
            setHandleValue("e", entityID);
    }

    @Override
    public PacketType getType() {
        return PacketType.Play.Server.OPEN_WINDOW;
    }

    public int getWindowID() {
        return (int) getHandleValue("a");
    }

    public void setWindowID(int windowId) {
        setHandleValue("a", windowId);
    }

    public int getWindowSize() {
        return (int) getHandleValue("d");
    }

    public void setWindowSize(int windowSize) {
        setHandleValue("d", windowSize);
    }

    public String getWindowTitle() {
        return ((IChatBaseComponent) getHandleValue("c")).getText();
    }

    public void setWindowTitle(IChatBaseComponent title) {
        setHandleValue("c", title);
    }

    public String getWindowType() {
        return (String) getHandleValue("b");
    }

    public void setWindowType(String type) {
        setHandleValue("b", type);
    }
}
