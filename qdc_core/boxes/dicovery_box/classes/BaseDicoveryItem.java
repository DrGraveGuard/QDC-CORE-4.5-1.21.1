package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.classes;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.BaseDiscoveryBox;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class BaseDicoveryItem {

	public Item item;
	public String modID;
	public String itemID;
	public String storageStr = "";

	public BaseDicoveryItem(Item item, String modID, String itemID) {

		this.item = item;
		this.modID = modID;
		this.itemID = itemID;
		this.storageStr = modID + BaseDiscoveryBox.VALUE_SEPARATOR + itemID + BaseDiscoveryBox.RECORD_SEPARATOR;
	}

	public BaseDicoveryItem(String storageString) {
		decodeStorageString(storageString);
	}

	private void decodeStorageString(String storageString) {
		this.storageStr = storageString + BaseDiscoveryBox.RECORD_SEPARATOR;

		String[] parts = storageString.split(BaseDiscoveryBox.VALUE_SEPARATOR);

		if (parts.length != 2)
			return;

		modID = parts[0];
		itemID = parts[1];

		ResourceLocation itemId = ResourceLocation.fromNamespaceAndPath(modID, itemID);
		Item item = BuiltInRegistries.ITEM.get(itemId);

		if (item != Items.AIR) {
			this.item = item;
		}

	}

	public boolean isValid() {
		if (item == null)
			return false;

		return true;
	}

	public boolean isSameItem(Item item) {
		if (this.item == item)
			return true;

		return false;
	}
}
