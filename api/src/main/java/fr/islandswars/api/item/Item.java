package fr.islandswars.api.item;

import fr.islandswars.api.player.IslandsPlayer;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.event.Cancellable;

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
 * Created the 26/04/2018 at 13:28
 * @since 0.2.9
 */
public interface Item {

	/**
	 * @return this internal item id
	 */
	int getId();

	Item globalLoreParameters(Supplier<Object[]> loreSupplier);

	Item globalNameParameters(Supplier<Object[]> nameSupplier);

	Item onClick(BiConsumer<IslandsPlayer, Cancellable> event);

	Item personnalLoreParameters(Supplier<Object[]> loreSupplier, UUID id);

	Item personnalNameParameters(Supplier<Object[]> nameSupplier, UUID id);

	/**
	 * @return this item wrapped in nms item
	 */
	ItemStack toNMSItem();

	Item withInternalisation(String nameKey, String loreKey);

	/**
	 * Supply custom properties to this item
	 *
	 * @param propertiesConsumer a consumer to fill existing properties
	 * @return this item builder
	 */
	Item withProperties(Consumer<ItemProperties> propertiesConsumer);

	/**
	 * @return the key use to translate this item's name, or else item's name
	 */
	String getNameTranslationKey();

	/**
	 * @return the key use to translate this item's lore
	 */
	String getLoreTranslationKey();

	/**
	 * @return this item's properties
	 */
	ItemProperties getproperties();

}
