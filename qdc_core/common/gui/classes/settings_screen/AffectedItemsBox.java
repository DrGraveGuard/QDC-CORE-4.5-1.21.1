package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.DefaultParticlesBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.SettingsScreen.GuiButton;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AffectedItemsBox {

	public List<AffectedItemsWindowTab> windowTabList = new ArrayList<AffectedItemsWindowTab>();

	public List<GuiButton> affectedItemsButtonList = null;
	public List<Item> itemList = null;

	public GuiButton btnPrev;
	public GuiButton btnNext;

	public AffectedItemsWindowTab curWindowTab = null;
	public AffectedItem curItem = null;
	public int curWindowTabIndex = 1;
	public int windowTabCount = 1;

	public AffectedItemsBox(List<GuiButton> affectedItemsButtonList, List<Item> itemList) {

		this.affectedItemsButtonList = affectedItemsButtonList;
		this.itemList = itemList;

		extractButtons();

		populateWindows();
		setFirstWindowTab();

	}

	private void extractButtons() {
		btnPrev = affectedItemsButtonList.get(0);
		btnNext = affectedItemsButtonList.get(1);
	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		if (curWindowTab != null) {
			curWindowTab.checkIfHoveringOver(windowPos, mouseX, mouseY);

		}
	}

	public void setFirstWindowTab() {

		curWindowTab = windowTabList.get(0);

		curWindowTabIndex = 1;
		
		handleButtons();
	}

	public void populateWindows() {

		curWindowTabIndex = 1;
		windowTabCount = 1;

		int tabIndex = 0;

		for (Item item : itemList) {

			if (windowTabList.size() == 0) {

				windowTabList.add(new AffectedItemsWindowTab());
			}

			if (windowTabList.get(tabIndex).hasSpace) {

			} else {
				windowTabList.add(new AffectedItemsWindowTab());
				windowTabCount++;
				tabIndex++;
			}

			windowTabList.get(tabIndex).add(new AffectedItem(new ItemStack(item)));
		}

	}
	
	
	

	public boolean handleNextClick() {
		if (curWindowTabIndex - 1 < windowTabList.size() - 1) {
			curWindowTabIndex++;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			
			handleButtons();
			return true;
		}

		return false;
	}

	public boolean handlePrevClick() {
		if (curWindowTabIndex > 1) {
			curWindowTabIndex--;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			
			
			handleButtons();
			
			return true;
		}
		return false;
	}

	
	private void handleButtons()
	{
		
		
		if(curWindowTabIndex == 1)
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
	
	public boolean isSameHoverItem(AffectedItem searchItem) {
		if (this.curItem != null)
			if (curItem.stack.getItem() == searchItem.stack.getItem())
				return true;

		return false;
	}

}
