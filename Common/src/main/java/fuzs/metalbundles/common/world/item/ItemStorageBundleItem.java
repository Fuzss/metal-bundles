package fuzs.metalbundles.common.world.item;

import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageHolder;
import fuzs.puzzleslib.common.api.util.v1.CommonHelper;
import net.minecraft.world.item.*;

public class ItemStorageBundleItem extends BundleItem {

    public ItemStorageBundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return ItemStorageHolder.ofItem(itemStack)
                .isBarVisible(itemStack, CommonHelper.getClientPlayer())
                .orElseGet(() -> super.isBarVisible(itemStack));
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return ItemStorageHolder.ofItem(itemStack)
                .getBarWidth(itemStack, CommonHelper.getClientPlayer())
                .orElseGet(() -> super.getBarWidth(itemStack));
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return ItemStorageHolder.ofItem(itemStack)
                .getBarColor(itemStack, CommonHelper.getClientPlayer())
                .orElseGet(() -> super.getBarColor(itemStack));
    }

    public static Item getVanillaByColor(DyeColor dyeColor) {
        return Items.DYED_BUNDLE.pick(dyeColor);
    }
}
