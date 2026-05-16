package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.EnchantmentFunctions;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class MainDiscoveryBox {

	public static void handleDisassemblerItemDiscovery(ItemStack stack) {
		if (GlobalFuncs.isEnchanted(stack)) {
			ItemEnchantments enchantments = null;

			enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);
			handlenchantments(enchantments);

			enchantments = stack.get(DataComponents.ENCHANTMENTS);
			handlenchantments(enchantments);
			
			if(stack.getItem() != Items.ENCHANTED_BOOK)
			{
				
				ItemStack tempStack = new ItemStack(stack.getItem());
				String itemID = GlobalFuncs.generateItemDataString(tempStack);
				
				if(RecipeBox.getDataItem(tempStack) != null)
					if (!BaseDiscoveryBox.isBaseItemDiscovered(itemID)) {
						BaseDiscoveryBox.addNewDiscoveredItem(itemID);
						Qdc.DisassemblerVariables.addNewDIscoveredItem(tempStack);
					}
			}
			

		} else {

			String itemID = GlobalFuncs.generateItemDataString(stack);
			if(RecipeBox.getDataItem(stack) != null)
			if (!BaseDiscoveryBox.isBaseItemDiscovered(itemID)) {
				BaseDiscoveryBox.addNewDiscoveredItem(itemID);
				Qdc.DisassemblerVariables.addNewDIscoveredItem(stack.copy());
			}
		}
		saveData(Qdc.curPlayer);
	}

	private static void handlenchantments(ItemEnchantments enchantments) {
		if (enchantments != null) {

			for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				int level = entry.getIntValue();

				ItemStack newBookItem = EnchantmentFunctions.createEnchantedBook(enchantment, level);

				String enchantmentID = GlobalFuncs.generateItemDataString(newBookItem);

				if (!BaseDiscoveryBox.isBaseItemDiscovered(enchantmentID)) {
					BaseDiscoveryBox.addNewDiscoveredItem(enchantmentID);

					Qdc.DisassemblerVariables.addNewDIscoveredItem(newBookItem.copy());
				}

			}

		}
	}

	public static boolean isItemDiscovered(ItemStack stack) {

		String searchID = GlobalFuncs.generateItemDataString(stack);


		
		if (BaseDiscoveryBox.isBaseItemDiscovered(searchID)) {
			return true;
		}

		return false;
	}



	public static void saveData(Player curPlayer) {
		BaseDiscoveryBox.saveData(curPlayer);
	}

}
