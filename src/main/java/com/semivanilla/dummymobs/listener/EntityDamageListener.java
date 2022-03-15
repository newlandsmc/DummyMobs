package com.semivanilla.dummymobs.listener;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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

        if(!event.getEntity().hasMetadata(Dummies.DUMMY_META_MOB))
            return;
        event.getEntity().setFireTicks(0);
        event.getEntity().setVisualFire(false);
        final LivingEntity entity = (LivingEntity) event.getEntity();

        entity.setHealth(20.0);

        if(!(event.getDamager() instanceof Player) && !(event.getDamager() instanceof Projectile))
            return;

        Optional<Dummies> optionalDummies = plugin.getDummyManager().getByEntityID(entity.getUniqueId());

        if(optionalDummies.isEmpty())
            return;

        optionalDummies.get().update(event.getDamage());
        event.setDamage(0.0);
    }


    @EventHandler
    public void onCombustEvent(EntityCombustEvent event){
        if(event.isCancelled())
            return;

        Entity entity = event.getEntity();
        if(entity.hasMetadata(Dummies.DUMMY_META_MOB)){
            event.setCancelled(true);
            event.setDuration(0);
        }
    }

}
