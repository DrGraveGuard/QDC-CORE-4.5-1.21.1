package com.qdc_mod.qdc_core_4_5.qdc_core.common.items;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.AssemblerScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class QdcMainItem extends Item {

	public QdcMainItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		// TODO Auto-generated method stub

		if (worldIn.isClientSide) {
			
			if(Qdc.isFinishedSettingParticles && Qdc.isFinishedLoadingData)
			{
			Minecraft.getInstance().setScreen(new AssemblerScreen());
			}
			else
			{
				GlobalFuncs.showInGameMessage("Particles are still being set up!");
			}

		}

		return super.use(worldIn, player, hand);
	}

}
