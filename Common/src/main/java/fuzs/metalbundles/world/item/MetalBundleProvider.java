package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.provider.BundleProvider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MetalBundleProvider extends BundleProvider {

    public MetalBundleProvider(int capacity, @Nullable DyeColor dyeColor) {
        super(capacity, dyeColor, "Items");
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    @Override
    protected int getItemWeight(ItemStack stack) {
        return MetalBundleItem.getItemWeight(stack);
    }
}
