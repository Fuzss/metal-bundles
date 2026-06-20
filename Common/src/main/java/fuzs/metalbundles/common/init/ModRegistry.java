package fuzs.metalbundles.common.init;

import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.world.item.ItemStorageBundleItem;
import fuzs.metalbundles.common.world.item.storage.MetalBundleContentsStorage;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.ColorCollection;

import java.util.function.Supplier;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(MetalBundles.MOD_ID);
    public static final Holder.Reference<Item> COPPER_BUNDLE_ITEM = registerMetalBundleItem("copper_bundle");
    public static final ColorCollection<Holder.Reference<Item>> DYED_COPPER_BUNDLE_ITEM = registerMetalBundleItems(
            "copper_bundle");
    public static final Holder.Reference<Item> IRON_BUNDLE_ITEM = registerMetalBundleItem("iron_bundle");
    public static final ColorCollection<Holder.Reference<Item>> DYED_IRON_BUNDLE_ITEM = registerMetalBundleItems(
            "iron_bundle");
    public static final Holder.Reference<Item> GOLDEN_BUNDLE_ITEM = registerMetalBundleItem("golden_bundle");
    public static final ColorCollection<Holder.Reference<Item>> DYED_GOLDEN_BUNDLE_ITEM = registerMetalBundleItems(
            "golden_bundle");
    public static final Holder.Reference<Item> DIAMOND_BUNDLE_ITEM = registerMetalBundleItem("diamond_bundle");
    public static final ColorCollection<Holder.Reference<Item>> DYED_DIAMOND_BUNDLE_ITEM = registerMetalBundleItems(
            "diamond_bundle");
    public static final Holder.Reference<Item> NETHERITE_BUNDLE_ITEM = registerMetalBundleItem("netherite_bundle",
            () -> new Item.Properties().fireResistant());
    public static final ColorCollection<Holder.Reference<Item>> DYED_NETHERITE_BUNDLE_ITEM = registerMetalBundleItems(
            "netherite_bundle",
            () -> new Item.Properties().fireResistant());
    public static final Holder.Reference<ItemStorageType<?>> METAL_BUNDLE_ITEM_STORAGE_TYPE = REGISTRIES.register(
            ItemStorage.REGISTRY_KEY,
            "metal_bundle",
            () -> new ItemStorageType<>(MetalBundleContentsStorage.CODEC));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            DYED_GOLDEN_BUNDLE_ITEM.red());

    static final TagFactory TAGS = TagFactory.make(MetalBundles.MOD_ID);
    public static final TagKey<Item> BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("bundles");
    public static final TagKey<Item> COPPER_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("copper_bundles");
    public static final TagKey<Item> IRON_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("iron_bundles");
    public static final TagKey<Item> GOLDEN_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("golden_bundles");
    public static final TagKey<Item> DIAMOND_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("diamond_bundles");
    public static final TagKey<Item> NETHERITE_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("netherite_bundles");

    public static void bootstrap() {
        // NO-OP
    }

    private static ColorCollection<Holder.Reference<Item>> registerMetalBundleItems(String name) {
        return registerMetalBundleItems(name, Item.Properties::new);
    }

    private static ColorCollection<Holder.Reference<Item>> registerMetalBundleItems(String name, Supplier<Item.Properties> itemPropertiesSupplier) {
        return ColorCollection.NAMES.map((String color) -> {
            return color + "_" + name;
        }).map((String itemName) -> {
            return registerMetalBundleItem(itemName, itemPropertiesSupplier);
        });
    }

    private static Holder.Reference<Item> registerMetalBundleItem(String name) {
        return registerMetalBundleItem(name, Item.Properties::new);
    }

    private static Holder.Reference<Item> registerMetalBundleItem(String name, Supplier<Item.Properties> itemPropertiesSupplier) {
        return REGISTRIES.registerItem(name,
                ItemStorageBundleItem::new,
                () -> itemPropertiesSupplier.get()
                        .stacksTo(1)
                        .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
    }
}
