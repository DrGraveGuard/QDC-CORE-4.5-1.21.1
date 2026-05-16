package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.store_particle_display;

import java.awt.Color;
import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.Item;

public class ParticleStoreGuiItem {

	public ParticleType type;
	public Item icon;
	public Point pos;
	public Point size;
	public String strVal = "";

	public boolean isHoveringOver = false;

	public ParticleStoreGuiItem(ParticleType type, Point pos) {

		this.type = type;
		this.pos = pos;
		this.size = AssemblerSettings.PARTICLE_STORE_WINDOW.ITEMS.SIZE;
		
		this.strVal = QdcApi.QDC_CORE.FUNCTIONS.getParticleAmountStringFromStorage(type);

		this.icon = QdcApi.QDC_CORE.FUNCTIONS.getParticleIconItem(type);

	}

	public void update() {
		this.strVal = QdcApi.QDC_CORE.FUNCTIONS.getParticleAmountStringFromStorage(type);
	}

	public ParticleStoreGuiItem checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos,size,
				new Point(mouseX, mouseY));

		if (isHoveringOver)
			return this;

		return null;
	}
}
