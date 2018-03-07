package fr.islandswars.api.net.packet.login.server;

import com.mojang.authlib.GameProfile;
import fr.islandswars.api.net.GamePacket;
import fr.islandswars.api.net.PacketType;
import net.minecraft.server.v1_12_R1.PacketLoginOutSuccess;

/**
 * File <b>SuccessPacket</b> located on fr.islandswars.api.net.packet.login.server
 * SuccessPacket is a part of Islands Wars - Api.
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 22/10/17 at 22:25
 * @since 0.1
 */
public class SuccessPacket extends GamePacket<PacketLoginOutSuccess> {

	protected SuccessPacket(PacketLoginOutSuccess handle) {
		super(handle);
	}

	/**
	 * @return a now valide game profile for the client
	 */
	public GameProfile getValidatedGameProfile() {
		return (GameProfile) getHandleValue("a");
	}

	@Override
	public PacketType getType() {
		return PacketType.Login.Server.SUCCESS;
	}
}
