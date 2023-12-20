package io.github.slimefunguguproject.bump.implementation.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

/**
 * This {@link Listener} is responsible for dragon breath damage.
 *
 * @author ybw0014
 */
public class DragonBreathListener implements Listener {
    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (damager instanceof AreaEffectCloud
            && ((AreaEffectCloud)damager).getParticle() == Particle.DRAGON_BREATH
        ) {
            ProjectileSource damageSource = ((AreaEffectCloud)damager).getSource();
            if (damageSource instanceof Player && entity instanceof Player
                && ((Player)damageSource).getUniqueId().equals(((Player)entity).getUniqueId())
            )
            e.setCancelled(true);
        }
    }
}
