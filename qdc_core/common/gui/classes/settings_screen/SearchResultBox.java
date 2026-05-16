package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.DefaultParticlesBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;

public class SearchResultBox {

	public List<SearchResultWindowTab> windowTabList = new ArrayList<SearchResultWindowTab>();

	public SearchResultWindowTab curWindowTab = null;
	public SearchResultItem curItem = null;
	public int curWindowTabIndex = 1;
	public int windowTabCount = 1;

	public SearchResultBox() {

		doSearch();

	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		if (curWindowTab != null) {
			curWindowTab.checkIfHoveringOver(windowPos, mouseX, mouseY);

			curItem = curWindowTab.curItemHover;

		}
	}

	public void doSearch() {
		windowTabList = new ArrayList<SearchResultWindowTab>();
		populateWindows();

		curWindowTab = null;
		setFirstWindowTab();
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

	public void tick()
	{
		if(curWindowTab!= null)
		curWindowTab.tick();
	}
	
	public void populateWindows() {

		curWindowTabIndex = 1;
		windowTabCount = 1;

		String search = Qdc.SettingsScreenVariables.searchString;

		int tabIndex = 0;

		for (DefaultItemParticles item : DefaultParticlesBox.defaultItems) {
			if(item.itemList != null)
			if (item.isValidForSearchString(search)) {
				if (windowTabList.size() == 0) {

					windowTabList.add(new SearchResultWindowTab());
				}

				if (windowTabList.get(tabIndex).hasSpace) {

				} else {
					windowTabList.add(new SearchResultWindowTab());
					windowTabCount++;
					tabIndex++;
				}

				windowTabList.get(tabIndex).add(new SearchResultItem(item));
			}
		}

	}


	public void handleItemClick(Point recipeWinPos) {
		if (curWindowTab != null) {
			if (curWindowTab.curItemHover != null) {
				curItem = curWindowTab.curItemHover;
				
			}
		}
	}

	public boolean handleNextClick() {
		if (curWindowTabIndex - 1 < windowTabList.size() - 1) {
			curWindowTabIndex++;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			return true;
		}

		return false;
	}

	public boolean handlePrevClick() {
		if (curWindowTabIndex > 1) {
			curWindowTabIndex--;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			return true;
		}
		return false;
	}

	public boolean isSameHoverItem(SearchResultItem searchResultItem) {
		if(this.curItem != null)
			if(this.curItem.itemData != null)
		if(curItem.itemData.id.equals(searchResultItem.itemData.id))
		return true;
		
		return false;
	}

}
