package fuzs.metalbundles.data;

import fuzs.puzzleslib.api.data.v1.AbstractModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(PackOutput packOutput, String modId, ExistingFileHelper fileHelper) {
        super(packOutput, modId, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.bundle(this.modLoc("leather_bundle"), new ResourceLocation("bundle"), this.modLoc("leather_bundle_filled"), new ResourceLocation("bundle_filled"));
        this.bundle(this.modLoc("copper_bundle"), this.modLoc("copper_bundle_filled"));
        this.bundle(this.modLoc("iron_bundle"), this.modLoc("iron_bundle_filled"));
        this.bundle(this.modLoc("golden_bundle"), this.modLoc("golden_bundle_filled"));
        this.bundle(this.modLoc("diamond_bundle"), this.modLoc("diamond_bundle_filled"));
        this.bundle(this.modLoc("netherite_bundle"), this.modLoc("netherite_bundle_filled"));
    }

    private void bundle(ResourceLocation modelLocation, ResourceLocation filledModelLocation) {
        this.bundle(modelLocation, modelLocation, filledModelLocation, filledModelLocation);
    }

    private void bundle(ResourceLocation itemLocation, ResourceLocation modelLocation, ResourceLocation filledItemLocation, ResourceLocation filledModelLocation) {
        this.itemModels().getBuilder(filledItemLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(filledModelLocation.getNamespace(), "item/" + filledModelLocation.getPath()));
        this.itemModels().getBuilder(itemLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(modelLocation.getNamespace(), "item/" + modelLocation.getPath()))
                .override().predicate(this.modLoc("filled"), 1.0F)
                .model(new ModelFile.ExistingModelFile(new ResourceLocation(filledModelLocation.getNamespace(), "item/" + filledModelLocation.getPath()), this.models().existingFileHelper))
                .end();
    }
}
