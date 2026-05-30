package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.ConfigFileBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes.ConfigItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.DefaultParticlesBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModIngredientSlot;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData.StackType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.Tags;

public class ParticleAssignmentFunctions {

	public static void assignParticlesToAllItems() {
		assignParticlesToIngredients();

		for (int i = 0; i < 10; i++)
			assignParticlesToCraftableItems();
		
		
		

	}

	private static void assignParticlesToIngredients() {
		for (DefaultItemParticles def : DefaultParticlesBox.defaultItems) {

			if (def.itemList != null)
				for (Item i : def.itemList) {

					ModItemData dataItem = RecipeBox.getDataItem(new ItemStack(i));

					if (dataItem != null) {

						if (def.configParticles == null) {
							dataItem.setParticles(DefaultItemParticlesFunctions
									.getDefaultParticlesForItem(dataItem.itemStack.copy()));
						} else if (dataItem.itemParticles == null)
							dataItem.setParticles(def.configParticles);

					}

				}
		}
	}

	public static void assignParticlesToCraftableItems() {
		int found = 0;
		int notFound = 0;

		for (ModItemData item : RecipeBox.itemList_ordered) {
			if (item.itemParticles == null) {

				ParticleCollection recParticles = getParticlesFromRecipe(item);

				if (recParticles != null) {
					item.itemParticles = recParticles.clone();
					found++;
				} else {
					notFound++;
				}

			}
		}

		GlobalFuncs.line();
		GlobalFuncs.msg("found: " + found);
		GlobalFuncs.msg("Not found: " + notFound);
	}

	public static RecipeType<?>[] recipeTypeList = new RecipeType<?>[] {

			RecipeType.CRAFTING, RecipeType.SMELTING, RecipeType.SMOKING, RecipeType.CAMPFIRE_COOKING,
			RecipeType.SMITHING, RecipeType.BLASTING, RecipeType.STONECUTTING };

	private static ParticleCollection getParticlesFromRecipe(ModItemData itemData) {
		if (itemData.itemRecipeCollection != null) {

			// =============================
			// SMELTING
			// =============================

			if (GlobalFuncs.isPotion(itemData.itemStack)) {

				if (itemData.itemStack.getItem() == Items.POTION) {
					ParticleCollection newParticles = getRecipeParticles(
							itemData.itemRecipeCollection.getDefaultRecipe(), itemData);

					if (newParticles != null) {
						return newParticles.clone();
					}
				} else if (itemData.itemStack.getItem() == Items.SPLASH_POTION) {
					ModRecipe tempRecipe = itemData.itemRecipeCollection.getRecipeByPotionType(Items.POTION);

					if (tempRecipe != null) {

						ParticleCollection newParticles = getRecipeParticles(tempRecipe, itemData);

						if (newParticles != null) {
							return newParticles.clone();
						}

					} else {
						ParticleCollection newParticles = getRecipeParticles(
								itemData.itemRecipeCollection.getDefaultRecipe(), itemData);

						if (newParticles != null) {
							return newParticles.clone();
						}
					}
				} else if (itemData.itemStack.getItem() == Items.LINGERING_POTION) {
					ModRecipe tempRecipe = itemData.itemRecipeCollection.getRecipeByPotionType(Items.SPLASH_POTION);

					if (tempRecipe != null) {

						ParticleCollection newParticles = getRecipeParticles(tempRecipe, itemData);

						if (newParticles != null) {
							return newParticles.clone();
						}

					} else {
						ParticleCollection newParticles = getRecipeParticles(
								itemData.itemRecipeCollection.getDefaultRecipe(), itemData);

						if (newParticles != null) {
							return newParticles.clone();
						}
					}
				}

			} else if (GlobalFuncs.isTippedArrow(itemData.itemStack)) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					return newParticles.clone();
				}

			} else if (GlobalFuncs.isRocket(itemData.itemStack)) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					return newParticles.clone();
				}
			} else if (GlobalFuncs.isSusStew(itemData.itemStack)) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					{
						return newParticles.clone().addPotionParticles(0.05d);
					}
				}
			} else if (itemData.stackType == StackType.SHULKER_BOX) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					return newParticles.clone();
				}
			} else if (itemData.stackType == StackType.ANVIL) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					return newParticles.clone();
				}
			} else if (itemData.stackType == StackType.CONCRETE) {
				ParticleCollection newParticles = getRecipeParticles(itemData.itemRecipeCollection.getDefaultRecipe(),
						itemData);

				if (newParticles != null) {
					return newParticles.clone();
				}
			}  else

				for (RecipeType<?> recType : recipeTypeList) {

					List<ModRecipe> recipeList = itemData.itemRecipeCollection.getRecipeByType(recType);

					if (recipeList != null)
						for (ModRecipe rec : recipeList) {

							if (rec != null) {
								ParticleCollection newParticles = getRecipeParticles(rec, itemData);

								if (newParticles != null) {

									if (itemData.itemStack.getItem() != Items.STICK) {
										return newParticles.clone();
									} else {
										if (rec.slotList.get(0).ingredientList.get(0).getItem() != Items.BAMBOO) {
											return newParticles.clone();
										}
									}

								}
							}
						}

				}

		}

		return null;
	}

	private static boolean shouldAddPotionParticles(ModItemData ingredient, ModItemData resultPotion) {
		if (ingredient.id.equals(PotionInitFunctions.WATER_BOTTLE_ID_STRING)) {
			if (resultPotion.id.equals(PotionInitFunctions.SPLASH_WATER_BOTTLE_ID_STRING)) {
				return false;
			}

			return true;
		}

		if (ingredient.id.equals(PotionInitFunctions.SPLASH_WATER_BOTTLE_ID_STRING)) {
			if (resultPotion.id.equals(PotionInitFunctions.LINGERING_WATER_BOTTLE_ID_STRING)) {
				return false;
			}

			return true;
		}

		return false;
	}

	private static List<TagKey<Item>> buckets = generateBucketTags();

	private static List<TagKey<Item>> generateBucketTags() {
		List<TagKey<Item>> res = new ArrayList<TagKey<Item>>();

		res.add(Tags.Items.BUCKETS_ENTITY_WATER);
		res.add(Tags.Items.BUCKETS_LAVA);
		res.add(Tags.Items.BUCKETS_MILK);
		res.add(Tags.Items.BUCKETS_POWDER_SNOW);
		res.add(Tags.Items.BUCKETS_WATER);

		return res;
	}

	private static boolean shouldRemoveBucketParticles(ItemStack result, ItemStack recipeItem) {
		if (result.is(Tags.Items.BUCKETS_EMPTY))
			return false;

		for (TagKey<Item> tag : buckets) {
			if (recipeItem.is(tag))
				return true;
		}

		return false;

	}

	private static ParticleCollection getRecipeParticles(ModRecipe rec, ModItemData resultItem) {

		if (rec == null)
			return null;

		ParticleCollection recParticles = new ParticleCollection();

		for (ModIngredientSlot slot : rec.slotList) {

			ParticleCollection slotParticles = new ParticleCollection();

			for (ItemStack stack : slot.ingredientList) {
				ModItemData tempDataITem = RecipeBox.getDataItem(stack);
				if (tempDataITem != null) {
					if (tempDataITem.itemParticles != null) {

						if (tempDataITem.stackType == StackType.POTION) {
							if (shouldAddPotionParticles(tempDataITem, resultItem)) {
								recParticles.addPotionParticles(Qdc.ParticleConstants.POTION_PARTICLES);
							}
						}

						ParticleCollection tempCollection = tempDataITem.itemParticles.clone();

						if (shouldRemoveBucketParticles(resultItem.itemStack, stack)) {

							ParticleCollection toRemove = RecipeBox
									.getDataItem(new ItemStack(Items.BUCKET)).itemParticles;

							if (toRemove != null) {

								toRemove = RecipeBox.getDataItem(new ItemStack(Items.BUCKET)).itemParticles.clone();

								toRemove.multiply((double) slot.ingredientCount);

								tempCollection.removeOtherParticleCollection(toRemove);
							} else {
								return null;
							}
						}
						tempCollection.multiply((double) slot.ingredientCount);
						slotParticles.addOtherParticleCollection(tempCollection);
						break;
					}
				}

			}

			if (slotParticles.isEmpty())
				return null;

			recParticles.addOtherParticleCollection(slotParticles);
		}

		if (recParticles.isEmpty())
			return null;

		if (rec.makeAmount > 1)
			recParticles.divide(rec.makeAmount);

		if(shouldAddCraftingExtraParticles(resultItem.itemStack))
		if (Qdc.ParticleConstants.EXTRATRA_CRAFTING_PARTICLE_PERCENTAGE > 0) {
			ParticleCollection extraCraftingParticles = new ParticleCollection();

			extraCraftingParticles.addOtherParticleCollection(recParticles);

			extraCraftingParticles.multiply(Qdc.ParticleConstants.EXTRATRA_CRAFTING_PARTICLE_PERCENTAGE);

			recParticles.addOtherParticleCollection(extraCraftingParticles);
		}

		return recParticles;
	}

	private static String[] modIdIgnoreExtraParticlesList = new String[] {Qdc.MOD_ID, "qdc_quantum_farming_2"};
	
	private static boolean shouldAddCraftingExtraParticles(ItemStack craftedItem)
	{
		String itemModID = GlobalFuncs.getItemModID(craftedItem.getItem());
		
		for(String s : modIdIgnoreExtraParticlesList)
		{
			if(s.equals(itemModID))
				return false;
		}
			
		return true;
	}
	
}
