package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.ItemStack;

public class DisassemblerDiscoveryItemCollection {

	public List<DisassemblerDiscoveryItem> lastDiscoveredItemList = new ArrayList<DisassemblerDiscoveryItem>();

	public DisassemblerDiscoveryItem curHoverItem = null;
	
	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curHoverItem = null;
		
		for(DisassemblerDiscoveryItem item : lastDiscoveredItemList)
		{
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curHoverItem = item;
			}
		}
	}
	
	public void populate()
	{
		lastDiscoveredItemList = new ArrayList<DisassemblerDiscoveryItem>();
		
		List<Point> DISCOVERY_ITEM_POS_LIST = AssemblerSettings.DISCOVERY_WINDOW.discoveryPosList;
		List<ItemStack> lastDiscoveredItems = Qdc.DisassemblerVariables.getDiscoveredItems();
		
		
		
		for(int i =0;i< lastDiscoveredItems.size();i++)
		{
			lastDiscoveredItemList.add(new DisassemblerDiscoveryItem(lastDiscoveredItems.get(i), DISCOVERY_ITEM_POS_LIST.get(i)));
		}
	}
	
}
