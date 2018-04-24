package fr.islandswars.core.bukkit.storage;

import fr.islandswars.api.item.Item;
import fr.islandswars.api.storage.Storage;
import fr.islandswars.api.storage.StorageIndex;
import fr.islandswars.api.utils.Preconditions;
import java.util.Optional;

/**
 * File <b>StorageWrapper</b> located on fr.islandswars.core.bukkit.storage
 * StorageWrapper is a part of Islands Wars - Api.
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
 * Created the 24/04/2018 at 16:55
 * @since 0.2.9
 */
public class StorageWrapper implements Storage {

	private final StorageIndex[] items;
	private final int            size;
	String name;

	StorageWrapper(String name, int size) {
		this.items = new StorageIndex[size];
		this.size = size;
		this.name = name;
	}

	@Override
	public void addItem(Item item) {
		Preconditions.checkNotNull(item);

		try {
			int index = getNextSlot();
			setItem(getRow(index), getColumn(index), item);
		} catch (UnsupportedOperationException exc) {
			exc.printStackTrace();
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public StorageIndex[] getSlots() {
		return items;
	}

	@Override
	public void removeItem(int row, int column) {
		Preconditions.checkState(column, ref -> ref >= 0 && ref <= 8);
		Preconditions.checkState(row, ref -> ref >= 0);

		int          index   = row * 9 + column - 1;
		StorageIndex storage = items[index];
		if (storage != null)
			storage.setItem(null);
	}

	@Override
	public void setItem(int row, int column, Item item) {
		Preconditions.checkNotNull(item);
		Preconditions.checkState(column, ref -> ref >= 0 && ref <= 8);
		Preconditions.checkState(row, ref -> ref >= 0);

		int          index   = row * 9 + column - 1;
		StorageIndex storage = items[index];
		if (storage == null)
			items[index] = new BukkitStorageIndexWrapper(item);
		else
			storage.setItem(item);
	}

	private int getNextSlot() {
		for (int i = 0; i < size - 1; i++) {
			StorageIndex storage = items[i];
			if (storage == null || !storage.getItem().isPresent())
				return i;
		}
		throw new UnsupportedOperationException("All slots from 0 to " + size + " aren't empty");
	}

	void clear() {
		for (int i = 0; i < size - 1; i++)
			items[i] = null;
	}

	private class BukkitStorageIndexWrapper implements StorageIndex {

		private Item item;

		BukkitStorageIndexWrapper(Item item) {
			setItem(item);
		}

		@Override
		public Optional<Item> getItem() {
			return Optional.ofNullable(item);
		}

		@Override
		public void setItem(Item item) {
			this.item = item;
		}
	}

}
