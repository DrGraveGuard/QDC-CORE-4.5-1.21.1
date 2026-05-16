package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.ItemStack;

public class SearchResultItem {

	public DefaultItemParticles itemData;
	public boolean isHoveringOver;
	public Point pos;
	public ItemStack curHoverRecipeItem = null;

	public SearchResultItem(DefaultItemParticles itemData) {
		this.itemData = itemData;

		
	}


	public void setPos(Point pos) {
		this.pos = new Point(pos);
	}

	
	public boolean isSame(SearchResultItem otherResultItem)
	{
		return itemData.isSameID(otherResultItem.itemData.id);
	}


	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		// in item list
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.SIZE,
				new Point(mouseX, mouseY));


		return isHoveringOver;
	}

}
