package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemRecipeCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.PotionItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.PotionRecipeItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.MainRecipeType;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

public class PotionInitFunctions {

	public static final String WATER_BOTTLE_ID_STRING = generateWaterBottleIdString(Items.POTION);
	public static final String SPLASH_WATER_BOTTLE_ID_STRING = generateWaterBottleIdString(Items.SPLASH_POTION);
	public static final String LINGERING_WATER_BOTTLE_ID_STRING = generateWaterBottleIdString(Items.LINGERING_POTION);

	private static String[] potionIgnoreList = new String[] { "[Mundane Potion]", "[Mundane Lingering Potion]", "[Mundane Splash Potion]",
			 "[Thick Potion]", "[Thick Lingering Potion]", "[Thick Splash Potion]" };

	public static List<PotionItem> vials = new ArrayList<PotionItem>();
	public static List<PotionItem> allPotions = new ArrayList<PotionItem>();

	public static List<PotionItem> tempPotList = new ArrayList<PotionItem>();
	
	public static List<PotionItem> finalPotionList = new ArrayList<PotionItem>();

	
	public static void clear()
	{
		vials = new ArrayList<PotionItem>();
		allPotions = new ArrayList<PotionItem>();
		tempPotList = new ArrayList<PotionItem>();
		finalPotionList = new ArrayList<PotionItem>();
	}
	
	
	
	
	public static void registerPotions(ServerLevel serverLevel) {

		addWaterBottle();

		getAllPotionItems(serverLevel);

		for (PotionItem potStack : allPotions) {
			
			PotionItem searchResult = getFromFinalPotionList(potStack);
			
			if(searchResult == null)
			{
				PotionItem tempPotItem = new PotionItem(potStack.potionItem, potStack.basePotion, potStack.ingredient);
				
				tempPotItem.addRecipeToList(potStack);
				
				finalPotionList.add(tempPotItem);
			}
			else
			{
				searchResult.addRecipeToList(potStack);
			}
		}
		

		for (PotionItem potStack : finalPotionList) {
			RecipeBox.addModItem(createPotionModItemDataForPotion(potStack));
		}
		
		registerAllTippedArrows();

	}

	
	public static void registerAllTippedArrows()
	{
		for(PotionItem pot : finalPotionList)
		{
			if(pot.isLingeringPotion())
			{
				
				registerTippedArrow(pot.potionItem);
			}
		}
	}
	
	private static void registerTippedArrow(ItemStack potion)
	{
		ItemStack tempArrow = createTippedArrow(potion);

		ModItemData result = new ModItemData(tempArrow.copy(), StackType.ARROW);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe discoveryRecipe = new ModRecipe();
		discoveryRecipe.addNewSlot(new ItemStack[] { tempArrow.copy() });

		ModRecipe baseRecipe = new ModRecipe();
		baseRecipe.addNewSlot(new ItemStack[] { potion.copy()});
		baseRecipe.addNewSlot(new ItemStack(Items.ARROW),8);
		baseRecipe.setMakeAmount(8);
		
		recipeCollection.addRecipe(discoveryRecipe);
		recipeCollection.addRecipe(baseRecipe, MainRecipeType.ARROW);

		result.setRecipes(recipeCollection);

		RecipeBox.addModItem(result);
	}
	
	
	
	private static ItemStack createTippedArrow(ItemStack potion)
	{
		
	    ItemStack tippedArrow = new ItemStack(Items.TIPPED_ARROW, 8);

	    PotionContents contents = potion.get(DataComponents.POTION_CONTENTS);

	    if (contents != null) {
	        tippedArrow.set(DataComponents.POTION_CONTENTS, contents);
	    }

	    return tippedArrow;
	}
	
	
	private static PotionItem getFromFinalPotionList(PotionItem search)
	{
		for (PotionItem potStack : finalPotionList) {
			
			if(potStack.isSamePotion(search))
				return potStack;
		}
		
		return null;
			
	}
	
	
	
	private static void addWaterBottle() {
		ItemStack tempPot = makePotionFromHolder(Items.POTION, Potions.WATER);

		ModItemData result = new ModItemData(tempPot.copy(), StackType.POTION);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe discoveryRecipe = new ModRecipe();
		discoveryRecipe.addNewSlot(new ItemStack[] { tempPot.copy() });

		ModRecipe baseRecipe = new ModRecipe();
		baseRecipe.addNewSlot(new ItemStack[] { new ItemStack(Items.GLASS_BOTTLE) });

		recipeCollection.addRecipe(discoveryRecipe);
		recipeCollection.addRecipe(baseRecipe, MainRecipeType.POTION);

		result.setRecipes(recipeCollection);

		RecipeBox.addModItem(result);
	}

	private static ModItemData createPotionModItemDataForPotion(PotionItem potItem) {
		ModItemData result = new ModItemData(potItem.potionItem.copy(), StackType.POTION);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe discoveryRecipe = new ModRecipe();
		discoveryRecipe.addNewSlot(new ItemStack[] { potItem.potionItem.copy() });
		recipeCollection.addRecipe(discoveryRecipe);
		
		
		for(PotionRecipeItem item : potItem.recipes.recipeList)
		{
			ModRecipe baseRecipe = new ModRecipe();
			baseRecipe.addNewSlot(new ItemStack[] { item.basePotion.copy() });
			baseRecipe.addNewSlot(new ItemStack[] { item.ingredient.copy() });
			recipeCollection.addRecipe(baseRecipe, MainRecipeType.POTION);
		}
		
		

		
		

		result.setRecipes(recipeCollection);


		return result;
	}

	private static PotionItem tryBrewPotion(ItemStack potion, ItemStack ingredient, PotionBrewing brewing) {

		if (brewing.hasMix(potion, ingredient)) {
			ItemStack result = brewing.mix(ingredient, potion);
			// if(!potionExingredientts(potionItem))

			if (!isInIgnoreList(result)) {
//				GlobalFuncs.msg(
//						"from " + potion.getDisplayName().getString() + " __ " + ingredient.getDisplayName().getString()
//								+ " -> " + result.getDisplayName().getString() + " __ " + getPotionDuration(potion) + " __ " + getPotionDuration(result));

				return new PotionItem(result, potion, ingredient);

			}
		}

		return null;

	}

	public static int getPotionDuration(ItemStack stack) {
		PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
		if (contents != null) {
			for (MobEffectInstance instance : contents.getAllEffects()) {
				return instance.getDuration(); // Duration in ticks
			}
		}

		return -1;
	}

	public static int getPotionLevel(ItemStack stack) {
		PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
		if (contents != null) {
			for (MobEffectInstance instance : contents.getAllEffects()) {
				return instance.getAmplifier(); // Duration in ticks
			}
		}

		return -1;
	}

	private static boolean isInIgnoreList(ItemStack potion) {
		String name = potion.getDisplayName().getString();

		for (String s : potionIgnoreList) {
			if (s.equals(name))
				return true;
		}

		return false;
	}

	private static ItemStack makePotionFromHolder(Item vialType, Holder<Potion> potionHolder) {
		ItemStack tempPot = new ItemStack(vialType);

		tempPot.set(DataComponents.POTION_CONTENTS, new PotionContents(potionHolder));

		return tempPot;
	}

	private static void tryBrewPotionList(List<PotionItem> toLoop, List<PotionItem> toAddTo, ItemStack ingredient,
			PotionBrewing brewing) {
		for (PotionItem pot : toLoop) {
			PotionItem tempPotionItem = tryBrewPotion(pot.potionItem, ingredient, brewing);

			if (tempPotionItem != null) {
				
					toAddTo.add(tempPotionItem);
				
			}

		}
	}

	private static boolean potionExistsInList(List<PotionItem> toSearch, PotionItem potItem) {
		for (PotionItem item : toSearch) {
			if (item.isSamePotion(potItem))
				if(item.isSameRecipe(potItem))
				return true;
		}

		return false;
	}

	
	private static void applyEeffectToPotions(PotionBrewing brewing)
	{
		ItemStack ingredient = new ItemStack(Items.REDSTONE);

		tryBrewPotionList(allPotions, tempPotList, ingredient, brewing);
		

		
		allPotions.addAll(tempPotList);
		tempPotList = new ArrayList<PotionItem>();
		
		// ------------------------------------
		// enhance potions

		ingredient = new ItemStack(Items.GLOWSTONE_DUST);

		tryBrewPotionList(allPotions, tempPotList, ingredient, brewing);

		
		allPotions.addAll(tempPotList);
		tempPotList = new ArrayList<PotionItem>();
		
		
		// ------------------------------------
		// make corrupt potions

		 ingredient = new ItemStack(Items.FERMENTED_SPIDER_EYE);

		tryBrewPotionList(allPotions, tempPotList, ingredient, brewing);

		
		allPotions.addAll(tempPotList);
		tempPotList = new ArrayList<PotionItem>();
	}
	
	private static void getAllPotionItems(ServerLevel serverLevel) {

		List<ItemStack> allItemStacks = BuiltInRegistries.ITEM.stream().map(ItemStack::new).toList();
		List<ItemStack> ingredients = new ArrayList<ItemStack>();

		PotionBrewing brewing = serverLevel.potionBrewing();

		for (ItemStack is : allItemStacks) {
			if (brewing.isIngredient(is)) {
				
				ingredients.add(is);
			}
		}

		

		// ------------------------------------
		// from water potion

		ItemStack tempPot = makePotionFromHolder(Items.POTION, Potions.WATER);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}
		
		applyEeffectToPotions(brewing);

		

		// ------------------------------------
		// from water potion

		tempPot = makePotionFromHolder(Items.SPLASH_POTION, Potions.WATER);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}
		
		applyEeffectToPotions(brewing);
		
	

		// ------------------------------------
		// from water potion

		tempPot = makePotionFromHolder(Items.LINGERING_POTION, Potions.WATER);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}
		
		
		
		
		applyEeffectToPotions(brewing);
		
		

		
		// ------------------------------------
		// from awkward potion

		tempPot = makePotionFromHolder(Items.POTION, Potions.AWKWARD);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}
		
		applyEeffectToPotions(brewing);
		
		
		// ------------------------------------
		// from awkward potion

		tempPot = makePotionFromHolder(Items.SPLASH_POTION, Potions.AWKWARD);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}
		
		applyEeffectToPotions(brewing);
		
		
	
		// ------------------------------------
		// from awkward potion

		tempPot = makePotionFromHolder(Items.LINGERING_POTION, Potions.AWKWARD);

		for (ItemStack ingredient : ingredients) {
			PotionItem tempPotionItem = tryBrewPotion(tempPot, ingredient, brewing);

			if (tempPotionItem != null) {
				allPotions.add(tempPotionItem);

			}

		}

		
		
		
		
		
		
		applyEeffectToPotions(brewing);
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		



		// ------------------------------------
		// make splash potions

		ItemStack ingredient = new ItemStack(Items.GUNPOWDER);
		tryBrewPotionList(allPotions, tempPotList, ingredient, brewing);

		
		
		
		allPotions.addAll(tempPotList);
		tempPotList = new ArrayList<PotionItem>();
		
		applyEeffectToPotions(brewing);
		
		

		// ------------------------------------
		// make lingering potions

		ingredient = new ItemStack(Items.DRAGON_BREATH);
		tryBrewPotionList(allPotions, tempPotList, ingredient, brewing);
		
		
		allPotions.addAll(tempPotList);
		tempPotList = new ArrayList<PotionItem>();

		// ------------------------------------

		applyEeffectToPotions(brewing);
	}

	private static ItemStack createPotionStack(Item potionType, Holder<Potion> potion) {
		ItemStack stack = new ItemStack(potionType);
		// 1.21.1 uses DataComponents for Potion Contents
		stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
		return stack;
	}

	public static void checkPotionContent(ItemStack is) {

		
		PotionContents contents = is.get(DataComponents.POTION_CONTENTS);

		if (contents != null) {
			// Get the base Potion (e.g., Awkward, Healing)
			Optional<Holder<Potion>> potion = contents.potion();

			

			// Get custom mob effects (if any)
			Iterable<MobEffectInstance> effects = contents.getAllEffects();

			// Check for a specific effect
			for (MobEffectInstance effect : effects) {
			}
		}
	}

	public static String getPotionItemName(ItemStack itemStack) {

		String potionName = itemStack.getDisplayName().getString();
		return potionName;

	}

	public static String getPotionName(ItemStack stack) {
		if (stack.has(DataComponents.POTION_CONTENTS)) {
			PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);

			if (contents != null && contents.potion().isPresent()) {
				Holder<Potion> potionHolder = contents.potion().get();

				return potionHolder.unwrapKey().map(key -> key.location().toString()).orElse("unknown");
			}
		}
		return "no_potion";
	}

	private static String generateWaterBottleIdString(Item vialType) {
		ItemStack tempPot = makePotionFromHolder(vialType, Potions.WATER);

		return GlobalFuncs.generateItemDataString(tempPot);
	}

	public static ParticleCollection getDefaultPotionParticles(ItemStack potion) {
		if (!GlobalFuncs.isPotion(potion))
			return null;

		ParticleCollection result = new ParticleCollection();

		result.addPotionParticles(Qdc.ParticleConstants.POTION_PARTICLES);

		if (potion.getItem() == Items.POTION) {
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.NETHER_WART)));
		} else if (potion.getItem() == Items.SPLASH_POTION) {
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.NETHER_WART)));
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.GUNPOWDER)));
		} else {
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.NETHER_WART)));
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.GUNPOWDER)));
			result.addOtherParticleCollection(
					QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(new ItemStack(Items.DRAGON_BREATH)));
		}

		return result;
	}
}
