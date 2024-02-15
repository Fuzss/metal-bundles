package fuzs.metalbundles;

import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class MetalBundlesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new, ContentRegistrationFlags.LEGACY_SMITHING);
    }
}
