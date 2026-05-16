package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.FlowerEffectItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemRecipeCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.MainRecipeType;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SusStewFunctions {

	public static List<FlowerEffectItem> flowerList = null;

	public static void clear() {
		flowerList = null;
	}

	public static void registerSusStews() {
		if (flowerList == null) {
			flowerList = new ArrayList<>();
		}

		for (Item i : ItemRecipeFuctions.ingredients) {
			ItemStack newStack = new ItemStack(i);

			if (GlobalFuncs.isFlower(newStack)) {
				
				
				
				Holder<MobEffect> effect = GlobalFuncs.getFlowerEffect(newStack);

				if (effect != null)
					addFlower(new FlowerEffectItem(effect, newStack));
			}
			
		}
		
		createStews();

	}

	private static void createStews() {
		for (FlowerEffectItem item : flowerList) {

			ItemStack res = new ItemStack(Items.SUSPICIOUS_STEW);

			res.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, GlobalFuncs.getFlowerSusEffect(item.flowerList.get(0)));
			
			
			
			ModItemData result = new ModItemData(res.copy(),StackType.SUS_STEW);
			result.appendEffectToName(item.effectName);
			
			
			ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

			ModRecipe modRecipeBase = new ModRecipe();

			modRecipeBase.addNewSlot(new ItemStack[] { res.copy() });

			recipeCollection.addRecipe(modRecipeBase);

			ModRecipe modRecipe = new ModRecipe();
			
			modRecipe.setMainRecipeType(MainRecipeType.SUS_STEW);

			
			modRecipe.addNewSlot(new ItemStack[] {new ItemStack(Items.BOWL)});
			modRecipe.addNewSlot(new ItemStack[] {new ItemStack(Items.RED_MUSHROOM)});
			modRecipe.addNewSlot(new ItemStack[] {new ItemStack(Items.BROWN_MUSHROOM)});
			
			modRecipe.addNewSlot(item.toArray());
			

			
			
			recipeCollection.addRecipe(modRecipe);
			result.setRecipes(recipeCollection);

			RecipeBox.addModItem(result);
			
		}

	}

	private static void addFlower(FlowerEffectItem flowerItem) {

		for (FlowerEffectItem item : flowerList) {
			if (item.tryMerge(flowerItem))
				return;
		}

		flowerList.add(flowerItem);

	}

}
