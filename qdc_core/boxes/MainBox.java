package com.qdc_mod.qdc_core_4_5.qdc_core.boxes;

import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.DiscoveredEnchantmentDataItem;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.ConfigFileBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.functions.FileFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.BaseDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.LootBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.DefaultParticlesBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.ParticleStorage;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions.ParticleAssignmentFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.EnchantmentInitFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.ItemRecipeFuctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.SusStewFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.ParticleRegistrationFunction;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MainBox {

	public static void saveData(Player curPlayer) {
		ParticleStorage.saveData(curPlayer);
		BaseDiscoveryBox.saveData(curPlayer);
	}

	public static void clearModData() {

		ItemRecipeFuctions.clear();
		PotionInitFunctions.clear();
		RecipeBox.clear();
		DefaultParticlesBox.clear();
		ConfigFileBox.clear();
		LootBox.clear();
		SusStewFunctions.clear();
		BaseDiscoveryBox.clear();
		
		Qdc.AssemblerVariables.clear();
		Qdc.SettingsScreenVariables.clear();
		Qdc.DisassemblerVariables.clear();
		
		Qdc.isFinishedSettingParticles = false;
		Qdc.isFinishedLoadingData = false;
	}

	public static void loadModData(Player curPlayer) {
		ParticleStorage.loadPlayerParticles(curPlayer);
		BaseDiscoveryBox.loadData(curPlayer);
		
		Qdc.isFinishedLoadingData = true;
		
	}

	public static void processItems(ServerLevel serverLevel) {

		ItemRecipeFuctions.extractFromCraftingRecipes(serverLevel);
		EnchantmentInitFunctions.setupEchantmentItems(serverLevel);
		PotionInitFunctions.registerPotions(serverLevel);
		SusStewFunctions.registerSusStews();
		ParticleRegistrationFunction.registerMainParticleItems();
		RecipeBox.generateOrderedItemList();
		RecipeBox.fillEnchantmentList();

		DefaultParticlesBox.setupDefaultParticles(serverLevel);

		FileFunctions.handleConfigFile();

		ConfigFileBox.assignConfigParticles();

		ParticleAssignmentFunctions.assignParticlesToAllItems();
		
		
		Qdc.isFinishedSettingParticles = true;
		
		
	}



	
}
