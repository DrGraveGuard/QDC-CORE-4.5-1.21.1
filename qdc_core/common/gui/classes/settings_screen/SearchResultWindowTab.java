package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings;

public class SearchResultWindowTab {

	public boolean hasSpace = true;
	
	public List<SearchResultItem> itemList = new ArrayList<SearchResultItem>();
	public int curIndex = 0;
	
	public SearchResultItem curItemHover = null;
	
	public void add(SearchResultItem assemblerItem)
	{
		
		
		if(itemList.size() < SettingsScreenSettings.CONSTANTS.ITEM_LIST_MAX)
		{
			itemList.add(assemblerItem);
			
			itemList.get(curIndex).setPos(SettingsScreenSettings.SEARCH_RESULT_WINDOW.ITEMS.ITEM_POS_LIST.get(curIndex));
			
			if(itemList.size() == SettingsScreenSettings.CONSTANTS.ITEM_LIST_MAX)
			{
				hasSpace = false;
			}
		}
		
		
		
		
	

		
		curIndex++;
		
		
	}
	
	public void tick()
	{
		for(SearchResultItem item : itemList)
		{
			item.itemData.tick();
		}
	}
	
	
	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover = null;
		
		for(SearchResultItem ai : itemList)
		{
			if(ai.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = ai;
				break;
			}
		}
		
	}
	
	
}
