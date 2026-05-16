package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.MainBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.AssemblerScreen.GuiButton;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

public class AssemblerBox {

	public List<WindowTab> windowTabList = new ArrayList<WindowTab>();

	public WindowTab curWindowTab = null;
	public AssemblerItem curItem = null;
	public int curWindowTabIndex = 1;
	public int windowTabCount = 1;
	public GuiButton btnNext = null;
	public GuiButton btnPrev = null;
	
	
	public AssemblerBox(GuiButton btnPrev, GuiButton btnNext) {

		this.btnNext = btnNext;
		this.btnPrev = btnPrev;
		
		doSearch();

	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		if (curWindowTab != null) {
			curWindowTab.checkIfHoveringOver(windowPos, mouseX, mouseY);


		}
	}

	public void doSearch() {
		windowTabList = new ArrayList<WindowTab>();
		populateWindows();

		curWindowTab = null;
		setFirstWindowTab();
		handleButtons();
	}

	
	private void handleButtons()
	{
		
		
		if(curWindowTabIndex <= 1)
		{
			btnPrev.setEnabledState(false);
		}
		else
		{
			btnPrev.setEnabledState(true);
		}
		
		if(curWindowTabIndex == windowTabList.size())
		{
			btnNext.setEnabledState(false);
		}
		else
		{
			btnNext.setEnabledState(true);
		}
	}
	
	
	public void setFirstWindowTab() {
		if (windowTabList.size() > 0) {
			curWindowTab = windowTabList.get(0);
		} else {
			curWindowTab = null;
			curWindowTabIndex = 0;
			windowTabCount = 0;
		}
	}

	public void updateCurWindow()
	{
		curWindowTab.update();
	}
	
	public void populateWindows() {

		curWindowTabIndex = 1;
		windowTabCount = 1;

		String search = Qdc.AssemblerVariables.searchString;

		int tabIndex = 0;

		for (ModItemData item : RecipeBox.itemList_ordered) {
			if (item.isValidForSearchString(search)) {
				if (windowTabList.size() == 0) {

					windowTabList.add(new WindowTab());
				}

				if (windowTabList.get(tabIndex).hasSpace) {

				} else {
					windowTabList.add(new WindowTab());
					windowTabCount++;
					tabIndex++;
				}

				windowTabList.get(tabIndex).add(new AssemblerItem(item));
			}
		}

	}
	
	public boolean isSameHoverItem(AssemblerItem item)
	{
		if(curWindowTab != null)
			if(curWindowTab.curItemHover != null)
			{
				if(item.itemData.itemStack == curWindowTab.curItemHover.itemData.itemStack)
				{
					return true;
				}
			}
		
		return false;
	}

	public void handleItemClick(Point recipeWinPos) {
		if (curWindowTab != null) {
			if (curWindowTab.curItemHover != null) {
				curItem = curWindowTab.curItemHover;
				curItem.setRecipePosition(recipeWinPos);
				
				if(curItem.itemData.itemParticles != null)
				{
					curItem.itemData.itemParticles.setParticleList();
					
					curItem.itemData.calcCanCreateAmount();
				}
			}
		}
	}

	public boolean handleNextClick() {
		if (curWindowTabIndex - 1 < windowTabList.size() - 1) {
			curWindowTabIndex++;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			curWindowTab.update();
			handleButtons();
			return true;
		}

		return false;
	}

	public boolean handlePrevClick() {
		if (curWindowTabIndex > 1) {
			curWindowTabIndex--;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			curWindowTab.update();
			handleButtons();
			return true;
		}
		return false;
	}

}
