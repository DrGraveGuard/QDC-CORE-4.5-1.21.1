package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PotionItem {

	public ItemStack potionItem;
	public String potionName;
	public int duration;
	public int level;
	public ItemStack basePotion;
	public ItemStack ingredient;
	public String basePotionID;
	public String ingredientID;
	
	public  PotionItemRecipeCollection recipes = null;

	public PotionItem(ItemStack potionItem, ItemStack basePotion, ItemStack ingredient) {

		this.potionItem = potionItem.copy();
		this.potionName = potionItem.getDisplayName().getString();
		this.duration = PotionInitFunctions.getPotionDuration(potionItem);
		this.level = PotionInitFunctions.getPotionLevel(potionItem);
		this.basePotion = basePotion.copy();
		this.ingredient = ingredient.copy();
		
		this.basePotionID = GlobalFuncs.generateItemDataString(basePotion);
		this.ingredientID = GlobalFuncs.generateItemDataString(ingredient);
	}

	public boolean isLingeringPotion()
	{
		if(potionItem.getItem() == Items.LINGERING_POTION)
			return true;
		
		return false;
	}
	
	public boolean isSamePotion(PotionItem compareItem) {
		if (this.potionName.equals(compareItem.potionName) && this.duration == compareItem.duration
				&& this.level == compareItem.level)
			return true;

		return false;

	}

	public boolean isSameRecipe(PotionItem compareItem) {

		if(this.basePotionID.equals(compareItem.basePotionID) && this.ingredientID.equals(compareItem.ingredientID))
			return true;
		
		return false;
		
	}
	
	public void addRecipeToList(PotionItem toAdd)
	{
		if(recipes == null)
		recipes = new PotionItemRecipeCollection();
		
		recipes.add(toAdd);
	}
	
}
