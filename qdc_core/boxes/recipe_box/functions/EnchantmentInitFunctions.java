package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.EnchantmentModItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemRecipeCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.EnchantmentFunctions;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentInitFunctions {

	public static void setupEchantmentItems(ServerLevel serverLevel) {

		// Get the enchantment registry from the world's registry access
		Registry<Enchantment> enchantmentRegistry = serverLevel.registryAccess()
				.registryOrThrow(Registries.ENCHANTMENT);

		// Stream through the registry to get all entries
		enchantmentRegistry.holders().forEach(holder -> {

			Enchantment enc = enchantmentRegistry.getOrThrow(holder.key());

			EnchantmentModItem tempEnchModItem = new EnchantmentModItem(holder);
			
			for (int i = 1; i <= enc.getMaxLevel(); i++) {
				ItemStack is = EnchantmentFunctions.createEnchantedBook(holder, i);


				
				ModItemData newDataItem = registerEnchantedBook(is);

				newDataItem.itemParticles = new ParticleCollection();
				
				newDataItem.itemParticles.addEnchantedParticles(calcEnchantmentParticles(i)); 
				
				RecipeBox.addModItem(newDataItem);
				
				tempEnchModItem.addIdToList(newDataItem.id);
			}
			
			RecipeBox.enchantmentList.add(tempEnchModItem);
		});

	}


	private static ModItemData registerEnchantedBook(ItemStack itemStack) {
		ModItemData result = new ModItemData(itemStack, StackType.ENCHANTMENT);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();
		ModRecipe modRecipe = new ModRecipe();

		modRecipe.addNewSlot(new ItemStack[] { itemStack.copy() });

		recipeCollection.addRecipe(modRecipe);
		result.setRecipes(recipeCollection);

		return result;
	}
	
	private static double calcEnchantmentParticles(int enchantmentLevel)
	{
		return EnchantmentFunctions.calcEnchantmentParticles(enchantmentLevel);
	}

}
