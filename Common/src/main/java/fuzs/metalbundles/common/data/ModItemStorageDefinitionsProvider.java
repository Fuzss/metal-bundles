package fuzs.metalbundles.common.data;

import fuzs.iteminteractions.common.api.v2.data.AbstractItemStorageDefinitionsProvider;
import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.metalbundles.common.world.item.storage.MetalBundleContentsStorage;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;

public class ModItemStorageDefinitionsProvider extends AbstractItemStorageDefinitionsProvider {

    public ModItemStorageDefinitionsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemStorageDefinitions(HolderLookup.Provider registries) {
        this.add(ModRegistry.COPPER_BUNDLE_ITEM, ModRegistry.DYED_COPPER_BUNDLE_ITEM);
        this.add(ModRegistry.IRON_BUNDLE_ITEM, ModRegistry.DYED_IRON_BUNDLE_ITEM);
        this.add(ModRegistry.GOLDEN_BUNDLE_ITEM, ModRegistry.DYED_GOLDEN_BUNDLE_ITEM);
        this.add(ModRegistry.DIAMOND_BUNDLE_ITEM, ModRegistry.DYED_DIAMOND_BUNDLE_ITEM);
        this.add(ModRegistry.NETHERITE_BUNDLE_ITEM, ModRegistry.DYED_NETHERITE_BUNDLE_ITEM);
    }

    public void add(Holder.Reference<Item> bundleItem, ColorCollection<Holder.Reference<Item>> dyedBundleItem) {
        this.add(bundleItem);
        dyedBundleItem.forEach(this::add);
    }

    public void add(Holder.Reference<Item> bundleItem) {
        this.add(new MetalBundleContentsStorage(), bundleItem.value());
    }
}
