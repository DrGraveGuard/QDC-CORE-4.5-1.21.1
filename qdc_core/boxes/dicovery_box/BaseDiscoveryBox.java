package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.dicovery_box;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.AttachmentInit;

import net.minecraft.world.entity.player.Player;

public class BaseDiscoveryBox {

	public static final String RECORD_SEPARATOR = "=";

	public static final String VALUE_SEPARATOR = "+";

	public static List<String> baseItemList = new ArrayList<String>();

	
	public static void clear()
	{
		baseItemList = new ArrayList<String>();
	}
	
	
	public static void addNewDiscoveredItem(String itemID) {
		
		if (!isBaseItemDiscovered(itemID)) {

			baseItemList.add(itemID);

		}

	}



	public static boolean isBaseItemDiscovered(String itemID) {
		
		for (String item : baseItemList) {
			
			if(item.equals(itemID))
				return true;
		}

		return false;
	}
	
	
	
	
	public static void loadData(Player player)
	{
		loadBaseItemData(player);
	}
	
	public static void saveData(Player player)
	{
		saveBaseItemData(player);
	}
	
	private static void loadBaseItemData(Player player)
	{
		if (player.hasData(AttachmentInit.BASE_DISCOVERY_STORAGE)) {
			
			baseItemList = new ArrayList<String>();
			
			String baseDiscoveryString = player.getData(AttachmentInit.BASE_DISCOVERY_STORAGE);
			
			
			String[] parts = baseDiscoveryString.split(RECORD_SEPARATOR);
			
			for(String s : parts)
			{
				if(!s.isBlank())
				{
					baseItemList.add(s);
				}
			}
			
			
		}

	}
	
	private static void saveBaseItemData(Player player)
	{
		
		String saveString = generateSaveString();
		
		
			player.setData(AttachmentInit.BASE_DISCOVERY_STORAGE, saveString);
		
	}
	
	private static String generateSaveString()
	{
		String res = "";
		
		for(String item : baseItemList)
		{
			res+= item +RECORD_SEPARATOR;
		}
		
		return res;
	}

}
