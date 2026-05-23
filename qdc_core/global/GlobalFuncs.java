package com.qdc_mod.qdc_core_4_5.qdc_core.global;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.fml.ModList;

public class GlobalFuncs {

	public static Boolean showConsoleMessages = true;

	public static String getModName(String modId) {
		return ModList.get().getModContainerById(modId).map(modContainer -> modContainer.getModInfo().getDisplayName())
				.orElse("Mod Not Found"); // Default if modId is invalid
	}

	
	public static String generateItemDataString(ItemStack stack)
	{
		String result = generateDefaultDataString(stack);
		
		
		if(isEnchanted(stack))
		{
			result += generateEnchantmentDataString(stack);
		}
		
		if(isPotion(stack))
		{
			result += generatePotionDataString(stack);
		}
		
		if(isTippedArrow(stack))
		{
			result += generatePotionDataString(stack);
		}

		
		
		return result;
	}
	
	
	
	
	
	private static String generateDefaultDataString(ItemStack stack) {

		String result = getItemModID(stack.getItem()) + "_";
		result += GlobalFuncs.getItemID(stack.getItem());
		
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
		if (stack.getItem() == Items.TIPPED_ARROW ) {
			return true;
		}

		return false;
	}

	public static boolean isEnchanted(ItemStack stack) {
		if (stack.getItem() == Items.AIR)
			return false;


		boolean  found = false;
		
			if (stack.get(DataComponents.STORED_ENCHANTMENTS) != null)			

			found =  !stack.get(DataComponents.STORED_ENCHANTMENTS).isEmpty();
		

			if(!found)
			{
				
			if (stack.get(DataComponents.ENCHANTMENTS) != null)
			found = !stack.get(DataComponents.ENCHANTMENTS).isEmpty();
		}

			return found;
			
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
