package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.classes;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.MainDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.functions.LootFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.INFO_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.LOOT_ITEM;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.SUB_TITLE.AMOUNT_LEFT;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.SUB_TITLE.CUR_AMOUNT;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.SUB_TITLE.DROP_ITEM;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.SUB_TITLE.PROGRESS;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.AttachmentInit;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.attachment.AttachmentType;

public class LootBoxItem {

	public ItemStack toDrop;
	public float curDamage = 0;
	public float targetDamage;
	public float damageLeft = -1;
	public boolean isRandomItem = false;
	AttachmentType<Float> attachment;

	public String curDamageStr = null;
	public String damageLeftStr = null;
	public String progressStr = null;

	public Point pos;
	public Point size;

	public Point iconPos;
	public Point curDamagePos;
	public Point damageLeftPos;

	public Point progressStrPos;
	public Point progressBarBorderPos;
	public Point progressBarFillPos;
	public int progressBarFillWidth;


	
	public LootBoxItem(ItemStack toDrop, float target, AttachmentType<Float> attachment) {

		this.toDrop = toDrop;
		this.targetDamage = target;
		this.attachment = attachment;

		loadDamageData();

		size = SacrificeScreenSettinigs.LOOT_ITEM.SIZE;
	}

	public LootBoxItem(float targetDamage) {

		this.targetDamage = targetDamage;
		this.attachment = AttachmentInit.RANDOM_ITEM_DAMAGE_SACRIFICE.get();
		loadDamageData();
		loadRandomItem();
		isRandomItem = true;

		size = SacrificeScreenSettinigs.LOOT_ITEM.SIZE;
	}

	public void setupForScreen() {
		
		int curDamageInt = (int)curDamage;
		int maxDamageInt = (int)targetDamage;
		
		
		if(curDamageInt > maxDamageInt)
			curDamageInt = maxDamageInt;
		
		
		
		curDamageStr = curDamageInt + " / " + maxDamageInt;

		int damageLeft = (maxDamageInt - curDamageInt);

		if (damageLeft == 0)
		{
			damageLeftStr = "0";
			progressStr ="100%";
			progressBarFillWidth = LOOT_ITEM.BAR_FILL_MAX_WIDTH;
		}
		else
		{
			damageLeftStr = damageLeft + "";
			
			int perc = (int)calcPerc(targetDamage, curDamage);
			
			progressStr = perc +"%";
			
			float barWidth = (float)LOOT_ITEM.BAR_FILL_MAX_WIDTH / 100 * perc;
			progressBarFillWidth = (int)barWidth;
		}
		
		
		
	}

	
	private float calcPerc(float total, float cur)
	{
		return cur/total*100;
	}
	
	
	public void setPos(Point pos) {
		this.pos = pos;
		this.iconPos = new Point(DROP_ITEM.TEXT_POS.x - 8, pos.y + calcMiddleVertical(16));
		this.curDamagePos = new Point(CUR_AMOUNT.TEXT_POS.x, pos.y + calcMiddleVertical(8));
		this.damageLeftPos = new Point(AMOUNT_LEFT.TEXT_POS.x, pos.y + calcMiddleVertical(8));

		this.progressStrPos = new Point(PROGRESS.TEXT_POS.x, pos.y + calcMiddleVertical(8));
		this.progressBarBorderPos = new Point(PROGRESS.DECORATION_POS.x+5,
				pos.y + calcMiddleVertical(LOOT_ITEM.BAR_BORDER_SIZE.y));
		this.progressBarFillPos = new Point(PROGRESS.DECORATION_POS.x + 6,
				pos.y + calcMiddleVertical(LOOT_ITEM.BAR_FILL_HEIGHT));
	}

	private int calcMiddleVertical(int itemHeight) {
		return (this.size.y / 2) - (itemHeight / 2);
	}

	public void checkIfItemIsDiscovered() {
		if (MainDiscoveryBox.isItemDiscovered(toDrop)) {
			setRandomUndiscoveredItem();
		}
	}

	private void saveDamageData() {
		Qdc.curPlayer.setData(attachment, curDamage);

	}

	private void loadDamageData() {
		if (Qdc.curPlayer.hasData(attachment)) {
			curDamage = Qdc.curPlayer.getData(attachment);
		}
	}

	private void loadRandomItem() {

		if (Qdc.curPlayer.hasData(AttachmentInit.SACRIFICE_RANDOM_ITEM.get())) {
			String itemID = Qdc.curPlayer.getData(AttachmentInit.SACRIFICE_RANDOM_ITEM.get());

			ModItemData tempItemData = RecipeBox.getDataItem(itemID);

			if (tempItemData != null) {
				toDrop = tempItemData.itemStack.copy();
			} else {
				setRandomUndiscoveredItem();
			}
		} else {
			setRandomUndiscoveredItem();
		}
	}

	public void updateMobHurt(float hitDamage) {
		this.curDamage += hitDamage;
		saveDamageData();
	}

	public ItemStack CheckForLoot() {
		if (curDamage >= targetDamage) {

			curDamage = 0;

			ItemStack tempDrop = toDrop.copy();

			if (isRandomItem) {
				ModItemData tempDataItem = RecipeBox.getDataItem(tempDrop);

				if (tempDataItem != null) {
					if (!tempDataItem.isDirectlyDiscovered()) {
						GlobalFuncs.showInGameMessage(
								"Undiscovered Item Dropped: " + tempDrop.getDisplayName().getString());
					}

				}

				setRandomUndiscoveredItem();
			}

			return tempDrop;
		}

		return null;
	}

	private void setRandomUndiscoveredItem() {
		toDrop = LootFunctions.getRandomUNdiscoveredItem().copy();

		Qdc.curPlayer.setData(AttachmentInit.SACRIFICE_RANDOM_ITEM.get(), GlobalFuncs.generateItemDataString(toDrop));
	}
	
	

	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		// in item list
		if(GuiFunctions.isHoveringOver(windowPos, iconPos, new Point(16,16),
				new Point(mouseX, mouseY)))
		{
			
			return true;
		}


		
		return false;
	}

}
