package com.qdc_mod.qdc_core_4_5.api;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.EnchantmentModItem;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class DiscoveredEnchantmentDataItem {

	public Holder<Enchantment> enchantment;
	public ItemStack[] bookList = null;
	public int discoveryAmount = 0;
	
	public DiscoveredEnchantmentDataItem(EnchantmentModItem enchDataItem)
	{
		this.enchantment = enchDataItem.enchantment;
		
		bookList = new ItemStack[enchDataItem.dataItemList.size()];
		
		for(int i = 0; i < enchDataItem.dataItemList.size();i++)
		{
			
			
			if(enchDataItem.dataItemList.get(i).isDirectlyDiscovered())
			{
				
				bookList[i] = enchDataItem.dataItemList.get(i).itemStack.copy();
				discoveryAmount++;
			}
			else
			{
				bookList[i] = null;
			}
		}
	}
	
	
}
