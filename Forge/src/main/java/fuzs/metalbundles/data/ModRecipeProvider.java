package fuzs.metalbundles.data;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.LEATHER_BUNDLE_ITEM.get())
                .define('#', Items.LEATHER)
                .define('-', Items.STRING)
                .pattern("-#-")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(exporter);
        legacyNetheriteSmithing(MetalBundles.MOD_ID, exporter, ModRegistry.LEATHER_BUNDLE_ITEM.get(), Items.COPPER_INGOT, RecipeCategory.TOOLS, ModRegistry.COPPER_BUNDLE_ITEM.get());
        legacyNetheriteSmithing(MetalBundles.MOD_ID, exporter, ModRegistry.COPPER_BUNDLE_ITEM.get(), Items.IRON_INGOT, RecipeCategory.TOOLS, ModRegistry.IRON_BUNDLE_ITEM.get());
        legacyNetheriteSmithing(MetalBundles.MOD_ID, exporter, ModRegistry.IRON_BUNDLE_ITEM.get(), Items.GOLD_INGOT, RecipeCategory.TOOLS, ModRegistry.GOLDEN_BUNDLE_ITEM.get());
        legacyNetheriteSmithing(MetalBundles.MOD_ID, exporter, ModRegistry.GOLDEN_BUNDLE_ITEM.get(), Items.DIAMOND, RecipeCategory.TOOLS, ModRegistry.DIAMOND_BUNDLE_ITEM.get());
        legacyNetheriteSmithing(MetalBundles.MOD_ID, exporter, ModRegistry.DIAMOND_BUNDLE_ITEM.get(), Items.NETHERITE_INGOT, RecipeCategory.TOOLS, ModRegistry.NETHERITE_BUNDLE_ITEM.get());
    }
}
