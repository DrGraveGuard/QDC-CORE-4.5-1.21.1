package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.world.item.Item;

public class ParticleStorageItem {

	public ParticleType type;
	public BigDecimal particles = null;
	public String strVal = "0";
	public Item icon;
	public Color textColor;
	
	public ParticleStorageItem(ParticleType type)
	{
		this.type = type;
		particles = new BigDecimal("0");
		icon = QdcApi.QDC_CORE.FUNCTIONS.getParticleIconItem(type);
		textColor = QdcApi.QDC_CORE.FUNCTIONS.getParticleTextColor(type);
	}
	
	
	public int countCanMake(double amount)
	{
		if(amount == 0)
			return -1;
		
		return particles.divide(new BigDecimal(amount),2, RoundingMode.FLOOR).intValue();
	}
	
	public boolean removeParticles(double amount)
	{
		if(!hasEnoughParticles(amount))
			return false;
		
		particles = particles.subtract(new BigDecimal(amount));

		strVal = GlobalFuncs.fixString(particles);
		
		
		return true;
	}
		
	
	public void addParticles(double amount)
	{
		
		particles = particles.add(new BigDecimal(amount));
		strVal = GlobalFuncs.fixString(particles);
	}
	
	public void setParticles(String amountStr)
	{
		particles = new BigDecimal(amountStr);
		strVal = GlobalFuncs.fixString(particles);
	}
	
	public boolean hasEnoughParticles(double amount)
	{
		int comp = particles.compareTo(new BigDecimal(amount));
		
		if(comp>-1)
			return true;
		
		return false;
	}
	
}
