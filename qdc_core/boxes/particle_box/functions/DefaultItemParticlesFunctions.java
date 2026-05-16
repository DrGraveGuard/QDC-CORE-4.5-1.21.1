package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class DefaultItemParticlesFunctions {

	
	private static  ParticleCollection COMMON_PARTICLES = null;
	private static  ParticleCollection UNCOMMON_PARTICLES = null;
	private static  ParticleCollection RARE_PARTICLES = null;
	private static  ParticleCollection EPIC_PARTICLES = null;
	
	
	
	public static ParticleCollection getDefaultParticlesForItem(ItemStack is)
	{
		if(COMMON_PARTICLES== null)
		{
			setupDefaulRarityParticles();
		}
		
		
		
		Rarity itemRarity = getStackRarity(is);
		
		
		
		switch(itemRarity)
		{
		case COMMON: return COMMON_PARTICLES.clone();
		case UNCOMMON: return UNCOMMON_PARTICLES.clone();
		case RARE: return RARE_PARTICLES.clone();
		case EPIC: return EPIC_PARTICLES.clone();
		
		default: return COMMON_PARTICLES.clone();
		
		}
	}
	
	
	private static void setupDefaulRarityParticles()
	{
		COMMON_PARTICLES = new ParticleCollection().addNatureParticles(10);
		UNCOMMON_PARTICLES = new ParticleCollection().addNatureParticles(25);
		RARE_PARTICLES = new ParticleCollection().addNatureParticles(50).addFoodParticles(5).addMetalParticles(5).addGemParticles(1);
		EPIC_PARTICLES = new ParticleCollection().addNatureParticles(50).addFoodParticles(10).addMetalParticles(10).addGemParticles(2.5d);
	}
	
	
	private static Rarity getStackRarity(ItemStack stack) {
	    // Returns the rarity, or null if not specifically set (defaulting to COMMON)
	    return stack.get(DataComponents.RARITY);
	}
}
