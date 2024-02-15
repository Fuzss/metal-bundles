package fuzs.metalbundles;

import fuzs.metalbundles.data.ModItemContainerProvider;
import fuzs.metalbundles.data.ModLanguageProvider;
import fuzs.metalbundles.data.ModModelProvider;
import fuzs.metalbundles.data.ModRecipeProvider;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.util.concurrent.CompletableFuture;

@Mod(MetalBundles.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MetalBundlesForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new, ContentRegistrationFlags.LEGACY_SMITHING);
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        final DataGenerator dataGenerator = evt.getGenerator();
        final PackOutput packOutput = dataGenerator.getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = evt.getLookupProvider();
        final ExistingFileHelper fileHelper = evt.getExistingFileHelper();
        dataGenerator.addProvider(true, new ModItemContainerProvider(packOutput));
        dataGenerator.addProvider(true, new ModLanguageProvider(packOutput, MetalBundles.MOD_ID));
        dataGenerator.addProvider(true, new ModModelProvider(packOutput, MetalBundles.MOD_ID, fileHelper));
        dataGenerator.addProvider(true, new ModRecipeProvider(packOutput));
    }
}
