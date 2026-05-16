package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler;

import java.awt.Point;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class InventoryBox {

	public List<DisassemblerInventoryItem> inventoryItemList = AssemblerSettings.INVENTORY.generateInventoryItemList();

	public DisassemblerInventoryItem curItemHover = null;
	public DisassemblerInventoryItem curClickedItem = null;


	
	public void updateCurDisassemblyItem()
	{
		if(curClickedItem != null)
		{
			if(curClickedItem.stack.getItem() == Items.AIR)
			{
				curClickedItem = null;
			}
		}
	}
	
	
	
	
	
	
	
	public InventoryBox(Player player) {

		refresh(player);
	}
	
	public void refresh(Player player)
	{
		for (int i = 0; i < 36; i++) {
			DisassemblerInventoryItem item = inventoryItemList.get(i);

			item.setStack(player.getInventory().getItem(item.actualIndex));
		}

	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover =null;
		
		for(DisassemblerInventoryItem item : inventoryItemList)
		{
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = item;
				break;
			}
		}
		
	}


}
