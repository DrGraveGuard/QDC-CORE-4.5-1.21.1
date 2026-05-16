package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentModItem {

	public List<ModItemData> dataItemList = null;
	public List<String> itemIDList = null;

	public Holder<Enchantment> enchantment;

	public EnchantmentModItem(Holder<Enchantment> enchantment) {

		this.enchantment = enchantment;
	}

	public void addIdToList(String id) {
		if (itemIDList == null)
			itemIDList = new ArrayList<String>();

		itemIDList.add(id);

	}
	


	public boolean tryAddDataItem(ModItemData dataItem) {
		if (itemIDList == null)
			return false;

		for (String s : itemIDList) {
			if (s.equals(dataItem.id)) {

				if (dataItemList == null)
					dataItemList = new ArrayList<ModItemData>();

				dataItemList.add(dataItem);

				return true;

			}
		}

		return false;
	}

}
