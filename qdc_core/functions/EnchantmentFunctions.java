package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.classes.EnchantmentItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.classes.EnchantmentItemCollection;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EnchantmentFunctions {

	public static EnchantmentItemCollection getEnchanmentListFromItem(ItemStack stack) {
		EnchantmentItemCollection col = new EnchantmentItemCollection();

		ItemEnchantments enchantments = null;

		enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);

		if (enchantments != null) {
			for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantmentHolder = entry.getKey();
				int level = entry.getIntValue();
				String enchantId = enchantmentHolder.unwrapKey().get().location().toString();

				col.addEnchantmentItem(new EnchantmentItem(enchantId, level));
			}

		}

		enchantments = stack.get(DataComponents.ENCHANTMENTS);

		if (enchantments != null) {
			for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				Holder<Enchantment> enchantmentHolder = entry.getKey();
				int level = entry.getIntValue();
				String enchantId = enchantmentHolder.unwrapKey().get().location().toString();

				col.addEnchantmentItem(new EnchantmentItem(enchantId, level));
			}

		}

		if (col.encList == null)
			return null;

		return col;
	}

	public static ItemStack createEnchantedBook(Holder<Enchantment> enchantment, int level) {
		// 1. Create the item stack for an enchanted book
		ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

		// Build the enchantment list
		ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
		builder.set(enchantment, level);

		// Apply the component to the book
		// enchantedBook.set(DataComponents.STORED_ENCHANTMENTS, builder.toImmutable());
		enchantedBook.set(DataComponents.ENCHANTMENTS, builder.toImmutable());

		return enchantedBook;
	}

	public static ItemStack createRealEnchantedBook(ItemStack book) {
		// 1. Create the item stack for an enchanted book

		ItemEnchantments enchantments = book.get(DataComponents.ENCHANTMENTS);

		Holder<Enchantment> enchantment = null;
		int level = -1;

		if (enchantments != null) {
			for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
				enchantment = entry.getKey();
				level = entry.getIntValue();
			}

		}

		if (enchantment != null && level > 0) {

			ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

			// Build the enchantment list
			ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
			builder.set(enchantment, level);

			// Apply the component to the book
			enchantedBook.set(DataComponents.STORED_ENCHANTMENTS, builder.toImmutable());
			// enchantedBook.set(DataComponents.ENCHANTMENTS, builder.toImmutable());

			return enchantedBook;
		}
		
		return null;
	}

	public static double calcEnchantmentParticles(int enchantmentLevel) {
		return (double) enchantmentLevel * Qdc.ParticleConstants.ENCHANMENT_LEVEL_PARTICLES;
	}
}
