package com.semivanilla.dummymobs.manager;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DummyManager {

    private final DummyMobs plugin;
    private final HashMap<UUID, Dummies> dummiesHashMap;

    public DummyManager(DummyMobs plugin) {
        this.plugin = plugin;
        this.dummiesHashMap = new HashMap<>();
    }

    public DummyMobs getPlugin() {
        return plugin;
    }

    public Dummies spawn(@NotNull Player player, @NotNull Location location){
        final Dummies dummies = new Dummies(player.getUniqueId(), location);
        this.dummiesHashMap.put(player.getUniqueId(), dummies);
        return dummies;
    }

    public Dummies spawn(@NotNull Player player){
        return this.spawn(player, player.getLocation());
    }

    public Optional<Dummies> getIfPresent(@NotNull Player player){
        return Optional.ofNullable(dummiesHashMap.get(player.getUniqueId()));
    }

    public boolean hasDummy(@NotNull Player player){
        return dummiesHashMap.containsKey(player.getUniqueId());
    }

    public void remove(@NotNull Player player){
        getIfPresent(player).ifPresent(dummy -> {
            dummy.despawn();
            dummiesHashMap.remove(player.getUniqueId());
        });
    }

    public Collection<Dummies> getAllDummies(){
        return dummiesHashMap.values();
    }
}
