package com.qdc_mod.qdc_core_4_5.qdc_core.core.init;

import java.util.function.Supplier;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Qdc.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("qdc_core_creative_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(QdcApi.QDC_CORE.ITEMS.QDC_MAIN_ITEM))
                    .title(Component.translatable("creativetab.qdc_core_4_5.qdc_core_creative_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(QdcApi.QDC_CORE.ITEMS.QDC_MAIN_ITEM);
                        
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.FOOD);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.METAL);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.GEM);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.ENCHANTED);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.POTION);
                        
                        
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.NATURE);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.FOOD);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.METAL);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.GEM);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.ENCHANTED);
                        output.accept(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.POTION);

                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.WOOD);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.STONE);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.IRON);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.GOLD);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.DIAMOND);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.EMERALD);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.NETHERITE);
                        output.accept(QdcApi.QDC_CORE.ITEMS.MACHINE_ITEMS.MACHINE_SCREEN);
                    }).build());

//    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("qdc_machines_creative_tab",
//            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.QUANTUM_KNOWLEDGE.get()))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID, "qdc_core_creative_tab"))
//                    .title(Component.translatable("creativetab.qdc_core_4.qdc_machines_creative_tab"))
//                    .displayItems((itemDisplayParameters, output) -> {
//                        output.accept(ModBlocks.BISMUTH_BLOCK);
//                        output.accept(ModBlocks.BISMUTH_ORE);
//
//                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
	

}
