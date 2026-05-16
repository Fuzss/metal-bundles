package fuzs.metalbundles.data;

import com.google.common.collect.Maps;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.common.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.bundleRecipes(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY,
                Items.COPPER_INGOT,
                Items.BUNDLE.builtInRegistryHolder(),
                ModRegistry.COPPER_BUNDLE_ITEM,
                getVanillaBundleItems(),
                ModRegistry.COPPER_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY,
                Items.IRON_INGOT,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                ModRegistry.IRON_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY,
                Items.GOLD_INGOT,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.IRON_BUNDLE_ITEMS,
                ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY,
                Items.DIAMOND,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY,
                Items.NETHERITE_INGOT,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    private static Map<DyeColor, Holder.Reference<Item>> getVanillaBundleItems() {
        return Arrays.stream(DyeColor.values())
                .collect(Maps.toImmutableEnumMap(Function.<DyeColor>identity(),
                        (DyeColor dyeColor) -> MetalBundleItem.getVanillaByColor(dyeColor).builtInRegistryHolder()));
    }

    private void bundleRecipes(TagKey<Item> tagKey, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        this.bundleRecipes(tagKey.location().getPath(),
                ingredientItem,
                bundleIngredientItem,
                bundleResultItem,
                bundleIngredientItems,
                bundleResultItems);
        this.bundleRecipes(tagKey, bundleResultItems);
    }

    private void bundleRecipes(String recipeGroup, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        this.bundleRecipe(recipeGroup, ingredientItem, bundleIngredientItem, bundleResultItem);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleIngredientItems.entrySet()) {
            this.bundleRecipe(recipeGroup, ingredientItem, entry.getValue(), bundleResultItems.get(entry.getKey()));
        }
    }

    private void bundleRecipe(String recipeGroup, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                        Ingredient.of(bundleIngredientItem.value()),
                        Ingredient.of(ingredientItem),
                        bundleResultItem.value())
                .group(recipeGroup)
                .unlockedBy(getHasName(ingredientItem), this.has(ingredientItem))
                .save(this.output);
    }

    /**
     * @see VanillaRecipeProvider#bundleRecipes()
     */
    private void bundleRecipes(TagKey<Item> tagKey, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        this.dyedBundleRecipe(tagKey, Items.WHITE_DYE, bundleItems.get(DyeColor.WHITE));
        this.dyedBundleRecipe(tagKey, Items.ORANGE_DYE, bundleItems.get(DyeColor.ORANGE));
        this.dyedBundleRecipe(tagKey, Items.MAGENTA_DYE, bundleItems.get(DyeColor.MAGENTA));
        this.dyedBundleRecipe(tagKey, Items.LIGHT_BLUE_DYE, bundleItems.get(DyeColor.LIGHT_BLUE));
        this.dyedBundleRecipe(tagKey, Items.YELLOW_DYE, bundleItems.get(DyeColor.YELLOW));
        this.dyedBundleRecipe(tagKey, Items.LIME_DYE, bundleItems.get(DyeColor.LIME));
        this.dyedBundleRecipe(tagKey, Items.PINK_DYE, bundleItems.get(DyeColor.PINK));
        this.dyedBundleRecipe(tagKey, Items.GRAY_DYE, bundleItems.get(DyeColor.GRAY));
        this.dyedBundleRecipe(tagKey, Items.LIGHT_GRAY_DYE, bundleItems.get(DyeColor.LIGHT_GRAY));
        this.dyedBundleRecipe(tagKey, Items.CYAN_DYE, bundleItems.get(DyeColor.CYAN));
        this.dyedBundleRecipe(tagKey, Items.PURPLE_DYE, bundleItems.get(DyeColor.PURPLE));
        this.dyedBundleRecipe(tagKey, Items.BLUE_DYE, bundleItems.get(DyeColor.BLUE));
        this.dyedBundleRecipe(tagKey, Items.BROWN_DYE, bundleItems.get(DyeColor.BROWN));
        this.dyedBundleRecipe(tagKey, Items.GREEN_DYE, bundleItems.get(DyeColor.GREEN));
        this.dyedBundleRecipe(tagKey, Items.RED_DYE, bundleItems.get(DyeColor.RED));
        this.dyedBundleRecipe(tagKey, Items.BLACK_DYE, bundleItems.get(DyeColor.BLACK));
    }

    /**
     * @see net.minecraft.data.recipes.RecipeProvider#dyedBundleRecipe(Item, Item)
     */
    private void dyedBundleRecipe(TagKey<Item> tagKey, Item dyeItem, Holder<Item> bundleItem) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                        this.tag(tagKey),
                        Ingredient.of(dyeItem),
                        bundleItem.value())
                .group(tagKey.location().getPath())
                .unlockedBy(getHasName(dyeItem), this.has(dyeItem))
                .save(this.output,
                        ResourceKey.create(Registries.RECIPE,
                                MetalBundles.id(getItemName(bundleItem.value()) + "_from_dying")));
    }
}
