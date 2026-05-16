package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import java.awt.Color;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.world.item.Item;

public class ParticleIconFunctions {

	public static Item getParticleIconItem(ParticleType type) {
		
		switch(type)
		{
		
		
		case NATURE: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE;
		case FOOD: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.FOOD;
		case METAL: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.METAL;
		case GEM: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.GEM;
		case ENCHANTED: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.ENCHANTED;
		case POTION: return QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.POTION;
			
		default:
			return null;
		
		}
		
	}
	
	public static final Color amountColor = Color.white;
	public static final Color natureColor = Color.green;
	public static final Color foodColor = Color.orange;
	public static final Color metalColor = Color.white;
	public static final Color gemColor = Color.cyan;
	public static final Color corruptionColor = new Color(207, 159, 255);
	public static final Color manaColor = Color.yellow;
	
	public static Color getParticleTextColor(ParticleType type)
	{
		switch(type)
		{
		case NATURE: return natureColor;
		case FOOD: return foodColor;
		case METAL: return metalColor;
		case GEM: return gemColor;
		case ENCHANTED: return corruptionColor;
		case POTION: return manaColor;
		
		default: return manaColor;
			
		
		}
	}
	
}
