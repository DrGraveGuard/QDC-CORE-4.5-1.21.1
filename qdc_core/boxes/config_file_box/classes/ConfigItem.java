package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.config_file_box.classes;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ConfigItem {

	public String id;
	public ParticleCollection cfgParticles;

	public ConfigItem(ConfigItemSerializable configData) {
		this.id = configData.id;

		cfgParticles = new ParticleCollection();
		cfgParticles.setData(configData.particles);

	}

}
