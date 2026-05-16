package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;

public class FlowerEffectItem {

	public String effectID = null;
	public Holder<MobEffect> effect = null;
	public List<ItemStack> flowerList = new ArrayList<>();
	public String effectName = null;
	
	public FlowerEffectItem(Holder<MobEffect> effect, ItemStack flower)
	{
		this.effect = effect;
		this.flowerList.add(flower);
		generateID();
	}
	
	
	public boolean tryMerge(FlowerEffectItem otherFlowerItem)
	{
		if(this.effectID.toLowerCase().equals(otherFlowerItem.effectID.toLowerCase()))
		{
			flowerList.add(otherFlowerItem.flowerList.get(0));
			return true;
		}
		
		return false;
	}
	
	public ItemStack[] toArray()
	{
		return flowerList.toArray(new ItemStack[0]);
	}
	
	
	private void generateID()
	{
		effectID = effect.getKey().location().toString();
		effectName = effect.getKey().location().getPath();
	}
	
	
	
	
}
