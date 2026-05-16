package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings;

import net.minecraft.world.item.ItemStack;

public class AffectedItem {

	public ItemStack stack;
	public Point pos;
	public boolean isHoveringOver = false;

	public AffectedItem(ItemStack stack) {

		this.stack = stack;
	}
	
	public void setPos(Point pos)
	{
		this.pos = pos;
	}

	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos,
				SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.ITEMS.SIZE, new Point(mouseX, mouseY));

		return isHoveringOver;

	}

}
