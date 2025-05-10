package net.boborice.time_machine.datagen;

import net.boborice.time_machine.TimeMachine;
import net.boborice.time_machine.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TimeMachine.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.PICKAXES)
                .add(Items.WOODEN_PICKAXE)
                .add(Items.STONE_PICKAXE)
                .add(Items.IRON_PICKAXE)
                .add(Items.GOLDEN_PICKAXE)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.NETHERITE_PICKAXE);
    }
}
