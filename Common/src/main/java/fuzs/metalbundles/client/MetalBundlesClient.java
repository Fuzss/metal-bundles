package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import fuzs.puzzleslib.api.core.v1.Proxy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class MetalBundlesClient implements ClientModConstructor {
    public static final ResourceLocation ITEM_MODEL_PROPETY_FILLED = MetalBundles.id("filled");

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(ITEM_MODEL_PROPETY_FILLED,
                (itemStack, clientLevel, livingEntity, i) -> {
                    Player player = livingEntity instanceof Player ?
                            (Player) livingEntity :
                            Proxy.INSTANCE.getClientPlayer();
                    return MetalBundleItem.getContentWeight(itemStack, player) > 0 ? 1.0F : 0.0F;
                },
                ModRegistry.LEATHER_BUNDLE_ITEM.value(),
                ModRegistry.COPPER_BUNDLE_ITEM.value(),
                ModRegistry.IRON_BUNDLE_ITEM.value(),
                ModRegistry.GOLDEN_BUNDLE_ITEM.value(),
                ModRegistry.DIAMOND_BUNDLE_ITEM.value(),
                ModRegistry.NETHERITE_BUNDLE_ITEM.value()
        );
    }
}
