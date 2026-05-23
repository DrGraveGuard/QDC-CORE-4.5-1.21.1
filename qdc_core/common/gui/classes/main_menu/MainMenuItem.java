package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.main_menu;

import java.lang.reflect.Constructor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModList;

public class MainMenuItem {

	public String displayText = null;
	public String classPath = null;
	public String modID = null;
	
	public MainMenuItem(String displayText, String classPath, String modID) {
		
		this.displayText = displayText;
		this.classPath = classPath;
		this.modID = modID;
	}
	
	
	
	public boolean isValid()
	{
		return modExists() && isValidClassPath();
	}
	
	
	public boolean modExists()
	{
		return ModList.get().isLoaded(modID);
	}
	
	public boolean isValidClassPath()
	{
		  try {
		       
		        Class<?> screenClass = Class.forName(classPath);
		        
		        return true;
			} catch (Exception e) {
			
				return false;
		}
	}
	
	public void openScreen()
	{
		try {
	        // Use the full internal path of the other mod's screen class
	        Class<?> screenClass = Class.forName(classPath);
	        
	        // Example: Constructor that takes a Component (title)
	        Constructor<?> constructor = screenClass.getConstructor();
	        Object screenInstance = constructor.newInstance();

	        if (screenInstance instanceof Screen screen) {
	        	Minecraft.getInstance().execute(() -> {
	        	    Minecraft.getInstance().setScreen(screen);
	        	});
	        }
	    } catch (Exception e) {

	    }
	}
	
	
}
