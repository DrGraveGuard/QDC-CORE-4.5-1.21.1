package com.qdc_mod.qdc_core_4_5.api;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.BaseDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.MainDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.ParticleStorage;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions.ParticleFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionSearchFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.AssemblerFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.DisassemblerFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.MainMenuScreen;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.ItemInit;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.ParticleIconFunctions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class QdcApi {

	public class QDC_CORE {

		public class ENUMS
		{
			public static enum TextureColor {
				GRAY_1,GRAY_2,
				WHITE_1, WHITE_2,
				BLACK_1, BLACK_2,
				BLUE_1, BLUE_2,
				GREEN_1, GREEN_2,
				RED_1, RED_2,
				YELLOW_1, YELLOW_2,
				PURPLE_1, PURPLE_2,
				ORANGE_1, ORANGE_2
			}
			
			public static enum TtleType
			{
				MAIN_TITLE, SUB_TITLE,
			}
			
		}
		
		
		
		public class ITEMS {

			public class PARTICLE_ITEM {
				public static final Item NATURE = ItemInit.PARTICLES_NATURE.get();
				public static final Item FOOD = ItemInit.PARTICLES_FOOD.get();
				public static final Item METAL = ItemInit.PARTICLES_METAL.get();
				public static final Item GEM = ItemInit.PARTICLES_GEM.get();
				public static final Item ENCHANTED = ItemInit.PARTICLES_ENCHANTED.get();
				public static final Item POTION = ItemInit.PARTICLES_POTION.get();
			}

			public static final Item QDC_MAIN_ITEM = ItemInit.QDC_MAIN_ITEM.get();

		}
		
	

		public class FUNCTIONS {
			
			public static void showMainMenuScreen()
			{
				Minecraft.getInstance().setScreen(new MainMenuScreen());
			}
			
			public static double getBaseEnchantmentParticleValue()
			{
				return Qdc.ParticleConstants.ENCHANMENT_LEVEL_PARTICLES;
			}
			
			
			public static List<DiscoveredEnchantmentDataItem> getAllDiscoveredEnchantments()
			{
				return RecipeBox.getAllDiscoveredEnchantments();
			}
			
			public static List<ItemStack> getDiscoveredPotionsByVialType(Item vialType) {
				return PotionSearchFunctions.getDiscoveredPotionsByVialType(vialType);
			}
			
			
			public static int disassembleItems(ItemStack stack, int count)
			{
				return DisassemblerFunctions.disassembleItemForApi(stack, count);
			}
			
			public static int assembleItems(ItemStack stack, int count)
			{
				return AssemblerFunctions.assembleItemForApi(stack, count);
			}
			
			public static  double getParticlesDouble(ParticleType particleType) {
				return ParticleStorage.getParticlesDouble(particleType);
			}
			
			public static  BigDecimal getParticlesBigDecimal(ParticleType particleType) {
				
				return ParticleStorage.getParticlesBigDecimal(particleType);

			}
			

			public static Item getParticleIconItem(ParticleType type) {

				return ParticleIconFunctions.getParticleIconItem(type);
			}

			public static Color getParticleTextColor(ParticleType type) {
				return ParticleIconFunctions.getParticleTextColor(type);
			}

			public static boolean itemHasParticles(ItemStack stack) {

				return ParticleFunctions.itemHasParticles(stack);
			}
			


			public static void addParticles(ParticleCollection particles) {

				ParticleStorage.addParticleCollection(particles);

			}
			
			public static boolean removeParticleCollection(ParticleCollection toRemove) {
				return ParticleStorage.removeParticleCollection(toRemove);
			}

			public static ParticleCollection getItemParticles(ItemStack stack) {
				return ParticleFunctions.getItemParticles(stack);
			}

			public static boolean hasEnoughParticles(ParticleType type, double amount) {
				return ParticleStorage.hasEnoughParticles(type, amount);
			}

			public static String getParticleAmountStringFromStorage(ParticleType type) {

				return ParticleStorage.getParticlesString(type);
			}
			
			public static int countCanMakeAmount(ParticleCollection itemParticles) {
				
				return ParticleStorage.countCanMakeAmount(itemParticles);
					
				
			}
			
		}
	}
}
