package com.qdc_mod.qdc_core_4_5.qdc_core.network;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData.MyData;
import com.qdc_mod.qdc_core_4_5.qdc_core.network.packets.myData2.MyData2;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {

	public static void handleTeleportDataOnMain(final MyData2 data, final IPayloadContext ctx) {
		// Do something with the data, on the main thread

		Player player = ctx.player();

		player.teleportTo(data.x_pos(), data.y_pos(), data.z_pos());

	}

	
	public static void handleInventoryDataOnMain(final MyData data, final IPayloadContext ctx) {
		// Do something with the data, on the main thread
		
		Player player = ctx.player();
		
		ItemStack newStack = toStack(data.itemID(), data.count());
		
		
		if(newStack.getItem() == Items.ENCHANTED_BOOK)
		{
			
			player.getInventory().setItem(data.slotIndex(), Qdc.AssemblerVariables.stackToAssemble.copy());
		}
		else if(GlobalFuncs.isPotion(newStack))
		{
			
			player.getInventory().setItem(data.slotIndex(), Qdc.AssemblerVariables.stackToAssemble.copy());
		}
		else if(GlobalFuncs.isTippedArrow(newStack))
		{
			
			player.getInventory().setItem(data.slotIndex(), Qdc.AssemblerVariables.stackToAssemble.copy());
		}
		else if(GlobalFuncs.isRocket(newStack))
		{
			
			player.getInventory().setItem(data.slotIndex(), Qdc.AssemblerVariables.stackToAssemble.copy());
		}else if(GlobalFuncs.isSusStew(newStack))
		{
			
			player.getInventory().setItem(data.slotIndex(), Qdc.AssemblerVariables.stackToAssemble.copy());
		}
		else
		{
			player.getInventory().setItem(data.slotIndex(), newStack);
		}
		
		
		
		
	}

	private static ItemStack toStack(int itemID, int count) {
		ItemStack stack = new ItemStack(Item.byId(itemID), count);

		return stack;
	}

}
