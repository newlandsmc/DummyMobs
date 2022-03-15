package com.semivanilla.dummymobs.manager;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.model.Dummies;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DummyManager {

    private final DummyMobs plugin;
    private final HashMap<UUID, Dummies> dummiesHashMap; //Dummy UUID, Dummy Object

    public DummyManager(DummyMobs plugin) {
        this.plugin = plugin;
        this.dummiesHashMap = new HashMap<>();
    }

    public DummyMobs getPlugin() {
        return plugin;
    }

    public Dummies spawn(@NotNull Player player, @NotNull Location location){
        final Dummies dummies = new Dummies(player.getUniqueId(), location);
        this.dummiesHashMap.put(dummies.getEntityUID(), dummies);
        return dummies;
    }

    public Dummies spawn(@NotNull Player player){
        return this.spawn(player, player.getLocation());
    }

    public Optional<Dummies> getDummyOfPlayer(@NotNull Player player){
        return dummiesHashMap.values().stream().filter(dummy -> dummy.getPlayerUID().equals(player.getUniqueId())).findFirst();
    }

    public Optional<Dummies> getByEntityID(@NotNull UUID uuid){
        return Optional.ofNullable(dummiesHashMap.get(uuid));
    }


    public void remove(@NotNull Player player){
        getDummyOfPlayer(player).ifPresent(dummy -> {
            dummy.despawn();
            dummiesHashMap.remove(dummy.getEntityUID());
        });
    }

    public Collection<Dummies> getAllDummies(){
        return dummiesHashMap.values();
    }
}
