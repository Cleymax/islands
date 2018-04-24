package fr.islandswars.core.bukkit.storage.item;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemType;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

/**
 * File <b>TranslatableItem</b> located on fr.islandswars.core.bukkit.storage.item
 * TranslatableItem is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:51
 * @since 0.2.9
 */
public class TranslatableItem implements Item {

	private final ItemType type;

	public TranslatableItem(ItemType type) {
		this.type = type;
	}

	public NBTTagCompound getCompound() {
		return type.getCompound();
	}

}
