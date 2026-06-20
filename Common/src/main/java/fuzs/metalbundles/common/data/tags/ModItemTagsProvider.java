package fuzs.metalbundles.common.data.tags;

import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        // Do not add to the vanilla bundles item tag, it is used for the custom bundle mouse action.
        this.tag(ModRegistry.BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY);
        this.addMetalBundleTag(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.DYED_COPPER_BUNDLE_ITEM);
        this.addMetalBundleTag(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.DYED_IRON_BUNDLE_ITEM);
        this.addMetalBundleTag(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DYED_GOLDEN_BUNDLE_ITEM);
        this.addMetalBundleTag(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.DYED_DIAMOND_BUNDLE_ITEM);
        this.addMetalBundleTag(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.DYED_NETHERITE_BUNDLE_ITEM);
    }

    private void addMetalBundleTag(TagKey<Item> key, Holder.Reference<Item> bundleItem, ColorCollection<Holder.Reference<Item>> dyedBundleItem) {
        AbstractTagAppender<Item> tag = this.tag(key);
        tag.add(bundleItem);
        dyedBundleItem.forEach(tag::add);
    }
}
