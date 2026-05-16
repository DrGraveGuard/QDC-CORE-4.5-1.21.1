package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.ConfigFileBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes.ConfigItemSerializable;

import net.neoforged.fml.loading.FMLPaths;

public class FileFunctions {

	private static String filePath = FMLPaths.CONFIGDIR.get().toAbsolutePath().toString() + "/qdc_core_4_5-1.qdc";
	private static File configFile = new File(filePath);

	public static void handleConfigFile() {
		if (!configFileExists()) {

			ConfigFileBox.generateDefaultConfigData();

			saveFile();

			GlobalFuncs.showInGameMessage("Config file not found, creating default config file!");
		} else {

			String msg = readFile();

			GlobalFuncs.showInGameMessage(msg);
			
			int newItemCount = ConfigFileBox.handleNewItems();
			
			if(newItemCount > 0)
			{
				GlobalFuncs.showInGameMessage("New items added to config file: " + newItemCount);
				saveFile();
			}
		}
	}

	private static String readFile() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
			// Cast the returned Object to the specific List type
			List<ConfigItemSerializable> list = (ArrayList<ConfigItemSerializable>) in.readObject();

			ConfigFileBox.fileDataItemList = new ArrayList<ConfigItemSerializable>(list);

			ConfigFileBox.unpackData();
			
			return "Config Data loaded " + list.size() + " items!";

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		ConfigFileBox.generateDefaultConfigData();

		saveFile();
		return "corrupt file, resetting to default!";
	}

	public static void saveFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
			oos.writeObject(ConfigFileBox.fileDataItemList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean configFileExists() {
		return configFile.exists();
	}

}
