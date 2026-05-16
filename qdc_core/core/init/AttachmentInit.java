package com.qdc_mod.qdc_core_4_5.qdc_core.core.init;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.qdc_mod.qdc_core_4_5.Qdc;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AttachmentInit {
	private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Qdc.MOD_ID);


	public static void register(IEventBus eventBus) {
		ATTACHMENT_TYPES.register(eventBus);
	}

	

	
	
	public static final Supplier<AttachmentType<String>> NATURE_PATICLES = ATTACHMENT_TYPES.register(
	        "nature_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> FOOD_PATICLES = ATTACHMENT_TYPES.register(
	        "food_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> METAL_PATICLES = ATTACHMENT_TYPES.register(
	        "metal_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> GEM_PATICLES = ATTACHMENT_TYPES.register(
	        "gem_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> ENCHANTED_PARTICLES = ATTACHMENT_TYPES.register(
	        "enchanted_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> POTION_PARTICLES = ATTACHMENT_TYPES.register(
	        "potion_particles", () -> AttachmentType.builder(() -> "0").serialize(Codec.STRING).build());
	
	
	
	
	public static final Supplier<AttachmentType<String>> BASE_DISCOVERY_STORAGE = ATTACHMENT_TYPES.register(
	        "base_discovery_item", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> ENCHANTMENT_DISCOVERY_STORAGE = ATTACHMENT_TYPES.register(
	        "enchantment_discovery_item", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build());
	
	public static final Supplier<AttachmentType<String>> POTION_DISCOVERY_STORAGE = ATTACHMENT_TYPES.register(
	        "potion_discovery_item", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build());
	
	
	
	
	
	
	
	public static final Supplier<AttachmentType<Float>> NATURE_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "nature_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	
	public static final Supplier<AttachmentType<Float>> FOOD_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "food_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	public static final Supplier<AttachmentType<Float>> METAL_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "metal_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	public static final Supplier<AttachmentType<Float>> GEM_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "gem_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	public static final Supplier<AttachmentType<Float>> ENCHANTED_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "enchanted_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	public static final Supplier<AttachmentType<Float>> POTION_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "potion_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	public static final Supplier<AttachmentType<Float>> RANDOM_ITEM_DAMAGE_SACRIFICE = ATTACHMENT_TYPES.register(
	        "random_item_damage_sacrifice", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
	
	
	
	public static final Supplier<AttachmentType<String>> SACRIFICE_RANDOM_ITEM = ATTACHMENT_TYPES.register(
	        "sacrifice_random_item", () -> AttachmentType.builder(() -> "").serialize(Codec.STRING).build());
	
}
