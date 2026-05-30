package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.MainRecipeType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemRecipeCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.common.Tags;

public class ItemRecipeFuctions {

	public static List<Item> recipeItems = new ArrayList<Item>();
	public static List<Item> ingredients = new ArrayList<Item>();

	private static Item[] ignoreItemList = new Item[] { QdcApi.QDC_CORE.ITEMS.QDC_MAIN_ITEM, Items.BARRIER,
			Items.STRUCTURE_VOID, Items.LIGHT, Items.COMMAND_BLOCK, Items.COMMAND_BLOCK_MINECART,
			Items.REPEATING_COMMAND_BLOCK, Items.CHAIN_COMMAND_BLOCK, Items.STRUCTURE_BLOCK, Items.JIGSAW,
			Items.PETRIFIED_OAK_SLAB, Items.BEDROCK, Items.CHORUS_PLANT, Items.REINFORCED_DEEPSLATE,
			Items.BUDDING_AMETHYST, Items.DIRT_PATH, Items.END_PORTAL_FRAME, Items.FARMLAND, Items.FROGSPAWN,
			Items.INFESTED_CHISELED_STONE_BRICKS, Items.INFESTED_COBBLESTONE, Items.INFESTED_CRACKED_STONE_BRICKS,
			Items.INFESTED_DEEPSLATE, Items.INFESTED_MOSSY_STONE_BRICKS, Items.INFESTED_STONE,
			Items.INFESTED_STONE_BRICKS, Items.SPAWNER, Items.TRIAL_SPAWNER, Items.VAULT, Items.DEBUG_STICK,
			Items.KNOWLEDGE_BOOK, Items.WRITTEN_BOOK, Items.ENCHANTED_BOOK, Items.FILLED_MAP, Items.SUSPICIOUS_STEW ,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.FOOD,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.METAL,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.GEM,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.ENCHANTED,
			QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.POTION};

	public static Item[] dyeArray = new Item[] { Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE,
			Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE,
			Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.WHITE_DYE,
			Items.YELLOW_DYE, };

	public static Item[] boxArray = new Item[] { Items.BLACK_SHULKER_BOX, Items.BLUE_SHULKER_BOX,
			Items.BROWN_SHULKER_BOX, Items.CYAN_SHULKER_BOX, Items.GRAY_SHULKER_BOX, Items.GREEN_SHULKER_BOX,
			Items.LIGHT_BLUE_SHULKER_BOX, Items.LIGHT_GRAY_SHULKER_BOX, Items.LIME_SHULKER_BOX,
			Items.MAGENTA_SHULKER_BOX, Items.ORANGE_SHULKER_BOX, Items.PINK_SHULKER_BOX, Items.PURPLE_SHULKER_BOX,
			Items.RED_SHULKER_BOX, Items.WHITE_SHULKER_BOX, Items.YELLOW_SHULKER_BOX, };

	public static Item[] anvilArray = new Item[] { Items.CHIPPED_ANVIL, Items.DAMAGED_ANVIL };

	public static Item[] concretePowderArray = new Item[] { Items.BLACK_CONCRETE_POWDER, Items.BLUE_CONCRETE_POWDER,
			Items.BROWN_CONCRETE_POWDER, Items.CYAN_CONCRETE_POWDER, Items.GRAY_CONCRETE_POWDER,
			Items.GREEN_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE_POWDER,
			Items.LIME_CONCRETE_POWDER, Items.MAGENTA_CONCRETE_POWDER, Items.ORANGE_CONCRETE_POWDER,
			Items.PINK_CONCRETE_POWDER, Items.PURPLE_CONCRETE_POWDER, Items.RED_CONCRETE_POWDER,
			Items.WHITE_CONCRETE_POWDER, Items.YELLOW_CONCRETE_POWDER, };

	public static Item[] concreteArray = new Item[] { Items.BLACK_CONCRETE, Items.BLUE_CONCRETE, Items.BROWN_CONCRETE,
			Items.CYAN_CONCRETE, Items.GRAY_CONCRETE, Items.GREEN_CONCRETE, Items.LIGHT_BLUE_CONCRETE,
			Items.LIGHT_GRAY_CONCRETE, Items.LIME_CONCRETE, Items.MAGENTA_CONCRETE, Items.ORANGE_CONCRETE,
			Items.PINK_CONCRETE, Items.PURPLE_CONCRETE, Items.RED_CONCRETE, Items.WHITE_CONCRETE,
			Items.YELLOW_CONCRETE, };

	public static void clear() {
		recipeItems = new ArrayList<Item>();
		ingredients = new ArrayList<Item>();
	}

	public static void extractFromCraftingRecipes(ServerLevel serverLevel) {

		List<ItemStack> allItemStacks = BuiltInRegistries.ITEM.stream().map(ItemStack::new).toList();

		separateItems(allItemStacks, serverLevel);

		for (Item i : recipeItems) {

			if (!isInIngredientList(i))
				RecipeBox.addModItem(generateRecipeItemData(i, serverLevel));
		}

		for (Item i : ingredients) {
			RecipeBox.addModItem(generateDefaultItemData(i));
		}

		handleFireWorkRecipes();
		handleShulkerBoxes();
		handleChippedAnvil();
		handleDamagedAnvil();
		handleConcrete();
	}

	public static void handleConcrete() {
		for (int i = 0; i < concreteArray.length; i++) {
			ItemStack box = new ItemStack(concreteArray[i]);

			ModItemData result = new ModItemData(box.copy(), StackType.CONCRETE);

			ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

			ModRecipe modRecipeBase = new ModRecipe();

			modRecipeBase.addNewSlot(new ItemStack[] { box.copy() });

			recipeCollection.addRecipe(modRecipeBase);

			ModRecipe modRecipe = new ModRecipe();

			modRecipe.setMainRecipeType(MainRecipeType.CONCRETE);

			modRecipe.addNewSlot(new ItemStack[] { new ItemStack(concretePowderArray[i]) });

			recipeCollection.addRecipe(modRecipe);
			result.setRecipes(recipeCollection);

			RecipeBox.addModItem(result);
		}

	}

	public static void handleChippedAnvil() {

		ItemStack anvilItem = new ItemStack(Items.CHIPPED_ANVIL);

		ModItemData result = new ModItemData(anvilItem.copy(), StackType.ANVIL);

		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe modRecipeBase = new ModRecipe();

		modRecipeBase.addNewSlot(new ItemStack[] { anvilItem.copy() });

		recipeCollection.addRecipe(modRecipeBase);

		ModRecipe modRecipe = new ModRecipe();

		modRecipe.setMainRecipeType(MainRecipeType.ANVIL);

		modRecipe.addNewSlot(new ItemStack[] { new ItemStack(Items.ANVIL) });

		recipeCollection.addRecipe(modRecipe);
		result.setRecipes(recipeCollection);

		RecipeBox.addModItem(result);

	}

	public static void handleDamagedAnvil() {

		ItemStack anvilItem = new ItemStack(Items.DAMAGED_ANVIL);

		ModItemData result = new ModItemData(anvilItem.copy(), StackType.ANVIL);

		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe modRecipeBase = new ModRecipe();

		modRecipeBase.addNewSlot(new ItemStack[] { anvilItem.copy() });

		recipeCollection.addRecipe(modRecipeBase);

		ModRecipe modRecipe = new ModRecipe();

		modRecipe.setMainRecipeType(MainRecipeType.ANVIL);

		modRecipe.addNewSlot(new ItemStack[] { new ItemStack(Items.CHIPPED_ANVIL) });

		recipeCollection.addRecipe(modRecipe);
		result.setRecipes(recipeCollection);

		RecipeBox.addModItem(result);

	}

	public static void handleShulkerBoxes() {

		for (int i = 0; i < dyeArray.length; i++) {
			ItemStack box = new ItemStack(boxArray[i]);

			ModItemData result = new ModItemData(box.copy(), StackType.SHULKER_BOX);

			ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

			ModRecipe modRecipeBase = new ModRecipe();

			modRecipeBase.addNewSlot(new ItemStack[] { box.copy() });

			recipeCollection.addRecipe(modRecipeBase);

			ModRecipe modRecipe = new ModRecipe();

			modRecipe.setMainRecipeType(MainRecipeType.SHULKER_BOX);

			modRecipe.addNewSlot(new ItemStack[] { new ItemStack(Items.SHULKER_BOX) });
			modRecipe.addNewSlot(new ItemStack[] { new ItemStack(dyeArray[i]) });

			recipeCollection.addRecipe(modRecipe);
			result.setRecipes(recipeCollection);

			RecipeBox.addModItem(result);
		}

	}

	public static void handleFireWorkRecipes() {
		for (int i = 1; i < 3; i++) {
			ItemStack rocket = createRocket(i + 1);

			ModItemData result = new ModItemData(rocket.copy(), StackType.ROCKET);
			ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

			ModRecipe modRecipeBase = new ModRecipe();

			modRecipeBase.addNewSlot(new ItemStack[] { rocket.copy() });

			recipeCollection.addRecipe(modRecipeBase);

			ModRecipe modRecipe = new ModRecipe();

			modRecipe.setMainRecipeType(MainRecipeType.ROCKET);

			modRecipe.addNewSlot(new ItemStack(Items.GUNPOWDER), i + 1);
			modRecipe.addNewSlot(new ItemStack[] { new ItemStack(Items.PAPER) });

			modRecipe.setMakeAmount(3);

			recipeCollection.addRecipe(modRecipe);
			result.setRecipes(recipeCollection);

			RecipeBox.addModItem(result);

		}
	}

	private static ItemStack createRocket(int duration) {
		ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);

		// Set the flight duration component (duration is in seconds)
		rocket.set(DataComponents.FIREWORKS, new Fireworks(duration, List.of()));

		return rocket;
	}

	private static ModItemData generateDefaultItemData(Item item) {
		ModItemData result = new ModItemData(item);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();
		ModRecipe modRecipe = new ModRecipe();

		modRecipe.addNewSlot(new ItemStack[] { new ItemStack(item) });

		recipeCollection.addRecipe(modRecipe);
		result.setRecipes(recipeCollection);

		return result;
	}

	private static ModItemData generateRecipeItemData(Item item, ServerLevel serverLevel) {
		ModItemData result = new ModItemData(item);
		ModItemRecipeCollection recipeCollection = new ModItemRecipeCollection();

		ModRecipe modRecipe = new ModRecipe();

		modRecipe.addNewSlot(new ItemStack[] { new ItemStack(item) });

		recipeCollection.addRecipe(modRecipe);

		List<RecipeHolder<?>> recipes = getRecipesForItem(serverLevel, item);

		for (RecipeHolder<?> rec : recipes) {

			modRecipe = new ModRecipe(rec);

			ItemStack baseResult = rec.value().getResultItem(serverLevel.registryAccess());

			modRecipe.setMakeAmount(baseResult.getCount());

			if (rec.value() instanceof SmithingTransformRecipe recipe) {
				Ingredient template = ObfuscationReflectionHelper.getPrivateValue(SmithingTransformRecipe.class, recipe,
						"template");
				Ingredient base = ObfuscationReflectionHelper.getPrivateValue(SmithingTransformRecipe.class, recipe,
						"base");
				Ingredient addition = ObfuscationReflectionHelper.getPrivateValue(SmithingTransformRecipe.class, recipe,
						"addition");

				modRecipe.addNewSlot(template.getItems());
				modRecipe.addNewSlot(base.getItems());
				modRecipe.addNewSlot(addition.getItems());

				if (!modRecipe.isEmpty())
					recipeCollection.addRecipe(modRecipe);

			} else {

				if (rec.value().getIngredients().size() == 0) {

				} else {

					if (rec.value().getType() == RecipeType.SMELTING) {

						ModRecipe smeltingRec = recipeCollection.getSmeltingRecipe();
						
						if(smeltingRec == null)
						{
							for (Ingredient ing : rec.value().getIngredients()) {

								if (ing.getItems().length > 0)
									modRecipe.addNewSlot(ing.getItems());
							}
							
							if (!modRecipe.isEmpty())
								recipeCollection.addRecipe(modRecipe);
						}
						else
						{
							for (Ingredient ing : rec.value().getIngredients()) {

								if (ing.getItems().length > 0)
									for(ItemStack tempStack : ing.getItems())
									smeltingRec.appendItemToRecipeSlot(tempStack);
							}
						}
						
					} else {
						for (Ingredient ing : rec.value().getIngredients()) {

							if (ing.getItems().length > 0)
								modRecipe.addNewSlot(ing.getItems());
						}
						
						if (!modRecipe.isEmpty())
						recipeCollection.addRecipe(modRecipe);
						
					}

					
				}
			}

		}

		if (recipeCollection.isEmpty()) {
			addToIngredientList(item);

			return generateDefaultItemData(item);
		}

		result.setRecipes(recipeCollection);

		return result;
	}

	private static void separateItems(List<ItemStack> allItemStacks, ServerLevel serverLevel) {

		for (ItemStack is : allItemStacks) {

			if (is.getItem() != Items.AIR && !GlobalFuncs.isQuantumSeed(is))
			{
				if (isOre(is)) {
					if (!isInIngredientList(is.getItem()))
						addToIngredientList(is.getItem());
				} else if (isInShulkerIgnoreList(is.getItem())) {

				} else if (isInAnvilIgnoreList(is.getItem())) {

				} else if (isInConcreteIgnoreList(is.getItem())) {

				} else {

					List<RecipeHolder<?>> recipes = getRecipesForItem(serverLevel, is.getItem());

					if (recipes.size() == 0) {
						if (!isInIngredientList(is.getItem())) {
							addToIngredientList(is.getItem());
						}
					} else {

						Item reverseItem = isRecipeAReverseItem(is.getItem(), serverLevel);

						if (reverseItem != null) {

							if (!isInIngredientList(reverseItem))
								addToIngredientList(reverseItem);
						}

						if (!isInIngredientList(is.getItem()))
							if (!isInRecipeItemsList(is.getItem())) {
								recipeItems.add(is.getItem());
							}
					}

				}
			}
		}
	}

	private static void addToIngredientList(Item item) {
		if (isInIgnoreList(item))
			return;

		ingredients.add(item);

	}

	private static boolean isInShulkerIgnoreList(Item item) {
		for (Item i : boxArray) {
			if (i == item)
				return true;
		}
		return false;
	}

	private static boolean isInAnvilIgnoreList(Item item) {
		for (Item i : anvilArray) {
			if (i == item)
				return true;
		}
		return false;
	}

	private static boolean isInConcreteIgnoreList(Item item) {
		for (Item i : concreteArray) {
			if (i == item)
				return true;
		}
		return false;
	}

	private static boolean isInIgnoreList(Item item) {
		for (Item i : ignoreItemList) {
			if (i == item)
				return true;
		}

		if (GlobalFuncs.getItemStackName(new ItemStack(item)).toLowerCase().contains("uncraftable"))
			return true;

		return false;
	}

	private static Item isRecipeAReverseItem(Item curItem, ServerLevel serverLevel) {

		List<RecipeHolder<?>> recipes = getRecipesForItem(serverLevel, curItem);

		if (recipes.size() == 0)
			return null;

		if (recipes.size() > 1)
			return null;

		RecipeHolder<?> rec = recipes.get(0);

		Item ingItem = null;

		if (isRecipeSameItem(rec) > -1) {
			ingItem = getFisrtValidItemInRecipe(rec);
			if (ingItem == curItem) {

				return curItem;
			}

			List<RecipeHolder<?>> ingRecipes = getRecipesForItem(serverLevel, ingItem);

			if (ingRecipes.size() == 0)
				return null;

			if (ingRecipes.size() > 1)
				return null;

			if (isRecipeSameItem(ingRecipes.get(0)) > 1) {
				if (getFisrtValidItemInRecipe(ingRecipes.get(0)) == curItem) {

					return curItem;

				}
			}

		} else {

			for (Ingredient ing : rec.value().getIngredients()) {

				if (ing.getItems().length == 1) {

					ingItem = ing.getItems()[0].getItem();

					List<RecipeHolder<?>> ingRecipes = getRecipesForItem(serverLevel, ingItem);

					if (ingRecipes.size() == 1) {
						for (Ingredient ing2 : ingRecipes.get(0).value().getIngredients()) {
							if (ing.getItems().length == 0)
								return null;

							for (ItemStack is : ing2.getItems()) {

								if (is.getItem() == curItem) {

									return curItem;

								}
							}
						}
					}
				}

			}

		}
		return null;
	}

	private static Item getFisrtValidItemInRecipe(RecipeHolder<?> rec) {

		for (Ingredient ing : rec.value().getIngredients()) {

			if (ing.getItems().length > 0) {

				return ing.getItems()[0].getItem();
			}
		}

		return null;
	}

	private static int isRecipeSameItem(RecipeHolder<?> rec) {

		Item ingItem = null;
		for (Ingredient ing : rec.value().getIngredients()) {

			for (ItemStack is : ing.getItems()) {

				if (ingItem == null) {
					ingItem = is.getItem();
				}

				if (is.getItem() != ingItem)
					return -1;
			}
		}

		if (ingItem == null)
			return -1;

		return rec.value().getIngredients().size();
	}

	private static boolean isInIngredientList(Item item) {
		for (Item i : ingredients) {
			if (i == item)
				return true;
		}

		return false;
	}

	private static boolean isInRecipeItemsList(Item item) {
		for (Item i : recipeItems) {
			if (i == item)
				return true;
		}

		return false;
	}

	private static boolean isOre(ItemStack itemStack) {
		if (itemStack.is(Tags.Items.ORES)) {
			return true;
		}

		return false;
	}

	private static List<RecipeHolder<?>> getRecipesForItem(ServerLevel level, Item targetItem) {
		RecipeManager recipeManager = level.getRecipeManager();

		// Filter recipes based on the result item
		return recipeManager.getRecipes().stream()
				.filter(holder -> holder.value().getResultItem(level.registryAccess()).getItem() == targetItem)
				.collect(Collectors.toList());
	}

}
