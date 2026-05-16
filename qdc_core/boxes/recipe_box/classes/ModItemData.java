package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class ModItemData {
	public static enum StackType {
		ENCHANTMENT, POTION, ARROW, ROCKET, SUS_STEW, SHULKER_BOX, ANVIL, CONCRETE
	}

	public ItemStack itemStack = null;
	public String name = null;
	public String displayName = null;
	public String modID = null;
	public String modName = null;
	public String id = null;
	public String searchString = "";
	public ParticleCollection itemParticles = null;
	public ModItemRecipeCollection itemRecipeCollection = null;
	public StackType stackType = null;

	public boolean canCreate = false;

	public int canCreateAmount = 0;

	private final int maxDisplayNameLen = 30;

//	public int potionDuration = 0;
//	public int potionLevel = 0;

	public ModItemData(Item item) {
		this.itemStack = new ItemStack(item);
		this.name = GlobalFuncs.getItemName(item);
		this.modID = GlobalFuncs.getItemModID(item);
		this.modName = GlobalFuncs.getModName(this.modID);
		this.id = GlobalFuncs.generateItemDataString(itemStack);

		setSearchString();
		setDisplayName();
	}

	public ModItemData(ItemStack stack, StackType stackType) {
		this.itemStack = stack.copy();

		if (stackType == StackType.ENCHANTMENT) {
			this.name = "[Enchantment] - " + GlobalFuncs.getEnchantmentName(stack);
		} else
			this.name = GlobalFuncs.getItemStackName(stack);

		if (stackType == StackType.POTION || stackType == StackType.ARROW || stackType == StackType.ENCHANTMENT) {
			this.modID = GlobalFuncs.getDataItemModID(stack);
		} else {
			this.modID = GlobalFuncs.getItemModID(itemStack.getItem());
		}
		this.modName = GlobalFuncs.getModName(this.modID);

		this.stackType = stackType;

		this.id = GlobalFuncs.generateItemDataString(itemStack);

		setSearchString();
		setDisplayName();
	}

	public void appendEffectToName(String effect) {
		this.name += " - " + GlobalFuncs.toCamelCase(effect);

		setSearchString();
		setDisplayName();
	}

	public void setDisplayName() {
		if (name.length() > maxDisplayNameLen) {
			displayName = name.substring(0, maxDisplayNameLen);
			displayName += "...";
		} else {
			displayName = name;
		}
	}

	public void calcCanCreateAmount() {
		checkCanCreate();

		if (canCreate) {
			if (itemParticles != null) {
				canCreateAmount = QdcApi.QDC_CORE.FUNCTIONS.countCanMakeAmount(itemParticles);
			}
		}
	}

	public void checkCanCreate() {
		if (itemRecipeCollection != null)
			canCreate = itemRecipeCollection.checkForDiscovery();
	}

	public boolean isDirectlyDiscovered() {

		return itemRecipeCollection.isDirectlyDiscovered();
	}

	private void setSearchString() {
		this.searchString = name + " " + id;
	}

	public void setGuiPositions(Point windowPos) {
		if (itemRecipeCollection != null) {
			itemRecipeCollection.setItemPositions(windowPos);
		}
	}

	public void setRecipes(ModItemRecipeCollection itemRecipeCollection) {
		this.itemRecipeCollection = itemRecipeCollection;
	}

	public void setParticles(ParticleCollection itemParticles) {
		this.itemParticles = itemParticles.clone();
		this.itemParticles.setParticleList();
	}

	public boolean isValidForSearchString(String search) {

		return searchString.toLowerCase().contains(search.toLowerCase());
	}

}
