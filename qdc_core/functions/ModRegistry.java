package com.qdc_mod.qdc_core_4_5.qdc_core.functions;

import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.AttachmentInit;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.CreativeTabInit;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.ItemInit;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.MenuInit;

import net.neoforged.bus.api.IEventBus;

public class ModRegistry {

	public static void registerItems(IEventBus eventBus) {
		ItemInit.register(eventBus);
	}
	

	public static void registerBlocks(IEventBus eventBus) {
//		BlockInit.register(eventBus);
	}

	public static void registerCreativeTabs(IEventBus eventBus) {
		CreativeTabInit.register(eventBus);
	}

	public static void registerMenus(IEventBus eventBus) {
		MenuInit.register(eventBus);
	}
	
	public static void registerAttachments(IEventBus eventBus) {
		AttachmentInit.register(eventBus);
	}
	
	public static void registerBlockEntities(IEventBus eventBus) {
//		BlockEntityInit.register(eventBus);
	}
}
