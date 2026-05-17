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
        return switch (dyeColor) {
            case WHITE -> Items.WHITE_BUNDLE;
            case ORANGE -> Items.ORANGE_BUNDLE;
            case MAGENTA -> Items.MAGENTA_BUNDLE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_BUNDLE;
            case YELLOW -> Items.YELLOW_BUNDLE;
            case LIME -> Items.LIME_BUNDLE;
            case PINK -> Items.PINK_BUNDLE;
            case GRAY -> Items.GRAY_BUNDLE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_BUNDLE;
            case CYAN -> Items.CYAN_BUNDLE;
            case BLUE -> Items.BLUE_BUNDLE;
            case BROWN -> Items.BROWN_BUNDLE;
            case GREEN -> Items.GREEN_BUNDLE;
            case RED -> Items.RED_BUNDLE;
            case BLACK -> Items.BLACK_BUNDLE;
            case PURPLE -> Items.PURPLE_BUNDLE;
        };
    }
}
