package fuzs.metalbundles.common.client;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ConfigHolder.registerConfigurationScreen(MetalBundles.MOD_ID, "iteminteractions");
    }
}
