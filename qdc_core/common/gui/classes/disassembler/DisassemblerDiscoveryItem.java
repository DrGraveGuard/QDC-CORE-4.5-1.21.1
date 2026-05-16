package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.ItemStack;

public class DisassemblerDiscoveryItem {
	
	
	public ItemStack stack;
	public Point pos;
	public boolean isHoveringOver;
	public String id;
	
	public DisassemblerDiscoveryItem(ItemStack stack, Point pos) {
		this.stack =stack;
		this.pos = pos;
		id = GlobalFuncs.generateItemDataString(stack);
	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		// in item list
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.SIZE,
				new Point(mouseX, mouseY));
		
		return isHoveringOver;
	}
	
	public boolean isSame(DisassemblerDiscoveryItem other)
	{
		if(this.id.equals(other.id))
			return true;
		
		return false;
	}

	public void setPos(Point pos) {
		this.pos = new Point(pos);
	}

}
