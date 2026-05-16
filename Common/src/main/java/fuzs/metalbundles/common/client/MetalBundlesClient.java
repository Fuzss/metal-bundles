package fuzs.metalbundles.common.client;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.client.gui.ModBundleMouseActions;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.event.v1.gui.ScreenEvents;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ConfigHolder.registerConfigurationScreen(MetalBundles.MOD_ID, "iteminteractions");
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ScreenEvents.afterInit(AbstractContainerScreen.class).register(ModBundleMouseActions::onAfterInit);
    }
}
