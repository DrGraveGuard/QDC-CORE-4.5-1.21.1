package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;

public class RecipeBoxFunctions {

	public static void generateOrderedItemList(List<ModItemData> itemList)
	{
		List<String> nameList = new ArrayList<String>();
		List<String> orderedNameList = new ArrayList<String>();
		
		for(ModItemData item : itemList)
		{
			nameList.add(item.id);
		}
		
		Collections.sort(nameList);
		
		
		for(String str : nameList)
		{
			for(ModItemData item : itemList)
			{
				if(item.id.equals(str))
				{
					RecipeBox.itemList_ordered.add(item);
					break;
				}
			}
		}
		
		
	}
}
