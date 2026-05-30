package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.DiscoveredEnchantmentDataItem;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.EnchantmentModItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.RecipeBoxFunctions;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RecipeBox {

	public static List<ModItemData> itemList = new ArrayList<ModItemData>();
	public static List<ModItemData> itemList_ordered = new ArrayList<ModItemData>();

	public static List<EnchantmentModItem> enchantmentList = new ArrayList<EnchantmentModItem>();

	public static void clear() {
		itemList = new ArrayList<ModItemData>();
		itemList_ordered = new ArrayList<ModItemData>();
		enchantmentList = new ArrayList<EnchantmentModItem>();
	}

	public static void addModItem(ModItemData modItem) {
		if (modItem.itemStack.getItem() == Items.POTION || modItem.itemStack.getItem() == Items.SPLASH_POTION
				|| modItem.itemStack.getItem() == Items.LINGERING_POTION
				|| modItem.itemStack.getItem() == Items.ENCHANTED_BOOK
				|| modItem.itemStack.getItem() == Items.FIREWORK_ROCKET
				|| modItem.itemStack.getItem() == Items.SUSPICIOUS_STEW
				|| modItem.itemStack.getItem() == Items.TIPPED_ARROW) {

			itemList.add(modItem);
		} else {
			if (getDataItemFromUnorderedList(modItem.itemStack.getItem()) == null)
				itemList.add(modItem);
		}

	}

	
	public static void addMainParticleItems()
	{
		
	}
	
	
	public static List<DiscoveredEnchantmentDataItem> getAllDiscoveredEnchantments()
	{
		List<DiscoveredEnchantmentDataItem> res = new ArrayList<DiscoveredEnchantmentDataItem>();
	
		
		for(EnchantmentModItem ench : enchantmentList)
		{
			res.add(new DiscoveredEnchantmentDataItem(ench));
		}
		
	
		return res;
	
	}
	
	
	public static void fillEnchantmentList()
	{
		for(ModItemData item : itemList_ordered)
		{
			if(item.stackType == StackType.ENCHANTMENT)
			{
				for(EnchantmentModItem enchItem : enchantmentList)
				{
					if(enchItem.tryAddDataItem(item))
						break;
				}
			}
		}
		
		
	}
	
	private static void display()
	{
		GlobalFuncs.line();
		GlobalFuncs.msg("enchantment item list");
		GlobalFuncs.line();
		
		
		for(EnchantmentModItem ench : enchantmentList)
		{
			GlobalFuncs.line();
			for(ModItemData item : ench.dataItemList)
			{
				GlobalFuncs.msg(item.name + " " + item.id);
			}
		}
		
		
		
		
		
	}
	
	
	public static void generateOrderedItemList() {
		RecipeBoxFunctions.generateOrderedItemList(itemList);
		
	}

	public static ModItemData getDataItemFromUnorderedList(Item item) {
		for (ModItemData dataItem : itemList) {
			if (dataItem.itemStack.getItem() == item)
				return dataItem;
		}

		return null;
	}

	public static ModItemData getDataItem(ItemStack stack) {

		String itemID = GlobalFuncs.generateItemDataString(stack);

		for (ModItemData dataItem : itemList_ordered) {

			if (dataItem.id.equals(itemID)) {
				return dataItem;
			}
		}

		return null;

	}

	public static ModItemData getDataItem(String itemID) {

		for (ModItemData dataItem : itemList_ordered) {

			if (dataItem.id.equals(itemID)) {
				return dataItem;
			}
		}

		return null;

	}

	public static List<ModItemData> getUndiscoveredItemList() {
		List<ModItemData> res = new ArrayList<ModItemData>();

		for (ModItemData data : itemList_ordered) {
			if (!data.isDirectlyDiscovered()) {
				res.add(data);
			}
		}

		return res;
	}

}
