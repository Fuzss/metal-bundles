package fuzs.metalbundles.init;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.api.init.v2.RegistryManager;
import fuzs.puzzleslib.api.init.v2.RegistryReference;
import net.minecraft.world.item.Item;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.instant(MetalBundles.MOD_ID);
    public static final RegistryReference<Item> LEATHER_BUNDLE_ITEM = REGISTRY.registerItem("leather_bundle", () -> new MetalBundleItem(MetalBundleItem.LEATHER_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));
    public static final RegistryReference<Item> COPPER_BUNDLE_ITEM = REGISTRY.registerItem("copper_bundle", () -> new MetalBundleItem(MetalBundleItem.COPPER_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));
    public static final RegistryReference<Item> IRON_BUNDLE_ITEM = REGISTRY.registerItem("iron_bundle", () -> new MetalBundleItem(MetalBundleItem.IRON_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));
    public static final RegistryReference<Item> GOLDEN_BUNDLE_ITEM = REGISTRY.registerItem("golden_bundle", () -> new MetalBundleItem(MetalBundleItem.GOLDEN_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));
    public static final RegistryReference<Item> DIAMOND_BUNDLE_ITEM = REGISTRY.registerItem("diamond_bundle", () -> new MetalBundleItem(MetalBundleItem.DIAMOND_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));
    public static final RegistryReference<Item> NETHERITE_BUNDLE_ITEM = REGISTRY.registerItem("netherite_bundle", () -> new MetalBundleItem(MetalBundleItem.NETHERITE_BUNDLE_CAPACITY, new Item.Properties().stacksTo(1)));

    public static void touch() {

    }
}
