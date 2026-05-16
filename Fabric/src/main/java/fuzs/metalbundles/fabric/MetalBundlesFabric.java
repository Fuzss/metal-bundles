package fuzs.metalbundles.fabric;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class MetalBundlesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new);
    }
}
