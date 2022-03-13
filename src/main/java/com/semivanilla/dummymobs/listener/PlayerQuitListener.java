package com.semivanilla.dummymobs.listener;

import com.semivanilla.dummymobs.DummyMobs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final DummyMobs plugin;

    public PlayerQuitListener(DummyMobs plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        final Player player = event.getPlayer();
        if(!plugin.getDummyManager().hasDummy(player))
            return;

        plugin.getDummyManager().remove(player);
    }
}
