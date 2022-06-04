package org.slimefunguguproject.bump.implementation.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Sound;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.core.handlers.BowUseHandler;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.implementation.tasks.WitherSkullBowTask;

import javax.annotation.Nonnull;

/**
 * {@link WitherSkullBow Withered bow} will launch {@link WitherSkull} when using.
 *
 * @author ybw0014
 */
public class WitherSkullBow extends BumpBow {

    private final IntRangeSetting skullDuration = new IntRangeSetting(this, "skull-duration", 0, 10, 60);

    public WitherSkullBow() {
        super(5, BumpItems.WITHERSKULL_BOW, RecipeType.ARMOR_FORGE, new ItemStack[] {
            SlimefunItems.NECROTIC_SKULL, BumpItems.PEACH_WOOD, SlimefunItems.NECROTIC_SKULL,
            SlimefunItems.POWER_CRYSTAL, BumpItems.PEACH_WOOD, SlimefunItems.NECROTIC_SKULL,
            BumpItems.PEACH_WOOD, null, null
        });

        addItemSetting(skullDuration);
    }

    @Nonnull
    @Override
    public BowUseHandler getItemHandler() {
        return (e, p, item) -> {
            e.setCancelled(true);
            if (costHunger(p)) {
                damageItem(p, item);

                e.setCancelled(true);
                Bump.getLocalization().sendActionbarMessage(p, "weapon.wither_skull_bow");

                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0F, 1.0F);

                WitherSkull skull = p.launchProjectile(WitherSkull.class);
                WitherSkullBowTask.track(skull);
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }

    public int getSkullDuration() {
        return skullDuration.getValue();
    }
}
