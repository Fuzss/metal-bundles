package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.metalbundles.world.item.MetalBundleProvider;
import fuzs.puzzlesapi.api.iteminteractions.v1.data.AbstractItemContainerProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;

public class ModItemContainerProvider extends AbstractItemContainerProvider {

    public ModItemContainerProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void registerBuiltInProviders() {
        this.add(ModRegistry.LEATHER_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.LEATHER_BUNDLE_CAPACITY, DyeColor.BROWN));
        this.add(ModRegistry.COPPER_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.COPPER_BUNDLE_CAPACITY, DyeColor.ORANGE));
        this.add(ModRegistry.IRON_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.IRON_BUNDLE_CAPACITY, DyeColor.LIGHT_GRAY));
        this.add(ModRegistry.GOLDEN_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.GOLDEN_BUNDLE_CAPACITY, DyeColor.YELLOW));
        this.add(ModRegistry.DIAMOND_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.DIAMOND_BUNDLE_CAPACITY, DyeColor.CYAN));
        this.add(ModRegistry.NETHERITE_BUNDLE_ITEM.get(), new MetalBundleProvider(MetalBundleItem.NETHERITE_BUNDLE_CAPACITY, DyeColor.GRAY));
    }
}
