package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;

public class PotionItemRecipeCollection {

	public List<PotionRecipeItem> recipeList;
	
	public PotionItemRecipeCollection()
	{
		recipeList = new ArrayList<PotionRecipeItem>();
	}
	
	
	
	public void add(PotionItem toAdd)
	{
		if(!existsInList(toAdd))
		{
			recipeList.add(new PotionRecipeItem(toAdd.basePotion, toAdd.ingredient));
		}
	}
	
	public boolean existsInList(PotionItem toAdd)
	{
		for(PotionRecipeItem rec : recipeList)
		{
			if(rec.isSameRecipe(toAdd))
			{
				
				return true;
			}
			
			
		}
		return false;
	}
	
	
}
