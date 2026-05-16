package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PotionSearchFunctions {

	public static List<ItemStack> getDiscoveredPotionsByVialType(Item vialType) {
		
		
		if (vialType == Items.POTION || vialType == Items.SPLASH_POTION || vialType == Items.LINGERING_POTION) {

			 List<ItemStack> result = new ArrayList<ItemStack>();
			
			for (ModItemData item : RecipeBox.itemList_ordered) {

				if(item.stackType == StackType.POTION)
				{
					if(item.itemStack.getItem() == vialType)
					{
						if(item.isDirectlyDiscovered())
						{
							result.add(item.itemStack.copy());
						}
					}
				}
				
			}
			
			return result;
		}
		
		return null;
	}

}
