package fuzs.metalbundles.neoforge;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.data.ModItemStorageDefinitionsProvider;
import fuzs.metalbundles.common.data.tags.ModItemTagProvider;
import fuzs.metalbundles.common.data.ModRecipeProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(MetalBundles.MOD_ID)
public class MetalBundlesNeoForge {

    public MetalBundlesNeoForge() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new);
        DataProviderHelper.registerDataProviders(MetalBundles.MOD_ID,
                ModItemStorageDefinitionsProvider::new,
                ModRecipeProvider::new,
                ModItemTagProvider::new);
    }
}
