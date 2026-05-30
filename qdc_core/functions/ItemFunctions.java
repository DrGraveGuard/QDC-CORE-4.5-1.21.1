package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.neoforged.neoforge.common.ItemAbilities;

public class ItemFunctions {

	public static List<ItemStack> getDiscoveredToolsAndWeps() {
		List<ItemStack> res = new ArrayList<ItemStack>();

		for (ModItemData item : RecipeBox.itemList_ordered) {
			if (isToolOrWep(item.itemStack)) {

				if (item.isDirectlyDiscovered()) {
					res.add(item.itemStack.copy());
				}
			}
		}

		return res;
	}

	private static boolean isToolOrWep(ItemStack stack) {
		if (isTool(stack))
			return true;

		if (isWeapon(stack))
			return true;

		return false;
	}

	private static boolean isTool(ItemStack stack) {

		if (stack.has(DataComponents.TOOL))
			return true;

		if (stack.canPerformAction(ItemAbilities.PICKAXE_DIG) || stack.canPerformAction(ItemAbilities.AXE_DIG))
			return true;

		return false;
	}

	private static boolean isWeapon(ItemStack stack) {
		if (stack.getItem() instanceof SwordItem || stack.getItem() instanceof TieredItem) {
			return true;
		}

		var attributeModifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
		if (attributeModifiers != null && !attributeModifiers.modifiers().isEmpty()) {
			// You can also refine this to strictly check for Attack Damage modifiers here
			return true;
		}

		return false;
	}
}
