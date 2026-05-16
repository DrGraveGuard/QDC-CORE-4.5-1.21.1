package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.INFO_WINDOW;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AssemblerItem {

	public ModItemData itemData;
	public boolean isHoveringOver;
	public Point pos;
	public ItemStack curHoverRecipeItem = null;
	public String curHoverRecipeItemID = null;
	public boolean isHoveringOverMainItem = false;
	public AssemblerItem(ModItemData itemData) {
		this.itemData = itemData;

		update();
	}

	public void update() {
		this.itemData.checkCanCreate();
	}

	public void setPos(Point pos) {
		this.pos = new Point(pos);
	}

	public void setRecipePosition(Point RecipeWinPos) {
		itemData.itemRecipeCollection.setItemPositions(RecipeWinPos);
	}

	
	public boolean isSame(AssemblerItem other)
	{
		if(itemData.id.equals(other.itemData.id))
			return true;
		
		return false;
	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		// in item list
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.SIZE,
				new Point(mouseX, mouseY));

		// in recipeLIst
		curHoverRecipeItem = itemData.itemRecipeCollection.checkIfHoveringOver(windowPos, mouseX, mouseY);

		if (curHoverRecipeItem != null)
			curHoverRecipeItemID = GlobalFuncs.generateItemDataString(curHoverRecipeItem);
		else
			curHoverRecipeItemID = null;


		isHoveringOverMainItem = GuiFunctions.isHoveringOver(windowPos, INFO_WINDOW.MAIN_ITEM.POS, INFO_WINDOW.MAIN_ITEM.SIZE, new Point(mouseX, mouseY));

		
		return isHoveringOver;
	}

}
