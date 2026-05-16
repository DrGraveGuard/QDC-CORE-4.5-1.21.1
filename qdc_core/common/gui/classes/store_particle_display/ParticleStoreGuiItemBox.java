package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.store_particle_display;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleItem;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.CONSTANTS;

public class ParticleStoreGuiItemBox {

	public List<ParticleStoreGuiItem> particleList;

	public ParticleStoreGuiItem curHoverItem = null;
	
	public ParticleStoreGuiItemBox() {
		setup();

	}

	private void setup() {

		particleList = new ArrayList<ParticleStoreGuiItem>();
		
		addNewItem(ParticleType.NATURE,0);
		addNewItem(ParticleType.FOOD,1);
		addNewItem(ParticleType.METAL,2);
		addNewItem(ParticleType.GEM,3);
		addNewItem(ParticleType.ENCHANTED,4);
		addNewItem(ParticleType.POTION,5);
		
	}

	private void addNewItem(ParticleType type, int index)
	{
		particleList.add(new ParticleStoreGuiItem(type, AssemblerSettings.PARTICLE_STORE_WINDOW.ITEMS.ITEM_POS_LIST.get(index)));
		
		
	}
	
	public void update()
	{
		if(particleList != null)
		for(ParticleStoreGuiItem item : particleList)
		{
			item.update();
		}
	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		curHoverItem = null;
		
		if(particleList == null)
			return false;
		
		
		
		for(ParticleStoreGuiItem item : particleList)
		{
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY) != null)
			{
				curHoverItem = item;
				return true;
			}
		}

		
		return false;
	}
	
}
