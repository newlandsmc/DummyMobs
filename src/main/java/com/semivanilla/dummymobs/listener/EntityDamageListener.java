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
import org.bukkit.metadata.FixedMetadataValue;


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

        final LivingEntity entity = (LivingEntity) event.getEntity();

        entity.setHealth(20.0);

        if(!(event.getDamager() instanceof Player))
            return;

        final Player player = (Player) event.getDamager();

        if(!plugin.getDummyManager().hasDummy(player) && !plugin.getDummyManager().getIfPresent(player).get().getEntityUID().equals(entity.getUniqueId()))
            return;

        double totalDamage = entity.getMetadata(Dummies.DUMMY_META).get(0).asDouble();
        totalDamage = totalDamage + event.getDamage();

        event.setDamage(0);
        entity.setCustomName(String.valueOf(totalDamage));
        entity.setMetadata(Dummies.DUMMY_META, new FixedMetadataValue(DummyMobs.getPlugin(), totalDamage));
    }


}
