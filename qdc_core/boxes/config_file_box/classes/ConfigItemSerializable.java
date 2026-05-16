package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes;

import java.io.Serializable;

public class ConfigItemSerializable implements Serializable{

	private static final long serialVersionUID = 1L;
	public String id;
	
	public double[] particles;
	
	public ConfigItemSerializable(String itemID,  double[] particles)
	{
		this.id = itemID;
		this.particles = particles;
	}
	
	public ConfigItemSerializable(ConfigItemSerializable data)
	{
		this.id = data.id;
		this.particles = data.particles;
		
//		ItemTags.
	}
	
}
