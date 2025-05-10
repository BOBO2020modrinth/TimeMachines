package net.boborice.time_machine.item;

import net.boborice.time_machine.TimeMachine;
import net.boborice.time_machine.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TimeMachine.MOD_ID);

    public static final Supplier<CreativeModeTab> ANCIENT_BLOCKS =
            CREATIVE_MODE_TABS.register("ancient_blocks", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.time_machine.ancient_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.ANCIENITE.asItem()))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ANCIENITE);
                        pOutput.accept(ModBlocks.ANCIENITE_TILE);
                    })).build());

    public static final Supplier<CreativeModeTab> ANCIENT_ITEMS =
            CREATIVE_MODE_TABS.register("ancient_items", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.time_machine.ancient_items"))
                    .icon(() -> new ItemStack(ModItems.ANCIENITE_SHARD.get()))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ANCIENITE_SHARD);
                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
