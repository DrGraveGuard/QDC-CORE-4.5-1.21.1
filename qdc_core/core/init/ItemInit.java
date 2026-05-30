package com.qdc_mod.qdc_core_4_5.qdc_core.core.init;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.items.MachineCore;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.items.ParticleItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.items.QdcMainItem;

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
	
	
	
	//particle fragments
	
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_NATURE = ITEMS.register("particle_fragment_nature", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_FOOD = ITEMS.register("particle_fragment_food", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_METAL = ITEMS.register("particle_fragment_metal", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_GEM = ITEMS.register("particle_fragment_gem", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_ENCHANTED = ITEMS.register("particle_fragment_enchanted", 
			() -> new ParticleItem(new Item.Properties()));
	public static final DeferredItem<ParticleItem> PARTICLE_FRAGMENT_POTION = ITEMS.register("particle_fragment_potion", 
			() -> new ParticleItem(new Item.Properties()));

	

	//machine cores
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_WOOD = ITEMS.register("qdc_core_quantum_core_wood", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_STONE = ITEMS.register("qdc_core_quantum_core_stone", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_IRON = ITEMS.register("qdc_core_quantum_core_iron", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_GOLD = ITEMS.register("qdc_core_quantum_core_gold", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_DIAMOND = ITEMS.register("qdc_core_quantum_core_diamond", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_EMERALD = ITEMS.register("qdc_core_quantum_core_emerald", 
			() -> new MachineCore(new Item.Properties()));
	
	public static final DeferredItem<MachineCore> QUANTUM_CORE_NETHERITE = ITEMS.register("qdc_core_quantum_core_netherite", 
			() -> new MachineCore(new Item.Properties()));
	
	
	
	public static final DeferredItem<Item> QUANTUM_MACHINE_SCREEN = ITEMS.register("qdc_machine_screen", 
			() -> new Item(new Item.Properties()));
}
