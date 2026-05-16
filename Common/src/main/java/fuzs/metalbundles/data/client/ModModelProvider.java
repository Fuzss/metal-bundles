package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.common.api.client.data.v2.models.ItemModelGenerationHelper;
import fuzs.puzzleslib.common.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.BundleSelectedItemSpecialRenderer;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.BundleHasSelectedItem;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;

import java.util.Map;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.COPPER_BUNDLE_ITEM.value(),
                ModRegistry.COPPER_BUNDLE_ITEMS,
                "copper_bundle_string",
                "copper_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.IRON_BUNDLE_ITEM.value(),
                ModRegistry.IRON_BUNDLE_ITEMS,
                "iron_bundle_string",
                "iron_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.GOLDEN_BUNDLE_ITEM.value(),
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                "golden_bundle_string",
                "golden_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.DIAMOND_BUNDLE_ITEM.value(),
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                "diamond_bundle_string",
                "diamond_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.NETHERITE_BUNDLE_ITEM.value(),
                ModRegistry.NETHERITE_BUNDLE_ITEMS,
                "netherite_bundle_string",
                "netherite_bundle_open_string");
    }

    public final void createMetalBundleItem(ItemModelGenerators itemModelGenerators, Item bundleItem, Map<DyeColor, Holder.Reference<Item>> bundleItems, String string, String openString) {
        Material stringTexture = ModelLocationHelper.getItemTexture(MetalBundles.id(string));
        Material openTexture = ModelLocationHelper.getItemTexture(MetalBundles.id(openString));
        this.createMetalBundleItem(itemModelGenerators, bundleItem, Items.BUNDLE, stringTexture, openTexture);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.createMetalBundleItem(itemModelGenerators,
                    entry.getValue().value(),
                    MetalBundleItem.getVanillaByColor(entry.getKey()),
                    stringTexture,
                    openTexture);
        }
    }

    /**
     * @see ItemModelGenerators#generateBundleModels(Item)
     */
    public final void createMetalBundleItem(ItemModelGenerators itemModelGenerators, Item bundleItem, Item baseBundleItem, Material stringTexture, Material openTexture) {
        Identifier identifier = ItemModelGenerationHelper.createLayeredItemModel(bundleItem,
                ModelLocationHelper.getItemTexture(baseBundleItem),
                stringTexture,
                ModelTemplates.TWO_LAYERED_ITEM,
                itemModelGenerators.modelOutput);
        Identifier openBackResourceLocation = ItemModelGenerationHelper.createFlatItemModel(ModelLocationHelper.getItemModel(
                        bundleItem,
                        "_open_back"),
                ModelLocationHelper.getItemTexture(baseBundleItem, "_open_back"),
                ModelTemplates.FLAT_ITEM,
                itemModelGenerators.modelOutput);
        Identifier openFrontResourceLocation = ItemModelGenerationHelper.createLayeredItemModel(ModelLocationHelper.getItemModel(
                        bundleItem,
                        "_open_front"),
                ModelLocationHelper.getItemTexture(baseBundleItem, "_open_front"),
                openTexture,
                ModelTemplates.TWO_LAYERED_ITEM,
                itemModelGenerators.modelOutput);
        ItemModel.Unbaked unbaked = ItemModelUtils.plainModel(identifier);
        ItemModel.Unbaked unbaked2 = ItemModelUtils.composite(ItemModelUtils.plainModel(openBackResourceLocation),
                new BundleSelectedItemSpecialRenderer.Unbaked(),
                ItemModelUtils.plainModel(openFrontResourceLocation));
        ItemModel.Unbaked unbaked3 = ItemModelUtils.conditional(new BundleHasSelectedItem(), unbaked2, unbaked);
        itemModelGenerators.itemModelOutput.accept(bundleItem,
                ItemModelUtils.select(new DisplayContext(),
                        unbaked,
                        ItemModelUtils.when(ItemDisplayContext.GUI, unbaked3)));
    }
}
