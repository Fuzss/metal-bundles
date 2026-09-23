package fuzs.metalbundles.neoforge.client;

import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.client.MetalBundlesClient;
import fuzs.metalbundles.common.data.client.ModLanguageProvider;
import fuzs.metalbundles.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MetalBundles.MOD_ID, dist = Dist.CLIENT)
public class MetalBundlesNeoForgeClient {

    public MetalBundlesNeoForgeClient() {
        ClientModConstructor.construct(MetalBundles.MOD_ID, MetalBundlesClient::new);
        DataProviderBuilder.of(MetalBundles.MOD_ID).addProvider(ModLanguageProvider::new, ModModelProvider::new);
    }
}
