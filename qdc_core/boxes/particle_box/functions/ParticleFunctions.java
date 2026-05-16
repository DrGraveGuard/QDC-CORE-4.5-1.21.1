package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ParticleFunctions {

	public static ParticleCollection getItemParticles(ItemStack stack) {
		ModItemData itemData = RecipeBox.getDataItem(stack);

		if (itemData == null)
			return null;

		if (itemData.itemParticles == null)
			return null;

		return itemData.itemParticles.clone();
	}

	public static boolean itemHasParticles(ItemStack stack) {
		ModItemData itemData = RecipeBox.getDataItem(stack);

		if (itemData == null)
			return false;
		
		return true;
	}

}
