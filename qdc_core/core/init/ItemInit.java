package com.qdc_mod.qdc_core_4_5.qdc_core.core.init;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.items.ParticleItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.items.QdcMainItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Qdc.MOD_ID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
	
	
	// main item
	public static final DeferredItem<QdcMainItem> QDC_MAIN_ITEM = ITEMS.register("qdc_core_main_item",
			() -> new QdcMainItem(new Item.Properties()));
	
	


	public static final DeferredItem<ParticleItem> PARTICLES_NATURE = ITEMS.register("particles_nature", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLES_FOOD = ITEMS.register("particles_food", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLES_METAL = ITEMS.register("particles_metal", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLES_GEM = ITEMS.register("particles_gem", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLES_ENCHANTED = ITEMS.register("particles_enchanted", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLES_POTION = ITEMS.register("particles_potion", 
			() -> new ParticleItem(new Item.Properties()));
	
	
	

	
}
