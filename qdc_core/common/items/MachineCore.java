package com.qdc_mod.qdc_core_4_5.qdc_core.common.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MachineCore extends Item {

	public MachineCore(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
