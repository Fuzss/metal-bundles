package fuzs.metalbundles.common.data;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.ColorCollection;

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
                Items.DYED_BUNDLE.map(Item::builtInRegistryHolder),
                ModRegistry.DYED_COPPER_BUNDLE_ITEM);
        this.bundleRecipes(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY,
                Items.IRON_INGOT,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.DYED_COPPER_BUNDLE_ITEM,
                ModRegistry.DYED_IRON_BUNDLE_ITEM);
        this.bundleRecipes(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY,
                Items.GOLD_INGOT,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DYED_IRON_BUNDLE_ITEM,
                ModRegistry.DYED_GOLDEN_BUNDLE_ITEM);
        this.bundleRecipes(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY,
                Items.DIAMOND,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.DYED_GOLDEN_BUNDLE_ITEM,
                ModRegistry.DYED_DIAMOND_BUNDLE_ITEM);
        this.bundleRecipes(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY,
                Items.NETHERITE_INGOT,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.DYED_DIAMOND_BUNDLE_ITEM,
                ModRegistry.DYED_NETHERITE_BUNDLE_ITEM);
    }

    private void bundleRecipes(TagKey<Item> tag, Item ingredient, Holder.Reference<Item> bundle, Holder.Reference<Item> result, ColorCollection<Holder.Reference<Item>> dyedBundle, ColorCollection<Holder.Reference<Item>> dyedResult) {
        this.bundleRecipes(tag.location().getPath(), ingredient, bundle, result, dyedBundle, dyedResult);
        this.bundleRecipes(tag, dyedResult);
    }

    private void bundleRecipes(String recipeGroup, Item ingredient, Holder.Reference<Item> bundle, Holder.Reference<Item> result, ColorCollection<Holder.Reference<Item>> dyedBundle, ColorCollection<Holder.Reference<Item>> dyedResult) {
        this.bundleRecipe(recipeGroup, ingredient, bundle, result);
        ColorCollection.zipApply(dyedBundle,
                dyedResult,
                (Holder.Reference<Item> bundleItem, Holder.Reference<Item> resultItem) -> {
                    this.bundleRecipe(recipeGroup, ingredient, bundleItem, resultItem);
                });
    }

    private void bundleRecipe(String recipeGroup, Item ingredient, Holder.Reference<Item> bundle, Holder.Reference<Item> result) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                        Ingredient.of(bundle.value()),
                        Ingredient.of(ingredient),
                        result.value())
                .group(recipeGroup)
                .unlockedBy(getHasName(ingredient), this.has(ingredient))
                .save(this.output);
    }

    private void bundleRecipes(TagKey<Item> tag, ColorCollection<Holder.Reference<Item>> dyedBundle) {
        ColorCollection.zipApply(Items.DYE, dyedBundle, (Item dyeItem, Holder.Reference<Item> bundleItem) -> {
            this.dyedBundleRecipe(tag, dyeItem, bundleItem);
        });
    }

    /**
     * @see net.minecraft.data.recipes.RecipeProvider#dyedBundleRecipe(Item, Item)
     */
    private void dyedBundleRecipe(TagKey<Item> tag, Item dyeItem, Holder<Item> bundleItem) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                        this.tag(tag),
                        Ingredient.of(dyeItem),
                        bundleItem.value())
                .group(tag.location().getPath())
                .unlockedBy(getHasName(dyeItem), this.has(dyeItem))
                .save(this.output,
                        ResourceKey.create(Registries.RECIPE,
                                MetalBundles.id(getItemName(bundleItem.value()) + "_from_dying")));
    }
}
