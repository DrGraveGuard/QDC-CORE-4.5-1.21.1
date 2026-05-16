package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;

import net.minecraft.world.item.ItemStack;

public class PotionRecipeItem {

	public ItemStack basePotion;
	public ItemStack ingredient;
	public String basePotionID;
	public String ingredientID;
	
	public PotionRecipeItem(ItemStack basePotion, ItemStack ingredient) {


		this.basePotion = basePotion.copy();
		this.ingredient = ingredient.copy();
		
		this.basePotionID = GlobalFuncs.generateItemDataString(basePotion);
		this.ingredientID = GlobalFuncs.generateItemDataString(ingredient);
	}
	
	
	public boolean isSameRecipe(PotionItem compareItem) {

		if(this.basePotionID.equals(compareItem.basePotionID) && this.ingredientID.equals(compareItem.ingredientID))
			return true;
		
		return false;
		
	}
}
