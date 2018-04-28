package fr.islandswars.core.bukkit.storage;

import fr.islandswars.api.i18n.Locale;
import fr.islandswars.api.item.Item;
import fr.islandswars.api.item.ItemType;
import fr.islandswars.api.player.IslandsPlayer;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageBuilder;
import fr.islandswars.api.storage.StorageManager;
import fr.islandswars.api.storage.StorageType;
import fr.islandswars.api.utils.Preconditions;
import fr.islandswars.core.bukkit.item.InternalItem;
import fr.islandswars.core.bukkit.item.ParametersSuppliers;
import fr.islandswars.core.bukkit.item.PropertiesWrapper;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File <b>StorageFactory</b> located on fr.islandswars.core.bukkit.storage
 * StorageFactory is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 14:41
 * @since 0.2.9
 */
public class StorageFactory implements StorageManager {

    private final AtomicInteger count;
    private final ConcurrentMap<Integer, InternalItem> itemsCache;

    public StorageFactory() {
        this.count = new AtomicInteger(0);
        this.itemsCache = new ConcurrentHashMap<>();
    }

    @Override
    public Storage createStorage(StorageBuilder builder) {
        AbstractStorage storage;
        if (builder.getType() == StorageType.GLOBAL)
            storage = new GlobalStorage(builder.getSize(), builder.getName());
        else
            storage = new PersonnalStorage(builder.getSize(), builder.getName());

        try {
            builder.getPattern().ifPresent(key -> {
                for (int i = 0; i < key.length(); i++) {
                    char c = key.charAt(i);
                    if (c != ' ' && c != 'X')
                        storage.setItem(i, builder.getPatternKey().get(c), null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storage;
    }

    @Override
    public Optional<Item> getItem(org.bukkit.inventory.ItemStack bukkitItem) {
        Preconditions.checkNotNull(bukkitItem);

        ItemStack nmsCopy = CraftItemStack.asNMSCopy(bukkitItem);

        if (nmsCopy == null || !nmsCopy.hasTag() || !nmsCopy.getTag().hasKeyOfType("id", 3))
            return Optional.empty();
        return Optional.ofNullable(itemsCache.get(nmsCopy.getTag().getInt("id")));
    }

    @Override
    public Item newItem(ItemType type) {
        Preconditions.checkNotNull(type);

        InternalItem item = new InternalItem(type, count.incrementAndGet());
        itemsCache.put(item.getId(), item);
        return item;
    }

    public void translateItem(ItemStack item, IslandsPlayer player) {
        InternalItem internalItem = (InternalItem) getItem(item);
        Locale locale = player.getPlayerLocale();
        NBTTagCompound display = new NBTTagCompound();
        Optional<ParametersSuppliers> parameters = internalItem.getSuppliers(player.getCraftPlayer().getUniqueId());

        if (internalItem.getNameTranslationKey() != null) {
            String nameKey = internalItem.getNameTranslationKey();
            if (parameters.isPresent() && parameters.get().nameSupplier != null) {
                Object[] params = parameters.get().nameSupplier.get();
                display.setString("Name", locale.format(nameKey, params));
            } else
                display.setString("Name", locale.format(nameKey));
        }
        if (internalItem.getLoreTranslationKey() != null) {
            String loreKey = internalItem.getLoreTranslationKey();
            NBTTagList loreList = new NBTTagList();
            String lore = null;
            if (parameters.isPresent() && parameters.get().loreSupplier != null) {
                Object[] params = parameters.get().loreSupplier.get();
                lore = locale.format(loreKey, params);
            } else
                lore = locale.format(loreKey);
            String[] loreParts = lore.split("\n");
            for (String line : loreParts)
                loreList.add(new NBTTagString(line));
            display.set("Lore", loreList);
        }
        if (((PropertiesWrapper) internalItem.getProperties()).isPlayerSkullItem()) {
            NBTTagCompound head = new NBTTagCompound();
            GameProfileSerializer.serialize(head, player.getCraftPlayer().getProfile());
            item.getTag().set("SkullOwner", head);
        }
        if (!display.isEmpty())
            item.getTag().set("display", display);

    }

    private Item getItem(ItemStack item) {
        return itemsCache.get(item.getTag().getInt("id"));
    }
}
