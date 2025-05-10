package net.boborice.time_machine.item;

import net.boborice.time_machine.TimeMachine;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TimeMachine.MOD_ID);

    // Add Items Here
        // Ingredients
    public static final DeferredItem<Item> ANCIENITE_SHARD = ITEMS.registerSimpleItem("ancienite_shard");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
