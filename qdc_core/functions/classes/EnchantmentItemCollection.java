package com.qdc_mod.qdc_core_4_5.qdc_core.functions.classes;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;

public class EnchantmentItemCollection {

	public List<EnchantmentItem> encList = null;
	public ParticleCollection particles = null;
	
	
	public void addEnchantmentItem(EnchantmentItem item)
	{
		if(encList == null)
		{
			encList = new ArrayList<EnchantmentItem>();
			particles = new ParticleCollection();
		}
		
		encList.add(item);
		particles.addEnchantedParticles((double)item.level * Qdc.ParticleConstants.ENCHANMENT_LEVEL_PARTICLES);
	}

}
