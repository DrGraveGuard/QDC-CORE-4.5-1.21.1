package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

public class WindowTab {

	public boolean hasSpace = true;
	
	public List<AssemblerItem> itemList = new ArrayList<AssemblerItem>();
	public int curIndex = 0;
	
	public AssemblerItem curItemHover = null;
	
	public void add(AssemblerItem assemblerItem)
	{
		
		
		if(itemList.size() < AssemblerSettings.CONSTANTS.ITEM_LIST_MAX)
		{
			itemList.add(assemblerItem);
			
			itemList.get(curIndex).setPos(AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.ITEM_POS_LIST.get(curIndex));
			
			if(itemList.size() == AssemblerSettings.CONSTANTS.ITEM_LIST_MAX)
			{
				hasSpace = false;
			}
		}
		
		
		
		
	

		
		curIndex++;
		
		
	}
	
	public void update()
	{
		for(AssemblerItem item : itemList)
		{
			item.update();
		}
	}
	
	
	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover = null;
		
		for(AssemblerItem ai : itemList)
		{
			if(ai.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = ai;
				break;
			}
		}
		
	}
	
	
}
