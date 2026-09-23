package fuzs.metalbundles.common.data;

import fuzs.iteminteractions.common.api.v2.data.ItemStorageProvider;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.metalbundles.common.world.item.storage.MetalBundleContentsStorage;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;

public class ModItemStorageProvider implements ItemStorageProvider {

    @Override
    public void run(BootstrapContext<ItemStorage.Definition> output) {
        this.add(output, ModRegistry.COPPER_BUNDLE_ITEM, ModRegistry.DYED_COPPER_BUNDLE_ITEM);
        this.add(output, ModRegistry.IRON_BUNDLE_ITEM, ModRegistry.DYED_IRON_BUNDLE_ITEM);
        this.add(output, ModRegistry.GOLDEN_BUNDLE_ITEM, ModRegistry.DYED_GOLDEN_BUNDLE_ITEM);
        this.add(output, ModRegistry.DIAMOND_BUNDLE_ITEM, ModRegistry.DYED_DIAMOND_BUNDLE_ITEM);
        this.add(output, ModRegistry.NETHERITE_BUNDLE_ITEM, ModRegistry.DYED_NETHERITE_BUNDLE_ITEM);
    }

    public void add(BootstrapContext<ItemStorage.Definition> output, Holder.Reference<Item> bundleItem, ColorCollection<Holder.Reference<Item>> dyedBundleItem) {
        this.add(output, bundleItem);
        dyedBundleItem.forEach(item -> this.add(output, item));
    }

    public void add(BootstrapContext<ItemStorage.Definition> output, Holder.Reference<Item> bundleItem) {
        this.add(output, new MetalBundleContentsStorage(), bundleItem.value());
    }
}
