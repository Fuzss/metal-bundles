package fuzs.metalbundles.data;

import fuzs.iteminteractions.common.api.v2.data.AbstractItemStorageDefinitionsProvider;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.storage.MetalBundleContentsStorage;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModItemStorageDefinitionsProvider extends AbstractItemStorageDefinitionsProvider {

    public ModItemStorageDefinitionsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemStorageDefinitions(HolderLookup.Provider registries) {
        this.add(ModRegistry.COPPER_BUNDLE_ITEM, ModRegistry.COPPER_BUNDLE_ITEMS);
        this.add(ModRegistry.IRON_BUNDLE_ITEM, ModRegistry.IRON_BUNDLE_ITEMS);
        this.add(ModRegistry.GOLDEN_BUNDLE_ITEM, ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.add(ModRegistry.DIAMOND_BUNDLE_ITEM, ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.add(ModRegistry.NETHERITE_BUNDLE_ITEM, ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    public void add(Holder.Reference<Item> bundleItem, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        this.add(bundleItem);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.add(entry.getValue());
        }
    }

    public void add(Holder.Reference<Item> itemLookup) {
        this.add(new MetalBundleContentsStorage(StorageOptions.DEFAULT.setFilterContainerItems()), itemLookup.value());
    }
}
