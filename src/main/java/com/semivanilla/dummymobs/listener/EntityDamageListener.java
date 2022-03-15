package com.semivanilla.dummymobs.listener;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Optional;


public class EntityDamageListener implements Listener {

    private final DummyMobs plugin;

    public EntityDamageListener(DummyMobs plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.isCancelled())
            return;

        if(!event.getEntity().hasMetadata(Dummies.DUMMY_META))
            return;
        event.getEntity().setFireTicks(0);
        event.getEntity().setVisualFire(false);
        final LivingEntity entity = (LivingEntity) event.getEntity();

        entity.setHealth(20.0);

        if(!(event.getDamager() instanceof Player))
            return;

        final Player player = (Player) event.getDamager();

        Optional<Dummies> optionalDummies = plugin.getDummyManager().getIfPresent(player);

        if(optionalDummies.isEmpty())
            return;

        if(!optionalDummies.get().getEntityUID().equals(entity.getUniqueId()))
            return;

        optionalDummies.get().update(event.getDamage());
        event.setDamage(0.0);
    }

    @EventHandler
    public void onFireDamage(EntityDamageEvent event){
        if(event.isCancelled())
            return;

        if(!event.getEntity().hasMetadata(Dummies.DUMMY_META))
            return;

        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() ==  EntityDamageEvent.DamageCause.FIRE) {
            event.getEntity().setFireTicks(0);
            event.getEntity().setVisualFire(false);
            event.setCancelled(true);
        }
    }

}
