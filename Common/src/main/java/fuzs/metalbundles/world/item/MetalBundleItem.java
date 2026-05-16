package fuzs.metalbundles.world.item;

import com.mojang.serialization.DataResult;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageHolder;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.storage.MetalBundleContentsStorage;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

public class MetalBundleItem extends BundleItem {

    public MetalBundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + Mth.mulAndTruncate(getAdjustedWeightSafe(itemStack), 12), 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return getAdjustedWeightSafe(itemStack).compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
    }

    /**
     * @see BundleItem#getWeightSafe(BundleContents)
     */
    private static Fraction getAdjustedWeightSafe(ItemStack itemStack) {
        BundleContents contents = itemStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return switch (contents.weight()) {
            case DataResult.Success<Fraction> success -> getAdjustedWeight(itemStack, success.value());
            case DataResult.Error<?> _ -> Fraction.ONE;
        };
    }

    private static Fraction getAdjustedWeight(ItemStack itemStack, Fraction weight) {
        ItemStorageHolder holder = ItemStorageHolder.ofItem(itemStack);
        if (holder.storage().getType() == ModRegistry.METAL_BUNDLE_ITEM_STORAGE_TYPE.value()) {
            return weight.divideBy(((MetalBundleContentsStorage) holder.storage()).getCapacityMultiplier(itemStack));
        } else {
            return weight;
        }
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
