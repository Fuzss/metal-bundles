package fuzs.metalbundles.common.data.client;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.StringJoiner;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), MetalBundles.MOD_NAME);
        addMetalBundles(translationBuilder,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                "Copper Bundle");
        addMetalBundles(translationBuilder, ModRegistry.IRON_BUNDLE_ITEM, ModRegistry.IRON_BUNDLE_ITEMS, "Iron Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                "Golden Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                "Diamond Bundle");
        addMetalBundles(translationBuilder,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.NETHERITE_BUNDLE_ITEMS,
                "Netherite Bundle");
    }

    private static void addMetalBundles(TranslationBuilder translationBuilder, Holder.Reference<Item> bundleItem, Map<DyeColor, Holder.Reference<Item>> bundleItems, String bundleName) {
        translationBuilder.addItem(bundleItem, bundleName);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            String dyeColor = capitalizeFully(entry.getKey().getName());
            translationBuilder.addItem(entry.getValue(), dyeColor + " " + bundleName);
        }
    }

    private static String capitalizeFully(String s) {
        s = s.replaceAll("\\W+", " ").replace('_', ' ');
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (String string : s.split("\\s+")) {
            if (!string.isEmpty()) {
                stringJoiner.add(Character.toUpperCase(string.charAt(0)) + string.substring(1));
            }
        }

        return stringJoiner.toString();
    }
}
