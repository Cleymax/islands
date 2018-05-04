package fr.islandswars.core.bukkit.item;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemProperties;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.utils.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.event.Cancellable;

/**
 * File <b>InternalItem</b> located on fr.islandswars.core.bukkit.item
 * InternalItem is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 14:35
 * @since 0.2.9
 */
public class InternalItem implements Item {

	private final int                                    id;
	private final ItemType                               type;
	private final PropertiesWrapper                      properties;
	private       ItemStack                              wrapper;
	private       BiConsumer<IslandsPlayer, Cancellable> clickEvent;
	private       Map<UUID, ParametersSuppliers>         suppliers;
	private       ParametersSuppliers                    globalSuppliers;
	private       String                                 loreKey, nameKey;

	public InternalItem(ItemType type, int id) {
		this.globalSuppliers = new ParametersSuppliers();
		this.properties = new PropertiesWrapper(type);
		this.suppliers = new HashMap<>();
		this.type = type;
		this.id = id;
		type.getCompound().set("tag", new NBTTagCompound());
		type.getCompound().getCompound("tag").setInt("id", id);
	}

	public void callEvent(IslandsPlayer player, Cancellable cancellable) {
		if (clickEvent != null)
			clickEvent.accept(player, cancellable);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getLoreTranslationKey() {
		return loreKey;
	}

	@Override
	public String getNameTranslationKey() {
		return nameKey;
	}

	@Override
	public ItemProperties getProperties() {
		return properties;
	}

	@Override
	public Item globalLoreParameters(Supplier<Object[]> loreSupplier) {
		Preconditions.checkNotNull(loreSupplier);

		this.globalSuppliers.loreSupplier = loreSupplier;
		return this;
	}

	@Override
	public Item globalNameParameters(Supplier<Object[]> nameSupplier) {
		Preconditions.checkNotNull(nameSupplier);

		this.globalSuppliers.nameSupplier = nameSupplier;
		return this;
	}

	@Override
	public Item onClick(BiConsumer<IslandsPlayer, Cancellable> event) {
		Preconditions.checkNotNull(event);

		this.clickEvent = event;
		return this;
	}

	@Override
	public Item personnalLoreParameters(Supplier<Object[]> loreSupplier, UUID id) {
		suppliers.compute(id, (key, params) -> {
			if (params == null) {
				ParametersSuppliers parameterSuppliers = new ParametersSuppliers();
				parameterSuppliers.loreSupplier = loreSupplier;
				return parameterSuppliers;
			} else {
				params.loreSupplier = loreSupplier;
				return params;
			}
		});
		return this;
	}

	@Override
	public Item personnalNameParameters(Supplier<Object[]> nameSupplier, UUID id) {
		suppliers.compute(id, (key, params) -> {
			if (params == null) {
				ParametersSuppliers parameterSuppliers = new ParametersSuppliers();
				parameterSuppliers.nameSupplier = nameSupplier;
				return parameterSuppliers;
			} else {
				params.nameSupplier = nameSupplier;
				return params;
			}
		});
		return this;

	}

	@Override
	public ItemStack toNMSItem() {
		if (wrapper == null) {
			this.wrapper = new net.minecraft.server.v1_12_R1.ItemStack(type.getCompound());
			return wrapper;
		} else
			return wrapper;
	}

	@Override
	public Item withInternalisation(String nameKey, String loreKey) {
		this.nameKey = nameKey;
		this.loreKey = loreKey;
		return this;
	}

	@Override
	public Item withProperties(Consumer<ItemProperties> propertiesConsumer) {
		propertiesConsumer.accept(properties);
		return this;
	}

	public PropertiesWrapper getPropertiesWrapper() {
		return properties;
	}

	public Optional<ParametersSuppliers> getSuppliers(UUID id) {
		return Optional.ofNullable(suppliers.getOrDefault(id, globalSuppliers));
	}

}
