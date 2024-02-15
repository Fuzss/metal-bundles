package fuzs.metalbundles.world.item;

import fuzs.puzzlesapi.api.iteminteractions.v1.ContainerItemHelper;
import fuzs.puzzlesapi.api.iteminteractions.v1.provider.ItemContainerProvider;
import fuzs.puzzleslib.api.core.v1.Proxy;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MetalBundleItem extends Item {
    public static final int LEATHER_BUNDLE_CAPACITY = 64;
    public static final int COPPER_BUNDLE_CAPACITY = 128;
    public static final int IRON_BUNDLE_CAPACITY = 512;
    public static final int GOLDEN_BUNDLE_CAPACITY = 1024;
    public static final int DIAMOND_BUNDLE_CAPACITY = 2048;
    public static final int NETHERITE_BUNDLE_CAPACITY = 4096;
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public final int capacity;

    public MetalBundleItem(int capacity, Properties properties) {
        super(properties);
        this.capacity = capacity;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stackInHand = player.getItemInHand(usedHand);
        ItemContainerProvider provider = ContainerItemHelper.INSTANCE.getItemContainerProvider(stackInHand);
        Objects.requireNonNull(provider, "provider is null");
        SimpleContainer container = provider.getItemContainer(stackInHand, player, true);
        List<ItemStack> items = container.removeAllItems();
        items.forEach(item -> player.drop(item, true));
        if (!items.isEmpty()) {
            player.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(stackInHand, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(stackInHand);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getContentWeight(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * getContentWeight(stack) / this.capacity, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(stack), this.capacity).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        ItemContainerProvider provider = ContainerItemHelper.INSTANCE.getItemContainerProvider(itemEntity.getItem());
        Objects.requireNonNull(provider, "provider is null");
        SimpleContainer container = provider.getItemContainer(itemEntity.getItem(), null, true);
        Stream<ItemStack> stream = ContainerItemHelper.INSTANCE.getListFromContainer(container).stream().filter(Predicate.not(ItemStack::isEmpty));
        ItemUtils.onContainerDestroyed(itemEntity, stream);
    }

    public static int getContentWeight(ItemStack containerStack) {
        return getContentWeight(containerStack, Proxy.INSTANCE.getClientPlayer());
    }

    public static int getContentWeight(ItemStack containerStack, @Nullable Player player) {
        ItemContainerProvider provider = ContainerItemHelper.INSTANCE.getItemContainerProvider(containerStack);
        Objects.requireNonNull(provider, "provider is null");
        return getContentWeight(provider, containerStack, player);
    }

    public static int getContentWeight(ItemContainerProvider provider, ItemStack containerStack, Player player) {
        SimpleContainer container = provider.getItemContainer(containerStack, player, false);
        return ContainerItemHelper.INSTANCE.getListFromContainer(container).stream().mapToInt(stack -> {
            return getItemWeight(stack) * stack.getCount();
        }).sum();
    }

    public static int getItemWeight(ItemStack stack) {
        if (stack.getItem() instanceof MetalBundleItem item) {
            return item.capacity / 16 + getContentWeight(stack, null);
        } else {
            return ContainerItemHelper.INSTANCE.getItemWeight(stack);
        }
    }
}
