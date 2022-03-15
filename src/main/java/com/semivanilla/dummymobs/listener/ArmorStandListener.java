package com.semivanilla.dummymobs.listener;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.swing.text.html.parser.Entity;

public class ArmorStandListener implements Listener {

    private final DummyMobs plugin;

    public ArmorStandListener(DummyMobs plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArmorStand(EntityDamageEvent event){
        if(event.isCancelled())
            return;

        if(event.getEntity().hasMetadata(Dummies.DUMMY_META_HOLO)){
            event.setCancelled(true);
        }
    }


}
