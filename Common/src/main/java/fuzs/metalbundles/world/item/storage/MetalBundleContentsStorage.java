package fuzs.metalbundles.world.item.storage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.common.api.v2.world.item.storage.BundleContentsStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.config.ServerConfig;
import fuzs.metalbundles.init.ModRegistry;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

public class MetalBundleContentsStorage extends BundleContentsStorage {
    public static final MapCodec<MetalBundleContentsStorage> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(itemContentsCodec()).apply(instance, MetalBundleContentsStorage::new);
    });

    public MetalBundleContentsStorage(StorageOptions storageOptions) {
        super(DEFAULT_CAPACITY_MULTIPLIER, storageOptions);
    }

    @Override
    public Fraction getCapacityMultiplier(ItemStack containerStack) {
        int capacityMultiplier = this.getConfigBasedCapacityMultiplier(containerStack);
        if (capacityMultiplier != -1) {
            return Fraction.getFraction(capacityMultiplier, DEFAULT_CAPACITY_MULTIPLIER);
        } else {
            return super.getCapacityMultiplier(containerStack);
        }
    }

    private int getConfigBasedCapacityMultiplier(ItemStack containerStack) {
        if (containerStack.is(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).copperCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).ironCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).goldenCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).diamondCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY)) {
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
