package net.boborice.time_machine.datagen;

import net.boborice.time_machine.block.ModBlocks;
import net.boborice.time_machine.item.ModItems;
import net.boborice.time_machine.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    protected static final LootItemCondition.Builder HAS_PICKAXE =
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.PICKAXES));

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ANCIENITE_TILE.get());

        this.add(ModBlocks.ANCIENITE.get(),
                block -> createStoneToStoneShardDrops(ModBlocks.ANCIENITE, ModItems.ANCIENITE_SHARD,
                        3f, 5f));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createPickaxeDispatchTable(DeferredBlock<Block> pBlock, LootPoolEntryContainer.Builder<?> pBuilder) {
        return createSelfDropDispatchTable(pBlock.get(), HAS_PICKAXE, pBuilder);
    }

    protected LootTable.Builder createStoneToStoneShardDrops(DeferredBlock<Block> pBlock, DeferredItem<Item> pItem,
                                                             float minDrop, float maxDrop) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createPickaxeDispatchTable(pBlock, this.applyExplosionDecay(pBlock.get(), LootItem.lootTableItem(pItem)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrop, maxDrop)))
                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}
