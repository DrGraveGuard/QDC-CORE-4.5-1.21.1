package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions;

import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.MainBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box.MainDiscoveryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.LootBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData.MyData;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

public class DisassemblerFunctions {

	public static void disassembleSingleItem(DisassemblerInventoryItem item) {
		disassembleItem(item, 1);
	}

	public static void disassembleStack(DisassemblerInventoryItem item) {
		disassembleItem(item, item.stack.getCount());
	}

	public static void disassembleAllStacks(DisassemblerInventoryItem item, List<DisassemblerInventoryItem> allItems) {

		for (DisassemblerInventoryItem invItem : allItems) {
			if (invItem.isSame(item)) {
				disassembleItem(invItem, invItem.stack.getCount());
			}
		}
	}

	public static void disassembleItem(DisassemblerInventoryItem item, int count) {

		if (item.stack.getItem() == Items.AIR)
			return;

		int actualIndex = item.actualIndex;

		ParticleCollection particles = item.particles;

		if (particles != null) {

			MainDiscoveryBox.handleDisassemblerItemDiscovery(item.stack.copy());
			LootBox.notifyNewDiscovery();
			Qdc.curPlayer.getInventory().removeItem(actualIndex, count);

			int newCount = Qdc.curPlayer.getInventory().getItem(actualIndex).getCount();
			int itemID = Item.getId(Qdc.curPlayer.getInventory().getItem(actualIndex).getItem());

			item.stack.setCount(newCount);

			particles.multiply(count);

			QdcApi.QDC_CORE.FUNCTIONS.addParticles(particles);
			MainBox.saveData(Qdc.curPlayer);

			PacketDistributor.sendToServer(new MyData(actualIndex, itemID, newCount));

		}

	}

	public static int disassembleItemForApi(ItemStack stack, int count) {
		if (stack.getItem() == Items.AIR)
			return -1;

		if (count < 1)
			return -1;

		DisassemblerInventoryItem tempDissItem = new DisassemblerInventoryItem(stack);

		ParticleCollection particles = tempDissItem.particles;

		if (particles != null) {

			MainDiscoveryBox.handleDisassemblerItemDiscovery(stack.copy());
			LootBox.notifyNewDiscovery();

			particles.multiply(count);

			QdcApi.QDC_CORE.FUNCTIONS.addParticles(particles);
			MainBox.saveData(Qdc.curPlayer);

			return count;
			
		}
		
		return -1;
	}

}
