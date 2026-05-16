package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.LootBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModItemData;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class LootFunctions {

	private static final Random random = new Random();

	public static void onEntityHurt(LivingDamageEvent.Post event) {
		float damage = event.getNewDamage(); // The raw damage amount
		LivingEntity entity = event.getEntity(); // Entity being hit

		if (event.getSource().getEntity() instanceof LivingEntity player)
			if (player == Qdc.curPlayer) {

				LootBox.updateMobHurt(damage);
			}
	}

	public static void handleMobKIll(LivingDeathEvent event) {

		if (event.getSource().getEntity() instanceof LivingEntity player)
			if (player == Qdc.curPlayer) {
				if (event.getEntity() instanceof LivingEntity livingEntity) {

					List<ItemStack> lootList = LootBox.checkForLoot();

					if (lootList.size() > 0) {
						for (ItemStack stack : lootList) {
							ItemEntity itemEntity = new ItemEntity(Qdc.serverLevel, livingEntity.position().x,
									livingEntity.position().y, livingEntity.position().z, stack);
							Qdc.serverLevel.addFreshEntity(itemEntity);
						}
					}

				}
			}

	}

	public static ItemStack getRandomUNdiscoveredItem() {
		List<ModItemData> undiscovered = RecipeBox.getUndiscoveredItemList();

		if (undiscovered.size() == 0) {
			undiscovered = new ArrayList<ModItemData>(RecipeBox.itemList_ordered);
		}

		int randomIndex = 0;

		if (undiscovered.size() > 1) {
			randomIndex = random.nextInt(undiscovered.size());
		}

		return undiscovered.get(randomIndex).itemStack;

	}

}
