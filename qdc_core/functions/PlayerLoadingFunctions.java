package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerLoadingFunctions {

	public static void onPlayerLoad(PlayerEvent.PlayerLoggedInEvent event) {

		Player player = event.getEntity();
		checkForMainItem(player);
		

		
	}

	
	
	
	
	
	
	private static void checkForMainItem(Player player) {
		
		if(player != null)
		if(!player.getInventory().contains(new ItemStack(QdcApi.QDC_CORE.ITEMS.QDC_MAIN_ITEM)))
		{
			int freeSlot = player.getInventory().getFreeSlot();
			
			if(freeSlot >-1)
			{
				player.getInventory().setItem(freeSlot, new ItemStack(QdcApi.QDC_CORE.ITEMS.QDC_MAIN_ITEM));
			}
		}

	}


}
