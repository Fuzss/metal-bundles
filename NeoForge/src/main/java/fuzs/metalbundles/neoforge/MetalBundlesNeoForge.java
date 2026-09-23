package fuzs.metalbundles.neoforge;

import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.data.ModItemStorageProvider;
import fuzs.metalbundles.common.data.tags.ModItemTagsProvider;
import fuzs.metalbundles.common.data.ModRecipeProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.fml.common.Mod;

@Mod(MetalBundles.MOD_ID)
public class MetalBundlesNeoForge {

    public MetalBundlesNeoForge() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new);
        DataProviderBuilder.of(MetalBundles.MOD_ID)
                .add(ItemStorage.Definition.REGISTRY_KEY, new ModItemStorageProvider())
                .addRecipeProvider(ModRecipeProvider::new)
                .addProvider(ModItemTagsProvider::new);
    }
}
