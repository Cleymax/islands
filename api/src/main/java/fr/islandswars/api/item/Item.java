package fr.islandswars.api.item;

import fr.islandswars.api.IslandsApi;
import net.minecraft.server.v1_12_R1.ItemStack;

/**
 * File <b>Item</b> located on fr.islandswars.api.item
 * Item is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:36
 * @since 0.2.9
 */
public interface Item {

	default org.bukkit.inventory.ItemStack asBukkitItemStack() {
		return IslandsApi.getInstance().getItemManager().wrappToBukkitItemStack(this);
	}

	default ItemStack asNMSItemStack() {
		return IslandsApi.getInstance().getItemManager().wrappToNMSItemStack(this);
	}

}
