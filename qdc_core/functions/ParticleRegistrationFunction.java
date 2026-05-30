package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemRecipeCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ParticleRegistrationFunction {

	public static void registerMainParticleItems()
	{
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE, ParticleType.NATURE);
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.FOOD, ParticleType.FOOD);
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.METAL, ParticleType.METAL);
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.GEM, ParticleType.GEM);
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.ENCHANTED, ParticleType.ENCHANTED);
		registerParticleModItemData(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.POTION, ParticleType.POTION);
	}
	
	private static void registerParticleModItemData(Item item, ParticleType type)
	{
		ItemStack itemStack = new ItemStack(item);
		
		ModItemData result = new ModItemData(itemStack, StackType.ENCHANTMENT);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();
		ModRecipe modRecipe = new ModRecipe();

		modRecipe.addNewSlot(new ItemStack[] { itemStack.copy() });

		recipeCollection.addRecipe(modRecipe);
		result.setRecipes(recipeCollection);
		
		result.itemParticles = new ParticleCollection();
		
		result.itemParticles.addParticles(type, 1);; 
		
		RecipeBox.addModItem(result);
	}
	
}
