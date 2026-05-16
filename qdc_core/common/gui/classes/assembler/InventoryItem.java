package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryItem {

	public int inventoryIndex = -1;
	public ItemStack itemStack;
	public Point pos;

	public ParticleCollection itemParticles = null;
	public boolean isDiscovered = false;
	public boolean isHoveringOver = false;
	public boolean isSameItemClicked = false;

	public InventoryItem(ItemStack itemStack, Point pos, int inventoryIndex, ParticleCollection itemParticles, boolean isDiscovered) {

		this.itemStack = itemStack.copy();
		this.pos = new Point(pos);
		this.inventoryIndex = inventoryIndex;
		this.isDiscovered = isDiscovered;
		this.itemParticles = itemParticles.clone();
	}


	public boolean checkIfClickedItem(int index) {

		if(this.inventoryIndex == index)
			isSameItemClicked = true;
		else
			isSameItemClicked = false;
		
		return isSameItemClicked;

	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.INVENTORY.ITEM.SIZE,
				new Point(mouseX, mouseY));

		return isHoveringOver;

	}

}
