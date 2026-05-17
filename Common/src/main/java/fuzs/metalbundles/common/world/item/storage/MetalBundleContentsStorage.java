package fuzs.metalbundles.common.world.item.storage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.common.api.v2.world.item.storage.BundleContentsStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import fuzs.metalbundles.common.MetalBundles;
import fuzs.metalbundles.common.config.ServerConfig;
import fuzs.metalbundles.common.init.ModRegistry;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

public class MetalBundleContentsStorage extends BundleContentsStorage {
    public static final MapCodec<MetalBundleContentsStorage> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(itemContentsCodec()).apply(instance, MetalBundleContentsStorage::new);
    });

    public MetalBundleContentsStorage(StorageOptions storageOptions) {
        super(storageOptions);
    }

    @Override
    public Fraction getMaxWeight(ItemStack itemStack) {
        int capacityMultiplier = this.getConfigBasedCapacityMultiplier(itemStack);
        if (capacityMultiplier != -1) {
            return Fraction.getFraction(capacityMultiplier, DEFAULT_CAPACITY_MULTIPLIER);
        } else {
            return super.getMaxWeight(itemStack);
        }
    }

    private int getConfigBasedCapacityMultiplier(ItemStack itemStack) {
        if (itemStack.is(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).copperCapacityMultiplier;
        } else if (itemStack.is(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).ironCapacityMultiplier;
        } else if (itemStack.is(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).goldenCapacityMultiplier;
        } else if (itemStack.is(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).diamondCapacityMultiplier;
        } else if (itemStack.is(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).netheriteCapacityMultiplier;
        } else {
            return -1;
        }
    }

    @Override
    public ItemStorageType<?> getType() {
        return ModRegistry.METAL_BUNDLE_ITEM_STORAGE_TYPE.value();
    }
}
