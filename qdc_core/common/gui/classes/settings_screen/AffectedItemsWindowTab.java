package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings;

public class AffectedItemsWindowTab {

	public boolean hasSpace = true;
	
	public List<AffectedItem> itemList = new ArrayList<AffectedItem>();
	public int curIndex = 0;
	
	public AffectedItem curItemHover = null;
	
	public void add(AffectedItem affectedItem)
	{
		
		
		if(itemList.size() < SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.ITEMS.ITEM_LIST_MAX)
		{
			itemList.add(affectedItem);
			
			itemList.get(curIndex).setPos(SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.ITEMS.ITEM_POS_LIST.get(curIndex));
			
			if(itemList.size() == SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.ITEMS.ITEM_LIST_MAX)
			{
				hasSpace = false;
			}
		}
		
		
		
		
	

		
		curIndex++;
		
		
	}

	
	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover = null;
		
		for(AffectedItem ai : itemList)
		{
			if(ai.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = ai;
				break;
			}
		}
		
	}
	
	
}
