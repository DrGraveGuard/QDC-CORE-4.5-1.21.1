package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.MainDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModIngredientSlot {

	public List<ItemStack> ingredientList = new ArrayList<ItemStack>();

	public int ingredientCount = 1;
	public int displayIndex = 0;
	public ItemStack curDisplayItem = null;

	public String curDisplayItemID = null;
	public Point pos = null;
	public boolean isHoveringOver;
	public boolean isDiscovered = false;
	
	public TextureColor slotColor = null;

	public int tickCount = 0;

	public ModIngredientSlot(ItemStack[] ingredients) {
		addIngredients(ingredients);
	}
	
	public ModIngredientSlot(ItemStack ingredient, int count) {
		addIngredientsMultiple(ingredient, count);
	}

	public boolean checkForDiscovery()
	{
		for(ItemStack is : ingredientList)
		{
			if(MainDiscoveryBox.isItemDiscovered(is))
			{
				isDiscovered = true;
				slotColor = AssemblerSettings.CONSTANTS.ITEM_FILL_ACTIVE;
				return true;
			}
				
		}
		slotColor = AssemblerSettings.CONSTANTS.ITEM_FILL;
		return false;
		
	}
	
	public void tick() {
		if (ingredientList.size() > 1) {
			if (tickCount >= Qdc.AssemblerVariables.ITEM_CHANGE_MAX_TICK_COUNT) {
				tickCount = 0;
				updateDisplayItem();
			}
			
			tickCount++;
		}
	}

	public boolean hasPotionType(Item potionType)
	{
		for(ItemStack is : ingredientList)
		{
			if(is.getItem() == potionType)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void incrementIngredientCount() {
		ingredientCount++;
	}

	public void setPos(Point newPos) {
		this.pos = new Point(newPos);
	}

	public void addIngredients(ItemStack[] ingredients) {
		for (ItemStack is : ingredients) {
			if (curDisplayItem == null) {
				curDisplayItem = is.copy();
				curDisplayItemID = GlobalFuncs.generateItemDataString(curDisplayItem);
			}
			addIngredient(is);
		}
	}
	
	public void addIngredientsMultiple(ItemStack ingredient, int count) {
		
			if (curDisplayItem == null) {
				curDisplayItem = ingredient.copy();
				curDisplayItemID = GlobalFuncs.generateItemDataString(curDisplayItem);
			}
			addIngredient(ingredient);
			this.ingredientCount = count;
		
	}

	public void addIngredient(ItemStack ingredient) {
		if (!ingredientExists(ingredient)) {
			ingredientList.add(ingredient.copy());
		}
	}

	private boolean ingredientExists(ItemStack ingredient) {
		for (ItemStack is : ingredientList) {
			if (is.getItem() == ingredient.getItem())
				return true;
		}

		return false;
	}

	public boolean isSame(ModIngredientSlot slot) {
		if (slot.ingredientList.size() != this.ingredientList.size())
			return false;

		for (ItemStack is : this.ingredientList) {
			boolean found = false;

			for (ItemStack is2 : slot.ingredientList) {
				if (is.getItem() == is2.getItem()) {
					found = true;
					break;
				}
			}

			if (!found)
				return false;
		}

		return true;
	}

	public void updateDisplayItem() {
		if (ingredientList.size() == 0) {
			curDisplayItem = null;
			curDisplayItemID = null;
			return;
		}

		if (ingredientList.size() > 1) {
			displayIndex++;

			if (displayIndex >= ingredientList.size()) {
				displayIndex = 0;
			}
		} else {
			displayIndex = 0;
		}

		curDisplayItem = ingredientList.get(displayIndex).copy();
		curDisplayItemID = GlobalFuncs.generateItemDataString(curDisplayItem);
	}

	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.RECIPE_WINDOW.ITEMS.SIZE,
				new Point(mouseX, mouseY));
		return isHoveringOver;
	}
}
