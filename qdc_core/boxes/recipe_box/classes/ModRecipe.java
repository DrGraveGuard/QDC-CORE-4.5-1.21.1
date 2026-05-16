package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.CONSTANTS;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.MainRecipeType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.RECIPE_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.RECIPE_WINDOW.RECIPE_TITLE;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.RecipeStatus;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipe {

	public List<ModIngredientSlot> slotList = new ArrayList<ModIngredientSlot>();

	public int slotCount = 0;
	public int discoveredCount = 0;
	public boolean canCreate = false;

	public int makeAmount = 1;
	public ParticleCollection recipeParticles = null;
	public RecipeType<?> recipeType = null;

	public Point titlePos = null;
	public Point itemStartPos = null;
	public Point itemFinishPoint = null;

	public ItemStack curHoverItem = null;
	public String curHoverItemID = null;

	public Point windowPos = null;
	public Point windowSize = null;

	public TextureColor recipeColor = null;

	public int index = -1;

	public String titleText = "";

	public RecipeStatus recipeStatus = null;

	public MainRecipeType mainRecipeType = null;

	public ModRecipe() {

	}

	public ModRecipe(RecipeHolder<?> rec) {

		mainRecipeType = MainRecipeType.GENERAL;
		recipeType = rec.value().getType();
	}

	public void appendItemToRecipeSlot(ItemStack toAppend)
	{
		if(slotList.size() == 0) return;
		
		slotList.get(0).addIngredient(toAppend);
	}
	
	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public void setMainRecipeType(MainRecipeType recType) {
		if (mainRecipeType == null) {
			mainRecipeType = recType;
		}
	}

	public boolean hasPotionType(Item potionType) {
		for (ModIngredientSlot slot : slotList) {
			if (slot.hasPotionType(potionType)) {
				return true;
			}
		}

		return false;
	}

	public RecipeStatus checkForDiscovery() {
		this.slotCount = slotList.size();
		discoveredCount = 0;

		for (ModIngredientSlot slot : slotList) {
			if (slot.checkForDiscovery()) {
				discoveredCount++;
			}
		}

		if (this.slotCount == this.discoveredCount) {

			canCreate = true;
			recipeStatus = RecipeStatus.COMPLETE_DISCOVERY;
			recipeColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.COMPLETE_DISCOVERY;

		} else if (this.discoveredCount > 0) {
			recipeStatus = RecipeStatus.PARTIAL_DISCOVERY;
			recipeColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.PARTIAL_DISCOVERY;
		} else if (this.discoveredCount == 0) {
			recipeStatus = RecipeStatus.NO_DISCOVERY;
			recipeColor = AssemblerSettings.CONSTANTS.STATUS_COLORS.NO_DISCOVERY;
		}

		if (mainRecipeType == MainRecipeType.GENERAL) {
			if (recipeType != null)
				generateDefaultTitleText();

		} else if (mainRecipeType == MainRecipeType.DISCOVERY) {
			generateDiscoveryTitleText();

		} else if (mainRecipeType == MainRecipeType.POTION) {
			generatePotionTitleText();
		} else if (mainRecipeType == MainRecipeType.ARROW) {
			generateArrowTitleText();
		} else if (mainRecipeType == MainRecipeType.ROCKET) {
			generateFireworkTitleText();
		} else if (mainRecipeType == MainRecipeType.SUS_STEW) {
			generateSusStewTitleText();
		} else if (mainRecipeType == MainRecipeType.SHULKER_BOX) {
			generateShukerBoxTitleText();
		} else if (mainRecipeType == MainRecipeType.ANVIL) {
			generateAnvilTitleText();
		} else if (mainRecipeType == MainRecipeType.CONCRETE) {
			generateConcreteTitleText();
		}

		return recipeStatus;

	}

	private void generateDefaultTitleText() {
		String recipeTypeString = recipeType.toString();

		titleText = recipeTypeString.substring(0, 1).toUpperCase() + recipeTypeString.substring(1);

		titleText += " " + discoveredCount + "/" + slotCount;
	}

	private void generateDiscoveryTitleText() {

		titleText = "Discovery";
	}

	private void generatePotionTitleText() {

		titleText = "Brewing " + discoveredCount + "/" + slotCount;
	}

	private void generateArrowTitleText() {

		titleText = "Crafting " + discoveredCount + "/" + slotCount;
	}

	private void generateFireworkTitleText() {

		titleText = "Crafting " + discoveredCount + "/" + slotCount;
	}

	private void generateSusStewTitleText() {

		titleText = "Crafting " + discoveredCount + "/" + slotCount;
	}

	private void generateShukerBoxTitleText() {

		titleText = "Crafting " + discoveredCount + "/" + slotCount;
	}

	private void generateAnvilTitleText() {

		titleText = "Degradation ";
	}

	private void generateConcreteTitleText() {

		titleText = "Solidification ";
	}

	public void tick() {
		for (ModIngredientSlot slot : slotList) {
			slot.tick();
		}
	}

	public void setMakeAmount(int makeAmount) {
		this.makeAmount = makeAmount;
	}

	public boolean isEmpty() {
		if (slotList.size() > 0)
			return false;

		return true;
	}

	public void setDiscoveryPos(Point startPos) {

		this.windowPos = new Point(startPos);
		this.titlePos = new Point(windowPos.x + RECIPE_WINDOW.DISCOVERY.TITLE_GAP.x,
				windowPos.y + RECIPE_WINDOW.DISCOVERY.TITLE_GAP.y);
		this.itemStartPos = new Point(startPos.x + 1, startPos.y + 1);

		slotList.get(0).setPos(itemStartPos);

		itemFinishPoint = startPos;

		windowSize = RECIPE_WINDOW.DISCOVERY.SIZE;

	}

	public void setPos(Point startPos) {

		this.windowPos = new Point(startPos);
		this.titlePos = new Point(windowPos.x + RECIPE_TITLE.TITLE_GAP.x, windowPos.y + RECIPE_TITLE.TITLE_GAP.y);
		this.itemStartPos = new Point(this.titlePos.x, titlePos.y + RECIPE_TITLE.SIZE.y);
		List<Point> ITEMS_POS_LIST = GuiFunctions.generateIconPosList(CONSTANTS.RECIPE_ITEM_LIST_MAX,
				CONSTANTS.RECIPE_ITEM_LIST_WIDTH, RECIPE_WINDOW.ITEMS.SIZE, RECIPE_WINDOW.ITEMS.GAP_SIZE, itemStartPos);

		if (slotList.size() == 0) {
			itemFinishPoint = new Point(startPos);
			return;
		}

		int index = 0;
		for (ModIngredientSlot slot : slotList) {
			slot.setPos(ITEMS_POS_LIST.get(index));

			itemFinishPoint = new Point(startPos.x, slot.pos.y);

			index++;
		}

		windowSize = new Point(RECIPE_TITLE.SIZE.x,
				(itemFinishPoint.y - windowPos.y) + RECIPE_WINDOW.ITEMS.SIZE.y + RECIPE_WINDOW.WINDOW_GAP.y);

	}

	public void addNewSlot(ItemStack[] ingredients) {

		ModIngredientSlot newSlot = new ModIngredientSlot(ingredients);

		ModIngredientSlot sameSlot = getSameSlot(newSlot);

		if (sameSlot == null) {
			slotList.add(newSlot);
		} else {
			sameSlot.incrementIngredientCount();
		}

	}

	public void addNewSlot(ItemStack ingredient, int count) {

		ModIngredientSlot newSlot = new ModIngredientSlot(ingredient, count);

		slotList.add(newSlot);

	}

	private ModIngredientSlot getSameSlot(ModIngredientSlot newSlot) {
		for (ModIngredientSlot slot : slotList) {
			if (slot.isSame(newSlot))
				return slot;
		}

		return null;
	}

	public ItemStack checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		for (ModIngredientSlot slot : slotList) {
			if (slot.checkIfHoveringOver(windowPos, mouseX, mouseY)) {

				curHoverItem = slot.curDisplayItem;

				curHoverItemID = GlobalFuncs.generateItemDataString(curHoverItem);

				return curHoverItem;
			}
		}
		curHoverItemID = null;
		curHoverItem = null;
		return null;

	}
}
