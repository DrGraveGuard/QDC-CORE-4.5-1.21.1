package com.qdc_mod.qdc_core_4_5;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.MainBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.functions.LootFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.RecipeBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.ModRegistry;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.PlayerLoadingFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.ServerPayloadHandler;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData.MyData;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData2.MyData2;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent.Save;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Qdc.MOD_ID)
public class Qdc {
	public static final String MOD_ID = "qdc_core_4_5";
	

	public static boolean isFinishedSettingParticles = false;
	public static boolean isFinishedLoadingData = false;
	
	
	public class AssemblerVariables
	{

		public static String searchString = "";
		
		public static final int ITEM_CHANGE_MAX_TICK_COUNT =100;
		
		public static ItemStack stackToAssemble = null;
	}
	

	public class SettingsScreenVariables
	{

		public static String searchString = "";
		
		public static final int ITEM_CHANGE_MAX_TICK_COUNT =100;
		
		public static final double NATURE_MAX =50;
		public static final double FOOD_MAX =10;
		public static final double METAL_MAX =10;
		public static final double GEM_MAX =5;
		

		public static final double MIN =0.25d;
		
		
		
		public static final int txtLenNature = 6;
		public static final int txtLenFood = 5;
		public static final int txtLenMetal = 5;
		public static final int txtLenGem = 5;
		
		
		
	}
	
	public class ParticleConstants
	{
		public static final double ENCHANMENT_LEVEL_PARTICLES =1.0d;
		public static final double POTION_PARTICLES =1.0d;
		public static final double EXTRATRA_CRAFTING_PARTICLE_PERCENTAGE =0.01d;
	}
	
	public class LootConstants
	{
		private static final float BASE = 10;
		
		public static final float NATURE = BASE*10;
		public static final float FOOD = BASE*20;
		public static final float METAL = BASE*30;
		public static final float GEM = BASE*50;
		public static final float ENCHANTED = BASE*70;
		public static final float POTION = BASE*80;
		
		
		public static final float UNOWNED = BASE*250;
	}
	
	public class DisassemblerVariables {
		public static final int ITEM_LIMIT = 24;

		public static List<ItemStack> discoveredItems = new ArrayList<ItemStack>();

		public static void addNewDIscoveredItem(ItemStack stack) {
			discoveredItems.add(stack);
		}

		public static List<ItemStack> getDiscoveredItems() {
			List<ItemStack> res = new ArrayList<ItemStack>();

			if (discoveredItems.size() <= ITEM_LIMIT)
				return discoveredItems;

			int count = discoveredItems.size();
			int extra = count - ITEM_LIMIT;

			for (int i = extra; i < discoveredItems.size(); i++) {
				res.add(discoveredItems.get(i));
			}

			return res;
		}
		
		public static void clear()
		{
			discoveredItems = new ArrayList<ItemStack>();
		}
	}
	
	public static Player curPlayer = null;
	public static ServerPlayer serverPlayer = null;
	public static ServerLevel serverLevel = null;
	
	public Qdc(IEventBus modEventBus, ModContainer modContainer) {
		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);

		// Register ourselves for server and other game events we are interested in.
		// Note that this is necessary if and only if we want *this* class (ExampleMod)
		// to respond directly to events.
		// Do not add this line if there are no @SubscribeEvent-annotated functions in
		// this class, like onServerStarting() below.
		NeoForge.EVENT_BUS.register(this);
		
		ModRegistry.registerItems(modEventBus);
		ModRegistry.registerBlocks(modEventBus);
		ModRegistry.registerCreativeTabs(modEventBus);
		ModRegistry.registerMenus(modEventBus);
		ModRegistry.registerAttachments(modEventBus);
		ModRegistry.registerBlockEntities(modEventBus);

		// Register our mod's ModConfigSpec so that FML can create and load the config
		// file for us
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
//		ItemBlockRenderTypes.setRenderLayer(BlockInit.EMPTY_PPM_CONTAINER.get(), ChunkSectionLayer.TRANSLUCENT);
//		ItemBlockRenderTypes.setRenderLayer(com.qdc_mod.qdc_core_4_5.qdc_machines.core.init.BlockInit.AMBER_BLOCK.get(),  ChunkSectionLayer.TRANSLUCENT);
//		ItemBlockRenderTypes.setRenderLayer(com.qdc_mod.qdc_core_4_5.qdc_machines.core.init.BlockInit.QDC_LIGHT_BLOCK.get(),  ChunkSectionLayer.TRANSLUCENT);
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {

		
		
	}

	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.PlayerLoggedInEvent event) {

		curPlayer = event.getEntity();
		serverLevel = (ServerLevel)curPlayer.level();
		MainBox.processItems(serverLevel);
		MainBox.loadModData(curPlayer);
		
		PlayerLoadingFunctions.onPlayerLoad(event);
	}

	@SubscribeEvent
	public void onPlayerClose(PlayerEvent.PlayerLoggedOutEvent event) {
		MainBox.clearModData();
		
		DisassemblerVariables.clear();
		RecipeBox.clear();
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		curPlayer = event.getEntity();
		
		MainBox.saveData(curPlayer);
	}

	@SubscribeEvent
	public void onWorldSave(Save event) {

		MainBox.saveData(curPlayer);

	}
	
	@SubscribeEvent
	public void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
	    PotionBrewing builder = event.getBuilder().build();
	

	
	}

	@SubscribeEvent
	public void onMobKill(LivingDeathEvent event) {
		LootFunctions.handleMobKIll(event);
	}

    @SubscribeEvent
    public  void onEntityHurt(LivingDamageEvent.Post event) {
       
    	LootFunctions.onEntityHurt(event);
    }

	// You can use EventBusSubscriber to automatically register all static methods
	// in the class annotated with @SubscribeEvent
	@EventBusSubscriber(modid = MOD_ID,  value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {

		}

		@SubscribeEvent
		public static void register(final RegisterPayloadHandlersEvent event) {
			final PayloadRegistrar registrar = event.registrar("1");
			registrar.playBidirectional(MyData.TYPE, MyData.STREAM_CODEC, ServerPayloadHandler::handleInventoryDataOnMain);
			registrar.playBidirectional(MyData2.TYPE, MyData2.STREAM_CODEC, ServerPayloadHandler::handleTeleportDataOnMain);
		}


		
	    @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
//            event.registerBlockEntityRenderer(BlockEntityInit.PPM_BLOCKENTITY.get(), PPM_BER::new);
        }

		
		@SubscribeEvent
		public static void registerScreens(RegisterMenuScreensEvent event) {
//
//			event.register(MenuInit.ASSEMBLER_MENU.get(), AssemblerScreen::new);
		}
	}
}