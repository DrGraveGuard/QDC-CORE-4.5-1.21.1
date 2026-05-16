package com.qdc_mod.qdc_core_4_5.api;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;

public class ParticleItem {

	public ParticleType type;
	public double amount;
	public String amountString;
	
	public ParticleItem(ParticleType type, double amount)
	{
		this.type = type;
		this.amount = amount;
		this.amountString = GlobalFuncs.fixDouble(amount);
	}
	
}
