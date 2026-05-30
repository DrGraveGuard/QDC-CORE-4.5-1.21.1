package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions.DefaultItemParticlesFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.ItemRecipeFuctions;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.Tags;

public class DefaultParticlesBox {

	public static List<DefaultItemParticles> defaultItems = new ArrayList<DefaultItemParticles>();
	public static List<Item> unassignedItems = new ArrayList<Item>();
	public static List<String> assignedItems = new ArrayList<String>();

	private final static ParticleCollection RARE_ITEMS_DEFUALT_PARTICLES = new ParticleCollection()
			.addNatureParticles(25).addFoodParticles(10).addMetalParticles(5).addGemParticles(2.5d);

	public static void clear() {
		defaultItems = new ArrayList<DefaultItemParticles>();
		unassignedItems = new ArrayList<Item>();
		assignedItems = new ArrayList<String>();
	}

	public static void setupDefaultParticles(ServerLevel serverLevel) {

		// Tags.Items.BUCKETS_ENTITY_WATER

		// ItemTags.

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.DIRT)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(2)));

		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.COBBLESTONES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(2)));

		

		defaultItems
				.add(new DefaultItemParticles(Items.SUSPICIOUS_SAND, new ParticleCollection().addNatureParticles(10)));
		defaultItems.add(
				new DefaultItemParticles(Items.SUSPICIOUS_GRAVEL, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.SAND, new ParticleCollection().addNatureParticles(2)));
		defaultItems.add(new DefaultItemParticles(Items.RED_SAND, new ParticleCollection().addNatureParticles(2)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.STRIPPED_LOGS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(6)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.LOGS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.LEAVES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.SAPLINGS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(15)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.FLOWERS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.CRIMSON_STEMS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.WARPED_STEMS)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// BUCKETS
		// -------------------------------------------

		// fish
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.BUCKETS_ENTITY_WATER)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1).addFoodParticles(1.5)));

		// lava
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.BUCKETS_LAVA)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1)));

		// water
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.BUCKETS_WATER)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1)));

		// milk
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.BUCKETS_MILK)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1).addFoodParticles(1.5)));

		// snow
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.BUCKETS_POWDER_SNOW)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1)));

		// ============================================
		// ENCHANTED GOLDEN APPLE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.ENCHANTED_GOLDEN_APPLE, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// RAW FOOD
		// -------------------------------------------

		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.FOODS_RAW_FISH)
				.setDefaultParticles(new ParticleCollection().addFoodParticles(1.5)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.FOODS_RAW_MEAT)
				.setDefaultParticles(new ParticleCollection().addFoodParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.FOODS_VEGETABLE)
				.setDefaultParticles(new ParticleCollection().addFoodParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.FOODS_BERRY)
				.setDefaultParticles(new ParticleCollection().addFoodParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.FOODS_FRUIT)
				.setDefaultParticles(new ParticleCollection().addFoodParticles(1)));

		// ============================================
		// RAW METALS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.RAW_MATERIALS_COPPER)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(0.9d)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.RAW_MATERIALS_IRON)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.RAW_MATERIALS_GOLD)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3)));

		// ============================================
		// ORES
		// -------------------------------------------

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.COAL_ORES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.LAPIS_ORES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(2)));
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.REDSTONE_ORES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10).addMetalParticles(1.2d)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.COPPER_ORES)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(0.9d)));
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.IRON_ORES)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.GOLD_ORES)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3)));

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.DIAMOND_ORES)
				.setDefaultParticles(new ParticleCollection().addGemParticles(1)));
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.EMERALD_ORES)
				.setDefaultParticles(new ParticleCollection().addGemParticles(0.2d)));

		// ============================================
		// MUSIC DISCS
		// -------------------------------------------

		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.MUSIC_DISCS)
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// SMITHING TEMPLATES
		// -------------------------------------------

		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.TRIM_TEMPLATES)
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// SHERDS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.DECORATED_POT_SHERDS)
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// HEADS
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles().addPartialName(" head").setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		defaultItems.add(
				new DefaultItemParticles().addPartialName(" skull").setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// SPAWN EGGS
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles().addPartialName(" spawn egg").setDefaultParticles(new ParticleCollection()
						.addNatureParticles(50).addFoodParticles(5).addEnchantedParticles(1).addPotionParticles(1)));

		// ============================================
		// VINES
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("vine")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(15)));

		// ============================================
		// FUNGUS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("fungus")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10).addFoodParticles(1)));

		// ============================================
		// SHROOMLIGHT
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SHROOMLIGHT, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// MUSHROOM
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("mushroom block")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(50).addFoodParticles(4.55d)));

		defaultItems.add(new DefaultItemParticles().addPartialName("mushroom")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5).addFoodParticles(0.5d)));

		defaultItems.add(new DefaultItemParticles(Items.WARPED_WART_BLOCK,
				new ParticleCollection().addNatureParticles(10).addFoodParticles(0.1d)));

		defaultItems.add(new DefaultItemParticles(Items.NETHER_WART,
				new ParticleCollection().addNatureParticles(5).addFoodParticles(1)));

		// ============================================
		// SPONGE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.WET_SPONGE, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// BUCKETS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("bucket")
				.setDefaultParticles(new ParticleCollection().addMetalParticles(3.1).addFoodParticles(1.5d)));

		// ============================================
		// SMITHING TEMPLATES
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// SUGAR CANE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SUGAR_CANE, new ParticleCollection().addNatureParticles(2.5d)));

		// ============================================
		// CORAL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("roots")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// BUCKETS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("nylium")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// ANVIL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addTagKey(ItemTags.ANVIL)
				.setDefaultParticles(new ParticleCollection().addMetalParticles(10)));

		// ============================================
		// CORAL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName(" coral")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(50)));

		// ============================================
		// CONCRETE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addTagKey(Tags.Items.CONCRETES)
				.setDefaultParticles(new ParticleCollection().addNatureParticles(15)));

	

		defaultItems
				.add(new DefaultItemParticles(Items.SHULKER_SHELL, new ParticleCollection().addNatureParticles(20)));

		// ============================================
		// CHAINMAIL
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.CHAINMAIL_HELMET, new ParticleCollection().addMetalParticles(6)));

		defaultItems.add(
				new DefaultItemParticles(Items.CHAINMAIL_CHESTPLATE, new ParticleCollection().addMetalParticles(9.2d)));

		defaultItems.add(
				new DefaultItemParticles(Items.CHAINMAIL_LEGGINGS, new ParticleCollection().addMetalParticles(8.1d)));

		defaultItems
				.add(new DefaultItemParticles(Items.CHAINMAIL_BOOTS, new ParticleCollection().addMetalParticles(4.5d)));

		// ============================================
		// CHAINMAIL
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.IRON_HORSE_ARMOR, new ParticleCollection().addMetalParticles(10)));

		defaultItems.add(
				new DefaultItemParticles(Items.GOLDEN_HORSE_ARMOR, new ParticleCollection().addMetalParticles(24)));

		defaultItems
				.add(new DefaultItemParticles(Items.DIAMOND_HORSE_ARMOR, new ParticleCollection().addGemParticles(5)));

		// ============================================
		// COPPER EXPOSED
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.EXPOSED_COPPER, new ParticleCollection().addMetalParticles(10)));
		defaultItems.add(
				new DefaultItemParticles(Items.EXPOSED_COPPER_DOOR, new ParticleCollection().addMetalParticles(2.1d)));
		defaultItems.add(new DefaultItemParticles(Items.EXPOSED_COPPER_TRAPDOOR,
				new ParticleCollection().addMetalParticles(3.2d)));

		// ============================================
		// COPPER WEATHERED
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles(Items.WEATHERED_COPPER, new ParticleCollection().addMetalParticles(10.2d)));
		defaultItems.add(new DefaultItemParticles(Items.WEATHERED_COPPER_DOOR,
				new ParticleCollection().addMetalParticles(2.3d)));
		defaultItems.add(new DefaultItemParticles(Items.WEATHERED_COPPER_TRAPDOOR,
				new ParticleCollection().addMetalParticles(3.4d)));

		// ============================================
		// COPPER OXIDIZED
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles(Items.OXIDIZED_COPPER, new ParticleCollection().addMetalParticles(10.4d)));
		defaultItems.add(
				new DefaultItemParticles(Items.OXIDIZED_COPPER_DOOR, new ParticleCollection().addMetalParticles(2.5d)));
		defaultItems.add(new DefaultItemParticles(Items.OXIDIZED_COPPER_TRAPDOOR,
				new ParticleCollection().addMetalParticles(3.6d)));

		// ============================================
		// AMETHYST CRYSTALS
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.AMETHYST_SHARD, new ParticleCollection().addNatureParticles(6)));
		defaultItems
				.add(new DefaultItemParticles(Items.AMETHYST_CLUSTER, new ParticleCollection().addNatureParticles(20)));

		defaultItems.add(new DefaultItemParticles().addPartialName("amethyst bud")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// ANCIENT DEBRIS
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.ANCIENT_DEBRIS, new ParticleCollection().addMetalParticles(10)));

		// ============================================
		// HEAVY CORE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.HEAVY_CORE, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// NAME TAG
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.NAME_TAG, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// TOTEM OF UNDYING
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.TOTEM_OF_UNDYING, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// BELL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BELL, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// SEEDS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("seeds")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// SEEDS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("froglight")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(50)));

		// ============================================
		// OMINOUS BOTTLE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.OMINOUS_BOTTLE, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// TRIAL KEY
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("trial key")
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// BREEZE ROD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BREEZE_ROD, new ParticleCollection().addNatureParticles(30)));

		// ============================================
		// BLAZE ROD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BLAZE_ROD, new ParticleCollection().addNatureParticles(50)));

		// ============================================
		// COBWEB
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.COBWEB, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// SCULK
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles().addPartialName("sculk").setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// BANNER PATTERN
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("banner pattern")
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// END STONE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.END_STONE, new ParticleCollection().addNatureParticles(2)));

		// ============================================
		// TRIDENT
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.TRIDENT, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// HEART OF THE SEA
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.HEART_OF_THE_SEA, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// NETHER STAR
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.NETHER_STAR, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// NETHER STAR
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.ELYTRA, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// DISC FRAGMENT
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.DISC_FRAGMENT_5, new ParticleCollection()
				.addNatureParticles(2.77d).addFoodParticles(1.11d).addMetalParticles(0.55d).addGemParticles(0.27d)));

		// ============================================
		// SADDLE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SADDLE,
				new ParticleCollection().addNatureParticles(31).addMetalParticles(1.1d)));

		// ============================================
		// DRIP LEAF
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles().addPartialName("dripleaf")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// LILY PAD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.LILY_PAD, new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// TUFF
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.TUFF, new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// NETHER QUARTZ ORE
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles(Items.NETHER_QUARTZ_ORE, new ParticleCollection().addNatureParticles(20)));

		// ============================================
		// NETHER QUARTZ ORE
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.GLOWSTONE_DUST, new ParticleCollection().addNatureParticles(15)));

		// ============================================
		// SNOWBALL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SNOWBALL, new ParticleCollection().addNatureParticles(0.5d)));

		// ============================================
		// SNOWBALL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.CACTUS,
				new ParticleCollection().addNatureParticles(10).addFoodParticles(0.2d)));

		// ============================================
		// ICE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.ICE, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// NETHERRACK
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.NETHERRACK, new ParticleCollection().addNatureParticles(2)));

		// ============================================
		// SOUL SAND
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SOUL_SAND, new ParticleCollection().addNatureParticles(2)));

		// ============================================
		// SOUL SOIL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SOUL_SOIL, new ParticleCollection().addNatureParticles(2)));

		// ============================================
		// BASALT
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BASALT, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// GLOW LICHEN
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.GLOW_LICHEN, new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// FLINT
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.FLINT, new ParticleCollection().addNatureParticles(7)));

		// ============================================
		// MAP
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.MAP,
				new ParticleCollection().addNatureParticles(11).addMetalParticles(5.35d)));

		// ============================================
		// BOTTLE O ENCHANTING
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.EXPERIENCE_BOTTLE, new ParticleCollection()
				.addNatureParticles(12).addFoodParticles(2.5d).addMetalParticles(2.5d).addGemParticles(0.25d)));

		// ============================================
		// PRISMARINE
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles(Items.PRISMARINE_CRYSTALS, new ParticleCollection().addNatureParticles(25)));
		defaultItems
				.add(new DefaultItemParticles(Items.PRISMARINE_SHARD, new ParticleCollection().addNatureParticles(25)));

		// ============================================
		// PITCHER POD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.PITCHER_POD, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// PHANTOM MEMBRANE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.PHANTOM_MEMBRANE, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// NAUTILUS SHELL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.NAUTILUS_SHELL, new ParticleCollection().addNatureParticles(30)
				.addFoodParticles(2.5d).addMetalParticles(2.5d).addGemParticles(0.5d)));

		// ============================================
		// HONEYCOMB
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.HONEYCOMB,
				new ParticleCollection().addNatureParticles(20).addFoodParticles(1.5d)));

		// ============================================
		// HONEYCOMB
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.HONEY_BOTTLE,
				new ParticleCollection().addNatureParticles(20).addFoodParticles(1.5d)));

		// ============================================
		// BEE NEST
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BEE_NEST,
				new ParticleCollection().addNatureParticles(50).addFoodParticles(5)));

		// ============================================
		// CRYING OBSIDIAN
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.CRYING_OBSIDIAN,
				new ParticleCollection().addNatureParticles(25).addMetalParticles(1d).addGemParticles(0.2d)));

		// ============================================
		// OBSIDIAN
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.OBSIDIAN,
				new ParticleCollection().addNatureParticles(10).addMetalParticles(0.1d).addGemParticles(0.1d)));

		// ============================================
		// BLACKSTONE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BLACKSTONE, new ParticleCollection().addNatureParticles(2.5d)));

		// ============================================
		// GILDED BLACKSTONE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.GILDED_BLACKSTONE,
				new ParticleCollection().addNatureParticles(10).addMetalParticles(2.5d)));

		// ============================================
		// POINTED DRIPSTONE
		// -------------------------------------------
		defaultItems.add(
				new DefaultItemParticles(Items.POINTED_DRIPSTONE, new ParticleCollection().addNatureParticles(25d)));

		// ============================================
		// ECHO SHARD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.ECHO_SHARD, RARE_ITEMS_DEFUALT_PARTICLES));

		// ============================================
		// FIREWORK STAR
		// -------------------------------------------
		defaultItems
				.add(new DefaultItemParticles(Items.FIREWORK_STAR, new ParticleCollection().addNatureParticles(25)));

		// ============================================
		// CLAY BALL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.CLAY_BALL, new ParticleCollection().addNatureParticles(3)));

		// ============================================
		// GRAVEL
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.GRAVEL, new ParticleCollection().addNatureParticles(5)));

		// ============================================
		// BUNDLE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.BUNDLE, new ParticleCollection().addNatureParticles(12)));

		// ============================================
		// CALCITE
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.CALCITE, new ParticleCollection().addNatureParticles(2.5d)));

		// ============================================
		// PLANTS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.SHORT_GRASS, new ParticleCollection().addNatureParticles(10)));
		defaultItems.add(new DefaultItemParticles(Items.TALL_GRASS, new ParticleCollection().addNatureParticles(15)));
		defaultItems.add(new DefaultItemParticles(Items.FERN, new ParticleCollection().addNatureParticles(10)));
		defaultItems.add(new DefaultItemParticles(Items.LARGE_FERN, new ParticleCollection().addNatureParticles(15)));
		defaultItems.add(new DefaultItemParticles(Items.SEAGRASS, new ParticleCollection().addNatureParticles(10)));
		defaultItems
				.add(new DefaultItemParticles(Items.NETHER_SPROUTS, new ParticleCollection().addNatureParticles(10)));
		defaultItems.add(new DefaultItemParticles(Items.DEAD_BUSH, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.SEA_PICKLE, new ParticleCollection().addNatureParticles(10)));

		// ============================================
		// OTHER FOOD
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.PUMPKIN,
				new ParticleCollection().addNatureParticles(2.5d).addFoodParticles(1)));

		defaultItems.add(new DefaultItemParticles(Items.WHEAT,
				new ParticleCollection().addNatureParticles(2.5d).addFoodParticles(1)));

		defaultItems.add(new DefaultItemParticles(Items.CARVED_PUMPKIN,
				new ParticleCollection().addNatureParticles(3).addFoodParticles(1.05d)));

		defaultItems.add(new DefaultItemParticles(Items.KELP,
				new ParticleCollection().addNatureParticles(2.5d).addFoodParticles(0.5d)));

		defaultItems.add(new DefaultItemParticles(Items.POISONOUS_POTATO,
				new ParticleCollection().addNatureParticles(5d).addFoodParticles(2d)));

		defaultItems.add(new DefaultItemParticles(Items.COCOA_BEANS,
				new ParticleCollection().addNatureParticles(5d).addFoodParticles(0.5d)));

		// ============================================
		// MOB DROPS
		// -------------------------------------------
		defaultItems.add(new DefaultItemParticles(Items.FEATHER, new ParticleCollection().addNatureParticles(2)));

		defaultItems.add(new DefaultItemParticles(Items.RABBIT_HIDE, new ParticleCollection().addNatureParticles(1)));

		defaultItems.add(new DefaultItemParticles(Items.RABBIT_FOOT, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.ROTTEN_FLESH,
				new ParticleCollection().addNatureParticles(10).addFoodParticles(0.5d)));

		defaultItems.add(new DefaultItemParticles(Items.BONE, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.STRING, new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles(Items.GUNPOWDER, new ParticleCollection().addNatureParticles(5)));

		defaultItems.add(new DefaultItemParticles(Items.BAMBOO, new ParticleCollection().addNatureParticles(0.35d)));

		defaultItems.add(new DefaultItemParticles(Items.GOAT_HORN, new ParticleCollection().addNatureParticles(15)));

		defaultItems.add(new DefaultItemParticles(Items.SLIME_BALL, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.SPIDER_EYE, new ParticleCollection().addNatureParticles(10)));

		defaultItems.add(new DefaultItemParticles(Items.GHAST_TEAR, new ParticleCollection().addNatureParticles(50)));

		defaultItems.add(new DefaultItemParticles(Items.ENDER_PEARL, new ParticleCollection().addNatureParticles(50)));

		defaultItems
				.add(new DefaultItemParticles(Items.DRAGON_BREATH, new ParticleCollection().addNatureParticles(30)));

		defaultItems.add(new DefaultItemParticles().addPartialName("ink sac")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(15)));

		defaultItems.add(new DefaultItemParticles().addPartialName("dragon egg")
				.setDefaultParticles(RARE_ITEMS_DEFUALT_PARTICLES));

		defaultItems.add(new DefaultItemParticles().addPartialName("egg")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10).addFoodParticles(1)));

		defaultItems.add(new DefaultItemParticles().addPartialName("scute")
				.setDefaultParticles(new ParticleCollection().addNatureParticles(10)));


		populateItems();

		setDefaultParticlesToUnassigned();
	}

	private static boolean itemExistsInList(List<Item> list, Item item) {
		for (Item i : list) {
			if (i == item)
				return true;
		}

		return false;
	}

	public static void populateItems() {

		for (Item i : ItemRecipeFuctions.ingredients) {
			if (checkIfByItem(i)) {

				// GlobalFuncs.msg(" item :" + GlobalFuncs.getItemID(i));

			} else if (checkIfByTag(i)) {

				// GlobalFuncs.msg(" tag :" + GlobalFuncs.getItemID(i));

			} else if (checkIfByPartialName(i)) {

				// GlobalFuncs.msg(" partial :" + GlobalFuncs.getItemID(i));

			} else {
				// GlobalFuncs.msg(" unassigned :" + GlobalFuncs.getItemID(i));
				unassignedItems.add(i);

			}
		}

	}

	public static ParticleCollection getItemDefaultParticles(Item item) {
		for (DefaultItemParticles def : defaultItems) {
			if (def.isInItemList(item))
				return def.defaultParticles;
		}

		return null;
	}

	public static boolean checkIfByTag(Item item) {

		for (DefaultItemParticles def : defaultItems) {

			if (def.tag != null)
				if (!isInAssignedList(item))
					if (def.addItem(item)) {
						addToAssignedList(item);
						return true;
					}

		}

		return false;
	}

	public static boolean checkIfByItem(Item item) {
		for (DefaultItemParticles def : defaultItems) {
			if (def.tag == null && def.partialName == null) {
				if (def.isInItemList(item)) {
					addToAssignedList(item);
					return true;
				}
			}
		}

		return false;
	}

	public static boolean checkIfByPartialName(Item item) {
		for (DefaultItemParticles def : defaultItems) {
			if (def.tag == null && def.partialName != null) {

				if (!isInAssignedList(item))
					if (def.addItem(item)) {
						addToAssignedList(item);
						return true;
					}

			}
		}

		return false;
	}


	private static void setDefaultParticlesToUnassigned() {

		ParticleCollection defaultParticles;

		int index = 1;
		for (Item i : unassignedItems) {

			defaultItems.add(new DefaultItemParticles(i,
					DefaultItemParticlesFunctions.getDefaultParticlesForItem(new ItemStack(i))).setToAutoGenerated());
			index++;
		}
	}

	
	private static void addToAssignedList(Item item)
	{
		assignedItems.add(GlobalFuncs.generateItemDataString(new ItemStack(item)));
	}
	
	private static boolean isInAssignedList(Item item) {
		for (String s : assignedItems) {

			String tempID = GlobalFuncs.generateItemDataString(new ItemStack(item));
			
			if (tempID.toLowerCase().equals(s.toLowerCase()))

				return true;
		}

		return false;
	}
}
