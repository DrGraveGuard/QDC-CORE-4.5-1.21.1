package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes.ConfigItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes.ConfigItemSerializable;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.functions.FileFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.DefaultParticlesBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.DefaultItemParticles;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.ItemRecipeFuctions;

import net.minecraft.world.item.Item;

public class ConfigFileBox {

	public static List<ConfigItem> loadedItemList = new ArrayList<ConfigItem>();
	public static List<ConfigItemSerializable> fileDataItemList = new ArrayList<ConfigItemSerializable>();

	public static void clear()
	{
		loadedItemList = new ArrayList<ConfigItem>();
		fileDataItemList = new ArrayList<ConfigItemSerializable>();
	}
	
	public static void generateDefaultConfigData() {
		for (DefaultItemParticles item : DefaultParticlesBox.defaultItems) {
			ConfigItemSerializable data = new ConfigItemSerializable(item.id, item.defaultParticles.particles);

			loadedItemList.add(new ConfigItem(data));
			fileDataItemList.add(data);
		}
		
		//FileFunctions.saveFile();

	}

	
	public static void updateCfgParticles(DefaultItemParticles itemData)
	{
		ConfigItem toEdit = getConfigItem(itemData.id);
		ConfigItemSerializable toEditSerial = getConfigItemSerializable(itemData.id);
		
		toEdit.cfgParticles = itemData.configParticles.clone();
		toEditSerial.particles = toEdit.cfgParticles.particles;
		
		FileFunctions.saveFile();     
		
	}
	
	public static void unpackData() {
		loadedItemList = new ArrayList<ConfigItem>();

		for (ConfigItemSerializable cfg : fileDataItemList) {
			loadedItemList.add(new ConfigItem(cfg));
		}
	}
	
	public static void assignConfigParticles()
	{
		for (ConfigItem cfg : loadedItemList) {
			for (DefaultItemParticles item : DefaultParticlesBox.defaultItems) {
				
				if(item.isSameID(cfg.id))
				{
				
					item.setConfigParticles(cfg.cfgParticles.clone());
					break;
				}
				
			}
		}
	}
	

	

	public static int handleNewItems() {
		int count = 0;

		for (Item item : ItemRecipeFuctions.ingredients) {

			if (DefaultParticlesBox.checkIfByTag(item)) {

			} else if (DefaultParticlesBox.checkIfByPartialName(item)) {

			} else 
			{

				String defaultID = GlobalFuncs.getDefaultItemID(item);

				if (!existsInConfigFile(defaultID)) {
					count++;

					ParticleCollection particles = DefaultParticlesBox.getItemDefaultParticles(item);

					ConfigItemSerializable data = new ConfigItemSerializable(defaultID, particles.particles);

					loadedItemList.add(new ConfigItem(data));
					fileDataItemList.add(data);

				}
			}
		}

		return count;
	}

	private static boolean existsInConfigFile(String id) {
		for (ConfigItem cfg : loadedItemList) {
			if (cfg.id.equals(id))
				return true;
		}

		return false;
	}

	public static ConfigItem getConfigItem(Item item) {
		return getConfigItem(GlobalFuncs.getDefaultItemID(item));
	}
	
	public static ConfigItem getConfigItem(String id) {
		for (ConfigItem cfg : loadedItemList) {
			if (cfg.id.equals(id))
				return cfg;
		}

		return null;
	}
	
	public static ConfigItemSerializable getConfigItemSerializable(String id) {
		for (ConfigItemSerializable cfg : fileDataItemList) {
			if (cfg.id.equals(id))
				return cfg;
		}

		return null;
	}

}
