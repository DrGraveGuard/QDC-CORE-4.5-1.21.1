package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler;


import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;

import net.minecraft.world.item.ItemStack;



public class InventoryItem {

	public int inventoryIndex;
	public ParticleCollection itemParticles;
	public ItemStack itemStack;
	public boolean isDiscovered;
	
	public InventoryItem(int inventoryIndex, ParticleCollection itemParticles, ItemStack itemStack,boolean isDiscovered)
	{
		this.inventoryIndex = inventoryIndex;
		this.itemParticles = itemParticles.clone();
		this.itemStack = itemStack;
		this.isDiscovered = isDiscovered;
	}
}
