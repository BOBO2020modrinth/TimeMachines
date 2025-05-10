package net.boborice.time_machine.block;

import net.boborice.time_machine.TimeMachine;
import net.boborice.time_machine.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TimeMachine.MOD_ID);

    // Add Blocks Here
        // Natural Block
    public static final DeferredBlock<Block> ANCIENITE = registerBlock("ancienite",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.8f)));
    public static final DeferredBlock<Block> ANCIENITE_TILE = registerBlock("ancienite_tile",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
