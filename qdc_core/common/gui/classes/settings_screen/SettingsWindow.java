package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.SettingsScreen;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.SettingsScreen.GuiButton;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SettingsWindow {

	private SettingsScreen screen;

	public DefaultItemParticles itemData = null;
	public List<CfgItemPosItem> cfgPosItemList = null;
	public List<GuiButton> nature_ButtonList = null;
	public List<GuiButton> food_ButtonList = null;
	public List<GuiButton> metal_ButtonList = null;
	public List<GuiButton> gem_ButtonList = null;
	public List<GuiButton> affectedItemsButtonList = null;
	public List<EditBox> txtList = null;

	public CfgDisplayItem natureDisplayItem;
	public CfgDisplayItem foodDisplayItem;
	public CfgDisplayItem metalDisplayItem;
	public CfgDisplayItem gemDisplayItem;

	public List<CfgDisplayItem> displayItemList = null;

	public AffectedItemsBox affectedItemBox;

	public String categoryName = "";

	public ItemStack curHoverParticle = null;

	public SettingsWindow(DefaultItemParticles itemData, List<CfgItemPosItem> cfgPosItemList,
			List<GuiButton> nature_ButtonList, List<GuiButton> food_ButtonList, List<GuiButton> metal_ButtonList,
			List<GuiButton> gem_ButtonList, List<GuiButton> affectedItemsButtonList, List<EditBox> txtList,
			SettingsScreen screen) {
		super();
		this.itemData = itemData;
		this.cfgPosItemList = cfgPosItemList;
		this.nature_ButtonList = nature_ButtonList;
		this.food_ButtonList = food_ButtonList;
		this.metal_ButtonList = metal_ButtonList;
		this.gem_ButtonList = gem_ButtonList;
		this.affectedItemsButtonList = affectedItemsButtonList;
		this.txtList = txtList;
		this.screen = screen;

		setupDisplayItems();
		setupDefaultMsg();
		enableAffectedItemsButtons();

		this.affectedItemBox = new AffectedItemsBox(affectedItemsButtonList, itemData.itemList);

	}

	
	//=====================
	// Nature Section
	
	public void handleEditNature() {
		disableOtherSections(natureDisplayItem.type);
		natureDisplayItem.handleEditClick();
	}

	public void handleSavelNature() {
		enableAllButons();
		natureDisplayItem.handleSaveClick();
	}

	public void handleCancelNature() {
		enableAllButons();
		natureDisplayItem.handleCancelClick();
	}

	public void handleSetDefaultlNature() {
		enableAllButons();
		natureDisplayItem.handleDefaultClick();

	}

	
	
	//=====================
	// Food Section
	
	public void handleEditFood() {
		disableOtherSections(foodDisplayItem.type);
		foodDisplayItem.handleEditClick();
	}

	public void handleSavelFood() {
		enableAllButons();
		foodDisplayItem.handleSaveClick();
	}

	public void handleCancelFood() {
		enableAllButons();
		foodDisplayItem.handleCancelClick();
	}

	public void handleSetDefaultlFood() {
		enableAllButons();
		foodDisplayItem.handleDefaultClick();

	}
	
	
	
	
	
	
	//=====================
	// Metal Section
	
	public void handleEditMetal() {
		disableOtherSections(metalDisplayItem.type);
		metalDisplayItem.handleEditClick();
	}

	public void handleSavelMetal() {
		enableAllButons();
		metalDisplayItem.handleSaveClick();
	}

	public void handleCancelMetal() {
		enableAllButons();
		metalDisplayItem.handleCancelClick();
	}

	public void handleSetDefaultlMetal() {
		enableAllButons();
		metalDisplayItem.handleDefaultClick();

	}
	
	
	
	
	
	
	
	
	
	//=====================
	// Gem Section
	
	public void handleEditGem() {
		disableOtherSections(gemDisplayItem.type);
		gemDisplayItem.handleEditClick();
	}

	public void handleSavelGem() {
		enableAllButons();
		gemDisplayItem.handleSaveClick();
	}

	public void handleCancelGem() {
		enableAllButons();
		gemDisplayItem.handleCancelClick();
	}

	public void handleSetDefaultlGem() {
		enableAllButons();
		gemDisplayItem.handleDefaultClick();

	}
	
	
	
	
	
	
	
	private void disableOtherSections(ParticleType ignore) {
		for (CfgDisplayItem item : displayItemList) {
			if (item.type != ignore) {
				item.btnEdit.setEnabledState(false);
				item.btnDefault.setEnabledState(false);
			}
		}
	}

	private void enableAllButons() {
		for (CfgDisplayItem item : displayItemList) {
			if (item.isEnabled) {
				item.btnEdit.setEnabledState(true);
				item.updateDefaultButton();
			}
		}

	}

	private void enableAffectedItemsButtons() {

		for (GuiButton btn : affectedItemsButtonList) {
			btn.setEnabledState(false);
			btn.setVisibleState(true);
		}
	}

	private void setupDefaultMsg() {
		if (itemData.tag != null) {
			ResourceLocation tagLocation = itemData.tag.location();

			String path = tagLocation.getPath();

			this.categoryName = "Category [" + path.toUpperCase().replace("_", " ") + "]";
		} else if (itemData.partialName != null) {
			this.categoryName = "Category [" + itemData.partialName.toUpperCase().trim() + "]";
		}

		else if (itemData.isAutoGenerated) {
			this.categoryName = "Item [" + GlobalFuncs.getItemName(itemData.itemList.get(0)) + "]";
		} else {
			this.categoryName = "Item [" + GlobalFuncs.getItemName(itemData.itemList.get(0)) + "]";
		}

		String titleMsg = "Editing particles for: " + categoryName;

		screen.setTitleMsg(titleMsg);
	}

	private void setupDisplayItems() {

		natureDisplayItem = new CfgDisplayItem(ParticleType.NATURE, cfgPosItemList.get(0), itemData, nature_ButtonList,
				txtList.get(0), txtList.get(4), screen);

		foodDisplayItem = new CfgDisplayItem(ParticleType.FOOD, cfgPosItemList.get(1), itemData, food_ButtonList,
				txtList.get(1), txtList.get(4), screen);

		metalDisplayItem = new CfgDisplayItem(ParticleType.METAL, cfgPosItemList.get(2), itemData, metal_ButtonList,
				txtList.get(2), txtList.get(4), screen);

		gemDisplayItem = new CfgDisplayItem(ParticleType.GEM, cfgPosItemList.get(3), itemData, gem_ButtonList,
				txtList.get(3), txtList.get(4), screen);

		displayItemList = new ArrayList<CfgDisplayItem>();

		displayItemList.add(natureDisplayItem);
		displayItemList.add(foodDisplayItem);
		displayItemList.add(metalDisplayItem);
		displayItemList.add(gemDisplayItem);
	}

	public void checkIfHoveringOver(Point wINDOW_POS, int x, int y) {

		this.affectedItemBox.checkIfHoveringOver(wINDOW_POS, x, y);

		curHoverParticle = null;

		for (CfgDisplayItem item : displayItemList) {

			if (item.checkIfHoveringOver(wINDOW_POS, x, y)) {
				curHoverParticle = new ItemStack(item.icon);
			}
		}

	}

}
