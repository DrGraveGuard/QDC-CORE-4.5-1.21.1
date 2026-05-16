package com.qdc_mod.qdc_core_4_5.api;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.neoforged.fml.ModList;

public class GlobalFuncs {

	public static Boolean showConsoleMessages = true;

	
	
	public static double calcRemainingParticlesFromDamagedItem(ItemStack item)
	{
		double max = (double)item.get(DataComponents.MAX_DAMAGE);
		double cur = (double)item.get(DataComponents.DAMAGE);
		
		double diff = max - cur;
		
		double perc = diff/max;
		
		
		return perc;
	}
	
	
	public static String calcDamagePercFromDamagedItem(ItemStack item)
	{
		
		
		double perc = calcRemainingParticlesFromDamagedItem(item);
		
		
		double damagePerc =  100 - (perc*100);
		
		DecimalFormat df = new DecimalFormat("#.#");
		return df.format(damagePerc);
	}
	

	
	
	public static String getModName(String modId) {
		return ModList.get().getModContainerById(modId).map(modContainer -> modContainer.getModInfo().getDisplayName())
				.orElse("Mod Not Found"); // Default if modId is invalid
	}

	public static String getDataItemModID(ItemStack stack) {

		if (isEnchanted(stack)) {
			return getEnchantmentModID(stack);
		}

		if (isPotion(stack) || isTippedArrow(stack)) {
			return getPotionOrArrowModID(stack);
		}

		return "minecraft";
	}

	private static String getEnchantmentModID(ItemStack stack) {

		ItemEnchantments enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();

				ResourceLocation fullId = enchantment.getKey().location();
				String modId = fullId.getNamespace();

				if (modId != "minecraft") {
					return modId;
				}

			}
		}

		enchantments = stack.get(DataComponents.ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				ResourceLocation fullId = enchantment.getKey().location();
				String modId = fullId.getNamespace();

				if (modId != "minecraft") {
					return modId;
				}
			}
		}

		return "minecraft";

	}
	
	public static String getEnchantmentName(ItemStack stack) {

		ItemEnchantments enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				
				int level = entry.getIntValue();
				Component displayName = Enchantment.getFullname(enchantment, level);
				String readableString = displayName.getString(); 
				
				return readableString;
			}
		}

		enchantments = stack.get(DataComponents.ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				
				int level = entry.getIntValue();
				Component displayName = Enchantment.getFullname(enchantment, level);
				String readableString = displayName.getString(); 
				
				return readableString;
			}
		}

		return "minecraft";

	}

	private static String getPotionOrArrowModID(ItemStack potion) {

		PotionContents contents = potion.get(DataComponents.POTION_CONTENTS);

		if (contents != null && contents.potion().isPresent()) {
			// Get the Holder<Potion>
			Holder<Potion> potionHolder = contents.potion().get();
			String modId = potionHolder.unwrapKey().get().location().getNamespace();

			return modId;
		}

		return "minecraft";
	}

	// -----------------

	public static String generateItemDataString(ItemStack stack) {
		String result = generateDefaultDataString(stack);

		if (isEnchanted(stack)) {
			result += generateEnchantmentDataString(stack);
		}

		if (isPotion(stack)) {
			result += generatePotionDataString(stack);
		}

		if (isTippedArrow(stack)) {
			result += generatePotionDataString(stack);
		}

		if (isRocket(stack)) {
			result += generateRocketDataString(stack);
		}

		if (isSusStew(stack)) {
			result += generateSusStewDataString(stack);
		}

		return result;
	}

	private static String generateDefaultDataString(ItemStack stack) {

		String result = getItemModID(stack.getItem()) + "_";
		result += GlobalFuncs.getItemID(stack.getItem());

		return result;

	}

	private static String generateRocketDataString(ItemStack stack) {

		String result = "_";

		if (stack.has(DataComponents.FIREWORKS)) {
			Fireworks fireworks = stack.get(DataComponents.FIREWORKS);
			if (fireworks != null) {
				// flightDuration is a byte: 0 = 1s, 1 = 2s, 2 = 3s
				byte duration = (byte) fireworks.flightDuration();

				result += "_firework_duration_" + duration;
			}
		}

		return result;
	}

	private static String generateEnchantmentDataString(ItemStack stack) {

		String result = "_";

		ItemEnchantments enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				int level = entry.getIntValue();

				result += enchantment.getRegisteredName() + "_" + level + "_";
			}
		}

		enchantments = stack.get(DataComponents.ENCHANTMENTS);
		if (enchantments != null) {
			for (Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				int level = entry.getIntValue();

				result += enchantment.getRegisteredName() + "_" + level + "_";
			}
		}

		return result;
	}

	private static String generatePotionDataString(ItemStack potion) {

		String potionName = "_";
		potionName += potion.getDisplayName().getString() + "_";
		potionName += PotionInitFunctions.getPotionDuration(potion) + "_";
		potionName += PotionInitFunctions.getPotionLevel(potion);

		return potionName;
	}

	public static String generateSusStewDataString(ItemStack stack) {
		SuspiciousStewEffects effects = stack.get(DataComponents.SUSPICIOUS_STEW_EFFECTS);
		String res = "";

		if (effects != null) {
			for (SuspiciousStewEffects.Entry entry : effects.effects()) {
				Holder<MobEffect> effect = entry.effect();

				String registryName = effect.getKey().location().toString();

				int duration = entry.duration();

				res += "_" + registryName + "_" + duration + "_";
			}
		}

		if (res.isEmpty())
			return null;

		return res;
	}
	
	public static String getSusStewEffectName(ItemStack stack) {
		SuspiciousStewEffects effects = stack.get(DataComponents.SUSPICIOUS_STEW_EFFECTS);
		
		if (effects != null) {
			for (SuspiciousStewEffects.Entry entry : effects.effects()) {
				Holder<MobEffect> effect = entry.effect();

				return effect.getKey().location().getPath();

			}
		}

			return null;

	}

	public static boolean isPotion(Item item) {
		return isPotion(new ItemStack(item));
	}

	public static boolean isPotion(ItemStack stack) {
		if (stack.getItem() == Items.POTION || stack.getItem() == Items.SPLASH_POTION
				|| stack.getItem() == Items.LINGERING_POTION) {
			return true;
		}

		return false;
	}

	public static boolean isTippedArrow(ItemStack stack) {
		if (stack.getItem() == Items.TIPPED_ARROW) {
			return true;
		}

		return false;
	}

	public static boolean isRocket(ItemStack stack) {
		if (stack.getItem() == Items.FIREWORK_ROCKET) {
			return true;
		}

		return false;
	}

	public static boolean isEnchanted(ItemStack stack) {
		if (stack.getItem() == Items.AIR)
			return false;

		boolean found = false;

		if (stack.get(DataComponents.STORED_ENCHANTMENTS) != null)

			found = !stack.get(DataComponents.STORED_ENCHANTMENTS).isEmpty();

		if (!found) {

			if (stack.get(DataComponents.ENCHANTMENTS) != null)
				found = !stack.get(DataComponents.ENCHANTMENTS).isEmpty();
		}

		return found;

	}

	public static boolean isSusStew(ItemStack stack) {
		if (stack.getItem() == Items.AIR)
			return false;

		if (stack.getItem() != Items.SUSPICIOUS_STEW)
			return false;

		if (stack.has(DataComponents.SUSPICIOUS_STEW_EFFECTS))
		{
		
			
			return true;
		}
		return false;

	}

	public static boolean isFlower(ItemStack stack) {

		if (stack.getItem() instanceof BlockItem blockItem
				&& blockItem.getBlock() instanceof SuspiciousEffectHolder effectHolder) {
			// This returns the SuspiciousStewEffects component record
			SuspiciousStewEffects effects = effectHolder.getSuspiciousEffects();

			if (effects != null)
				return true;
		}

		return false;
	}

	public static Holder<MobEffect> getFlowerEffect(ItemStack stack) {
		SuspiciousStewEffects effects = null;

		if (stack.getItem() instanceof BlockItem blockItem
				&& blockItem.getBlock() instanceof SuspiciousEffectHolder effectHolder) {
			// This returns the SuspiciousStewEffects component record

			effects = effectHolder.getSuspiciousEffects();
		}

		if (effects != null) {
			for (SuspiciousStewEffects.Entry entry : effects.effects()) {
				Holder<MobEffect> effect = entry.effect();
				int duration = entry.duration();

				return effect;
			}
		}

		return null;
	}

	public static SuspiciousStewEffects getFlowerSusEffect(ItemStack stack) {
		SuspiciousStewEffects effects = null;

		if (stack.getItem() instanceof BlockItem blockItem
				&& blockItem.getBlock() instanceof SuspiciousEffectHolder effectHolder) {
			// This returns the SuspiciousStewEffects component record

			effects = effectHolder.getSuspiciousEffects();
		}
		
		return effects;

	}

	public static String getModIdFromEnchantment(Holder<Enchantment> enchantmentHolder) {
		return enchantmentHolder.unwrapKey() // Get Optional<ResourceKey<Enchantment>>
				.map(key -> key.location().getNamespace()) // Get Namespace (Mod ID)
				.orElse("minecraft"); // Fallback if not registered
	}

	public static String getIdFromEnchantment(Holder<Enchantment> enchantmentHolder) {
		return enchantmentHolder.unwrapKey() // Get Optional<ResourceKey<Enchantment>>
				.map(key -> key.location().getPath()) // Get Namespace (Mod ID)
				.orElse("minecraft"); // Fallback if not registered
	}

	public static String fixString(BigDecimal amount) {

		int comp = amount.compareTo(new BigDecimal("1000000"));

		if (comp > -1) {
			return fixBigDecimal(amount.divide(new BigDecimal("1000000"))) + "M";

		}

		comp = amount.compareTo(new BigDecimal("10000"));

		if (comp > -1) {
			return fixBigDecimal(amount.divide(new BigDecimal("1000"))) + "K";

		}

		return fixBigDecimal(amount);

	}

	@SuppressWarnings("deprecation")
	private static String fixBigDecimal(BigDecimal bd) {
		bd = bd.setScale(2, BigDecimal.ROUND_DOWN);

		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);

		return df.format(bd);
	}

	public static String formatNumber(BigDecimal val, int numOfDecimal) {
		BigDecimal bd = val.setScale(2, BigDecimal.ROUND_DOWN);

		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(numOfDecimal);

		df.setMinimumFractionDigits(0);

		df.setGroupingUsed(true);

		return df.format(bd);
	}

	@SuppressWarnings("deprecation")
	public static String prepareBigDecimalForSave(BigDecimal val) {
		BigDecimal bd = val.setScale(2, BigDecimal.ROUND_DOWN);

		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(2);

		df.setMinimumFractionDigits(0);

		df.setGroupingUsed(false);

		return df.format(bd);
	}

	public static String getItemNameShort(Item item, int len) {
		String name = getItemName(item);

		if (len > name.length() - 1)
			return name;

		return name.substring(0, len) + "..";

	}

	public static String getItemName(Item item) {
		return item.getName(new ItemStack(item)).getString();
	}

	public static String getItemStackName(ItemStack itemStack) {
		return itemStack.getDisplayName().getString();
	}

	public static String getItemModID(Item item) {
		return BuiltInRegistries.ITEM.getKey(item).getNamespace();
	}

	public static String getItemID(Item item) {
		return BuiltInRegistries.ITEM.getKey(item).getPath();
	}

	public static String getDefaultItemID(Item item) {
		return getItemModID(item) + ":" + getItemID(item);
	}

	public static String fixDouble(double d) {
		String str = round(d, 2) + "";

		if (str.endsWith(".0")) {
			return str.replace(".0", "");
		} else {
			return str;
		}
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	
	
	
	
	public static String toCamelCase(String old)
	{
		
		char[] parts = old.strip().replace("_", " ").toCharArray();
		
		String result = "";
		boolean doCapital = true;
		for(char c : parts)
		{
			String s = c+"";
			
			if(doCapital)
			{
				result += s.toUpperCase();
				doCapital = false;
			}
			else
			{
				result += s;
			}
			
			if(s.equals(" "))
			{
				doCapital = true;
			}
		}
		
		return result;
		
		
	}
	
	
	

	public static void showInGameMessage(String msg) {
		Qdc.curPlayer.displayClientMessage(Component.literal(msg), false);
	}

	public static BigDecimal addParticles(BigDecimal cur, double toAdd) {
		return cur.add(new BigDecimal(toAdd));
	}

	public static void msg(String str) {
		if (showConsoleMessages)
			System.out.println(str);
	}

	public static void msg(int num) {
		if (showConsoleMessages)
			System.out.println(num);
	}

	public static void msg(float num) {
		if (showConsoleMessages)
			System.out.println(num);
	}

	public static void msg(BigDecimal bd) {
		if (showConsoleMessages)
			System.out.println(bd);
	}

	public static void line() {
		if (showConsoleMessages)
			System.out.println("----------------------------------");
	}

}
