package fr.islandswars.core.bukkit.item;

import fr.islandswars.api.item.ItemProperties;
import fr.islandswars.api.item.ItemType;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

/**
 * File <b>PropertiesWrapper</b> located on fr.islandswars.core.bukkit.item
 * PropertiesWrapper is a part of Islands Wars - Api.
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
 * Created the 27/04/2018 at 17:06
 * @since TODO edit
 */
public class PropertiesWrapper implements ItemProperties {

    private ItemType properties;
    private boolean playerSkullItem;

    PropertiesWrapper(ItemType properties) {
        this.playerSkullItem = false;
        this.properties = properties;
        getTag();
    }

    @Override
    public ItemProperties addEnchantment(Enchantment enchantment, int level) {
        NBTTagCompound tag = getTag();

        if (!tag.hasKeyOfType("ench", 9))
            tag.set("ench", new NBTTagList());

        NBTTagList nbttaglist = tag.getList("ench", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short) enchantment.getId());
        nbttagcompound.setShort("lvl", (short) level);
        nbttaglist.add(nbttagcompound);
        return this;
    }

    @Override
    public ItemProperties addItemFlags(ItemFlag... flags) {
        NBTTagCompound tag = getTag();

        if (!tag.hasKeyOfType("HideFlags", 3))
            tag.setInt("HideFlags", 0);

        int hideFlag = tag.getInt("HideFlags");
        for (ItemFlag flag : flags) {
            hideFlag |= this.getBitModifier(flag);
            properties.getCompound().setInt("HideFlags", hideFlag);
        }

        return this;
    }

    @Override
    public ItemProperties addNBTTag(String key, NBTBase nbtTag) {
        getTag().set(key, nbtTag);
        return this;
    }

    @Override
    public int getAmount() {
        return properties.getAmount();
    }

    @Override
    public short getDurability() {
        return properties.getData();
    }

    @Override
    public Material getMaterial() {
        return properties.getMaterial();
    }

    @Override
    public ItemProperties setAmount(int amount) {
        if (amount < 1 || amount > 128)
            return this;
        properties.writeAmount(amount);
        return this;
    }

    @Override
    public ItemProperties setDurability(short durability) {
        properties.writeData(durability);
        return this;
    }

    @Override
    public ItemProperties setGlowing(boolean value) {
        NBTTagCompound tag = getTag();
        if (value) {
            if (!tag.hasKeyOfType("ench", 9))
                tag.set("ench", new NBTTagList());

            NBTTagList ench = tag.getList("ench", 10);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setShort("id", (short) 250);
            nbttagcompound.setShort("lvl", (short) 1);
            ench.add(nbttagcompound);
        } else {
            if (tag.hasKeyOfType("ench", 9)) {
                NBTTagList ench = tag.getList("ench", 10);
                for (int i = 0; i < ench.size(); i++)
                    if (ench.get(i).getShort("id") == 250)
                        ench.remove(i);
            }
        }

        return this;
    }

    @Override
    public ItemProperties setMaterial(Material material) {
        properties.writeMaterial(material);
        if (properties.getAmount() < 1 || properties.getAmount() > 128)
            setAmount(1);
        return this;
    }

    @Override
    public ItemProperties setUnbreakable(boolean value) {
        String key = "Unbreakable";

        if (value)
            getTag().setBoolean(key, true);
        else
            getTag().remove(key);

        return this;
    }

    @Override
    public ItemProperties usePlayerHead() {
        this.playerSkullItem = true;
        return this;
    }

    @Override
    public ItemProperties clone() {
        try {
            return new PropertiesWrapper(properties.clone());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPlayerSkullItem() {
        return this.playerSkullItem;
    }

    private byte getBitModifier(ItemFlag hideFlag) {
        return (byte) (1 << hideFlag.ordinal());
    }

    private NBTTagCompound getTag() {
        if (!properties.getCompound().hasKey("tag"))
            properties.getCompound().set("tag", new NBTTagCompound());
        return properties.getCompound().getCompound("tag");
    }

    /**
     * @return this item compound
     */
    NBTTagCompound getCompound() {
        return properties.getCompound();
    }
}

