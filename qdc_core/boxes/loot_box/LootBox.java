package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.classes.LootBoxItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.LOOT_ITEM;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.AttachmentInit;

import net.minecraft.world.item.ItemStack;

public class LootBox {

	public static List<LootBoxItem> lootItemList = null;

	public static void clear() {
		lootItemList = null;
	}

	public static void initLootBox() {
		if (lootItemList == null) {
			generateLootBoxItems();
		}
	}

	public static void generateLootBoxItems() {

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE), Qdc.LootConstants.NATURE,
				AttachmentInit.NATURE_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.FOOD), Qdc.LootConstants.FOOD,
				AttachmentInit.FOOD_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.METAL), Qdc.LootConstants.METAL,
				AttachmentInit.METAL_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.GEM), Qdc.LootConstants.GEM,
				AttachmentInit.GEM_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.ENCHANTED), Qdc.LootConstants.ENCHANTED,
				AttachmentInit.ENCHANTED_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.POTION), Qdc.LootConstants.POTION,
				AttachmentInit.POTION_DAMAGE_SACRIFICE.get()));

		add(new LootBoxItem(Qdc.LootConstants.UNOWNED));
	}

	private static void add(LootBoxItem lootItem) {
		if (lootItemList == null)
			lootItemList = new ArrayList<LootBoxItem>();

		if (lootItemList.size() == 0) {
			lootItem.setPos(SacrificeScreenSettinigs.LOOT_ITEM.ITEM_START_POS);
			lootItemList.add(lootItem);
		} else {
			LootBoxItem lastItem = lootItemList.get(lootItemList.size() - 1);

			Point newPos = new Point(lastItem.pos.x, lastItem.pos.y + lastItem.size.y + LOOT_ITEM.GAP);

			lootItem.setPos(newPos);
			lootItemList.add(lootItem);
		}

	}

	public static void setupForScreen() {
		initLootBox();

		for (LootBoxItem item : lootItemList) {

			item.setupForScreen();

		}
	}

	public static void notifyNewDiscovery() {

		initLootBox();

		for (LootBoxItem item : lootItemList) {

			if (item.isRandomItem) {
				item.checkIfItemIsDiscovered();
			}

		}
	}

	public static void updateMobHurt(float damage) {
		initLootBox();

		for (LootBoxItem item : lootItemList) {

			item.updateMobHurt(damage);

		}

	}

	public static List<ItemStack> checkForLoot() {
		initLootBox();

		List<ItemStack> lootList = new ArrayList<ItemStack>();

		for (LootBoxItem item : lootItemList) {

			ItemStack tempDrop = item.CheckForLoot();

			if (tempDrop != null)
				lootList.add(tempDrop);
		}

		return lootList;
	}

	

	public static ItemStack checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		for (LootBoxItem item : lootItemList) {
			
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				return item.toDrop.copy();
			}
		}
		
		return null;
	}
	
}
