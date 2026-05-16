package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.MainRecipeType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.RECIPE_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.RecipeStatus;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class ModItemRecipeCollection {

	public List<ModRecipe> recipeList = null;
	public ItemStack curHoverItem = null;
	public boolean canCreate = false;

	public TextureColor statusColor = null;
	public RecipeStatus recipeCollectionStatus = null;
	private int curIndex = 0;

	public ModItemRecipeCollection() {
		this.recipeList = new ArrayList<ModRecipe>();
	}

	
	
	public ModRecipe getSmeltingRecipe()
	{
		for(ModRecipe rec : recipeList)
		{
			if(rec.recipeType != null)
			{
				if(rec.recipeType == RecipeType.SMELTING)
				{
					return rec;
				}
			}
		}
		
		return null;
	}
	
	
	
	public boolean checkForDiscovery() {

		if(hasCompleteRecipe())
		{

			recipeCollectionStatus = RecipeStatus.COMPLETE_DISCOVERY;
			statusColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.COMPLETE_DISCOVERY;
			
			return true;
		}
		else if(hasPartialRecipe())
		{
			recipeCollectionStatus = RecipeStatus.PARTIAL_DISCOVERY;
			statusColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.PARTIAL_DISCOVERY;
		}
		else
		{
			recipeCollectionStatus = RecipeStatus.NO_DISCOVERY;
			statusColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.NO_DISCOVERY;
		}
	
		
		
		return false;
	}

	public boolean isDirectlyDiscovered() {
		if(recipeList.get(0).checkForDiscovery() == RecipeStatus.COMPLETE_DISCOVERY)
			return true;
		
		return false;
	}
	
	private boolean hasCompleteRecipe() {

		boolean hasComplete = false;
		
		for (ModRecipe rec : recipeList) {

			if (rec.checkForDiscovery() == RecipeStatus.COMPLETE_DISCOVERY) {
				hasComplete = true;
			}
		}
		return hasComplete;
	}
	
	private boolean hasPartialRecipe() {

		boolean hasPartial = false;
		
		for (ModRecipe rec : recipeList) {

			if (rec.checkForDiscovery() == RecipeStatus.PARTIAL_DISCOVERY) {
				hasPartial = true;
			}
		}
		return hasPartial;
	}

	public void addRecipe(ModRecipe recipe, MainRecipeType recType) {
		
		if(curIndex == 0)
			recipe.setMainRecipeType(MainRecipeType.DISCOVERY);
		else
			recipe.setMainRecipeType(recType);
		
		recipe.setIndex(curIndex);

		recipeList.add(recipe);

		curIndex++;
	}
	
	public void addRecipe(ModRecipe recipe) {
		
		if(recipe.mainRecipeType == MainRecipeType.GENERAL)
		{
			if(recipe.recipeType != null)
			{
				if(recipe.recipeType == RecipeType.BLASTING ||
						recipe.recipeType == RecipeType.CAMPFIRE_COOKING ||
						recipe.recipeType == RecipeType.SMOKING)
					return;
			}
		}
		
		
		
		if(curIndex == 0)
			recipe.setMainRecipeType(MainRecipeType.DISCOVERY);
		
		recipe.setIndex(curIndex);

		recipeList.add(recipe);

		curIndex++;
	}

	public void tick() {
		for (ModRecipe rec : recipeList) {
			rec.tick();
		}
	}

	public boolean isEmpty() {
		if (recipeList.size() > 1)
			return false;

		return true;
	}

	public ModRecipe getDefaultRecipe()
	{
		if(recipeList != null)
		{
			if(recipeList.size()> 1)
			{
				return recipeList.get(1);
			}
		}
		
		return null;
	}
	
	
	
	public ModRecipe getRecipeByPotionType(Item potionType) {

		for (ModRecipe rec : recipeList) {
			if (rec.hasPotionType(potionType))
					return rec;
				
		}



		return null;
	}
	
	
	
	public List<ModRecipe> getRecipeByType(RecipeType<?> recipeType) {
		List<ModRecipe> resultList = new ArrayList<ModRecipe>();

		for (ModRecipe rec : recipeList) {
			if (rec.recipeType != null)
				if (rec.recipeType.equals(recipeType)) {
					resultList.add(rec);
				}
		}

		if (resultList.size() > 0)
			return resultList;

		return null;
	}

	public ModRecipe getFirstValidRecipe() {
		if (recipeList.size() > 1) {
			return recipeList.get(1);
		}

		return null;
	}

	public void setItemPositions(Point windowPos) {
		Point lastPos = new Point(windowPos);

		for (ModRecipe rec : recipeList) {
			
			if(rec.mainRecipeType == MainRecipeType.DISCOVERY)
				rec.setDiscoveryPos(lastPos);
			else			
				rec.setPos(lastPos);

			if (rec.itemFinishPoint != null)
				lastPos = new Point(rec.itemFinishPoint.x, rec.itemFinishPoint.y + RECIPE_WINDOW.ITEMS.SIZE.y + 2);
		}
	}

	public ItemStack checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		for (ModRecipe recipe : recipeList) {
			curHoverItem = recipe.checkIfHoveringOver(windowPos, mouseX, mouseY);
			if (curHoverItem != null) {
				return curHoverItem;
			}
		}

		curHoverItem = null;
		return null;

	}
}
