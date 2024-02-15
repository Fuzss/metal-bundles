package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import fuzs.puzzleslib.api.core.v1.Proxy;
import net.minecraft.world.entity.player.Player;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(MetalBundles.id("filled"), (itemStack, clientLevel, livingEntity, i) -> {
            Player player = livingEntity instanceof Player ? (Player) livingEntity : Proxy.INSTANCE.getClientPlayer();
            return (float) MetalBundleItem.getContentWeight(itemStack, player) / ((MetalBundleItem) itemStack.getItem()).capacity;
        }, ModRegistry.LEATHER_BUNDLE_ITEM.get(), ModRegistry.COPPER_BUNDLE_ITEM.get(), ModRegistry.IRON_BUNDLE_ITEM.get(), ModRegistry.GOLDEN_BUNDLE_ITEM.get(), ModRegistry.DIAMOND_BUNDLE_ITEM.get(), ModRegistry.NETHERITE_BUNDLE_ITEM.get());
    }
}
