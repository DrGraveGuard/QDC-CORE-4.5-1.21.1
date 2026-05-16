package com.qdc_mod.qdc_core_4_5.qdc_core.core.init;

import com.qdc_mod.qdc_core_4_5.Qdc;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MenuInit {

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Qdc.MOD_ID);

//	public static final DeferredHolder<MenuType<?>, MenuType<MainItemMenu>> MAIN_ITEM_MENU = registerMenuType(
//			"main_item_menu", MainItemMenu::new);
//
//	
//	public static final DeferredHolder<MenuType<?>, MenuType<DisassemblerMenu>> DISASSEMBLER_MENU = registerMenuType(
//			"disassembler_menu", DisassemblerMenu::new);
//	
      
	
	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
			String name, IContainerFactory<T> factory) {
		return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
	}

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}

}
