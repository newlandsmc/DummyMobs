package com.semivanilla.dummymobs.listener;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    private final DummyMobs plugin;

    public EntityDamageListener(DummyMobs plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.isCancelled())
            return;

        Entity entity = event.getEntity();
        if(!entity.hasMetadata(Dummies.DUMMY_META))
            return;

        if(event.getDamager() == null)
            return;

        if(!(event.getDamager() instanceof Player))
            return;
        
    }

}
