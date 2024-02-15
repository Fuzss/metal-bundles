package fuzs.metalbundles.data;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractLanguageProvider;
import net.minecraft.data.PackOutput;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(PackOutput packOutput, String modId) {
        super(packOutput, modId);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeModeTab(MetalBundles.MOD_NAME);
        this.add(ModRegistry.LEATHER_BUNDLE_ITEM.get(), "Leather Bundle");
        this.add(ModRegistry.COPPER_BUNDLE_ITEM.get(), "Copper Bundle");
        this.add(ModRegistry.IRON_BUNDLE_ITEM.get(), "Iron Bundle");
        this.add(ModRegistry.GOLDEN_BUNDLE_ITEM.get(), "Golden Bundle");
        this.add(ModRegistry.DIAMOND_BUNDLE_ITEM.get(), "Diamond Bundle");
        this.add(ModRegistry.NETHERITE_BUNDLE_ITEM.get(), "Netherite Bundle");
    }
}
