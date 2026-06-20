package fuzs.metalbundles.common.data.client;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;

public class ModLanguageProvider extends AbstractLanguageProvider {
    public static final ColorCollection<String> NAMES = new ColorCollection<>("White",
            "Orange",
            "Magenta",
            "Light Blue",
            "Yellow",
            "Lime",
            "Pink",
            "Gray",
            "Light Gray",
            "Cyan",
            "Purple",
            "Blue",
            "Brown",
            "Green",
            "Red",
            "Black");

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), MetalBundles.MOD_NAME);
        addMetalBundles(translationBuilder,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.DYED_COPPER_BUNDLE_ITEM,
                "Copper Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.DYED_IRON_BUNDLE_ITEM,
                "Iron Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DYED_GOLDEN_BUNDLE_ITEM,
                "Golden Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.DYED_DIAMOND_BUNDLE_ITEM,
                "Diamond Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.DYED_NETHERITE_BUNDLE_ITEM,
                "Netherite Bundle");
    }

    private static void addMetalBundles(TranslationBuilder translationBuilder, Holder.Reference<Item> bundleItem, ColorCollection<Holder.Reference<Item>> dyedBundleItem, String itemName) {
        translationBuilder.addItem(bundleItem, itemName);
        ColorCollection.zipApply(NAMES, dyedBundleItem, (String color, Holder.Reference<Item> item) -> {
            translationBuilder.addItem(item, color + " " + itemName);
        });
    }
}
