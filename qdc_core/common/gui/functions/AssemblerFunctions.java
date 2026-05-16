package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.MainBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.EnchantmentFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData.MyData;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

public class AssemblerFunctions {

	public static void assembleSingleItem(ModItemData itemData) {
		if (itemData.canCreate) {

			int slotIndex = Qdc.curPlayer.getInventory().getSlotWithRemainingSpace(itemData.itemStack);

			if (slotIndex == -1) {
				slotIndex = Qdc.curPlayer.getInventory().getFreeSlot();
			}

			if (slotIndex > -1) {
				assembleItem(itemData, slotIndex, 1);
			}
		}
	}

	public static void assembleStack(ModItemData itemData) {
		if (itemData.canCreate) {

			int slotIndex = Qdc.curPlayer.getInventory().getSlotWithRemainingSpace(itemData.itemStack);

			if (slotIndex == -1) {
				slotIndex = Qdc.curPlayer.getInventory().getFreeSlot();
			}

			if (slotIndex > -1) {
				int max = itemData.itemStack.getMaxStackSize();
				assembleItem(itemData, slotIndex, max);
			}
		}
	}  

	public static void assembleAll(ModItemData itemData) {
		if (itemData.canCreate) {

			for (int i = 0; i < 36; i++) {

				itemData.calcCanCreateAmount();

				if (itemData.canCreateAmount < 1)
					break;

				int slotIndex = Qdc.curPlayer.getInventory().getSlotWithRemainingSpace(itemData.itemStack);

				if (slotIndex == -1) {
					slotIndex = Qdc.curPlayer.getInventory().getFreeSlot();
				}

				if (slotIndex > -1) {
					int max = itemData.itemStack.getMaxStackSize();
					assembleItem(itemData, slotIndex, max);
				} else {
					break;
				}
			}
		}
	}

	private static void assembleItem(ModItemData itemData, int slotIndex, int count) {

		if (slotIndex < 0)
			return;

		if (itemData.itemStack.getItem() == Items.ENCHANTED_BOOK) {

			if (!GlobalFuncs.isEnchanted(itemData.itemStack))
				return;
		}

		itemData.calcCanCreateAmount();
		int canMake = itemData.canCreateAmount;

		if (canMake < 1)
			return;

		ParticleCollection toRemove = new ParticleCollection();
		toRemove.addOtherParticleCollection(itemData.itemParticles);

		int maxStackSize = itemData.itemStack.getMaxStackSize();
		int toMake = count;
		int itemID = Item.getId(itemData.itemStack.getItem());
		// add items
		if (toMake > maxStackSize) {
			toMake = maxStackSize;
		}

		if (toMake > canMake) {
			toMake = canMake;
		}

		if (Qdc.curPlayer.getInventory().getItem(slotIndex).getItem() == Items.AIR) {
			toRemove.multiply(toMake);

			if (QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(toRemove)) {

				if (itemData.itemStack.getItem() == Items.ENCHANTED_BOOK) {

					ItemStack correctEnchantedBook = EnchantmentFunctions.createRealEnchantedBook(itemData.itemStack);

					Qdc.AssemblerVariables.stackToAssemble = correctEnchantedBook.copy();
					Qdc.curPlayer.getInventory().setItem(slotIndex, correctEnchantedBook.copy());
				} else if (GlobalFuncs.isPotion(itemData.itemStack)) {

					Qdc.AssemblerVariables.stackToAssemble = itemData.itemStack.copy();
					Qdc.curPlayer.getInventory().setItem(slotIndex, itemData.itemStack.copy());
				} else if (GlobalFuncs.isTippedArrow(itemData.itemStack)) {

					ItemStack tempArrowStack = itemData.itemStack.copy();
					tempArrowStack.setCount(toMake);

					Qdc.AssemblerVariables.stackToAssemble = tempArrowStack.copy();

					Qdc.curPlayer.getInventory().setItem(slotIndex, tempArrowStack);
				} else if (GlobalFuncs.isRocket(itemData.itemStack)) {

					ItemStack tempRocketStack = itemData.itemStack.copy();
					tempRocketStack.setCount(toMake);

					Qdc.AssemblerVariables.stackToAssemble = tempRocketStack.copy();

					Qdc.curPlayer.getInventory().setItem(slotIndex, tempRocketStack);
				}  else if (GlobalFuncs.isSusStew(itemData.itemStack)) {

					ItemStack tempSusStew = itemData.itemStack.copy();
					tempSusStew.setCount(toMake);

					Qdc.AssemblerVariables.stackToAssemble = tempSusStew.copy();

					Qdc.curPlayer.getInventory().setItem(slotIndex, tempSusStew);
				}  else {

					Qdc.curPlayer.getInventory().setItem(slotIndex,
							new ItemStack(itemData.itemStack.getItem(), toMake));

				}

				PacketDistributor.sendToServer(new MyData(slotIndex, itemID, toMake));

			}
		} else {
			if (maxStackSize == 1)
				return;

			int oldCount = Qdc.curPlayer.getInventory().getItem(slotIndex).getCount();
			int space = maxStackSize - oldCount;

			if (space <= 0)
				return;

			if (space < toMake) {
				toMake = space;
			}

			toRemove.multiply(toMake);

			if (QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(toRemove)) {

				int newCount = oldCount + toMake;

				if (GlobalFuncs.isTippedArrow(itemData.itemStack)) {

					ItemStack tempArrowStack = itemData.itemStack.copy();
					tempArrowStack.setCount(newCount);

					Qdc.AssemblerVariables.stackToAssemble = tempArrowStack.copy();

					Qdc.curPlayer.getInventory().setItem(slotIndex, tempArrowStack);
					PacketDistributor.sendToServer(new MyData(slotIndex, itemID, newCount));
				} else {

					Qdc.curPlayer.getInventory().setItem(slotIndex,	new ItemStack(itemData.itemStack.getItem(), newCount));
					PacketDistributor.sendToServer(new MyData(slotIndex, itemID, newCount));
				}
			}
		}

		itemData.calcCanCreateAmount();

	}
	
	
	
	public static int assembleItemForApi(ItemStack stack, int count)
	{
		if(count < 1) return -1;
		
		if (stack.getItem() == Items.AIR)
			return -1;
		
		
		ModItemData itemData = RecipeBox.getDataItem(stack);
		
		if(itemData == null)
			return -1;
		

		
		itemData.calcCanCreateAmount();
		
		int canMake = itemData.canCreateAmount;
		
		if(canMake == 0)
			return 0;
		
		if(canMake > count)
			canMake = count;
		
		ParticleCollection particles = itemData.itemParticles.clone();
		
		particles.multiply(canMake);
		
		QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(particles);
		MainBox.saveData(Qdc.curPlayer);
		
		return canMake;
	}

}
