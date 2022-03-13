package com.semivanilla.dummymobs.model;

import com.semivanilla.dummymobs.DummyMobs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class Dummies {

    public static final String DUMMY_META;

    static {
        DUMMY_META = "dummy-mob";
    }

    private final UUID playerUID;
    private final LivingEntity entity;

    public Dummies(UUID playerUID, Location currentLocation) {
        this.playerUID = playerUID;

        this.entity = (LivingEntity) currentLocation.getWorld().spawnEntity(currentLocation, EntityType.ZOMBIE);
        this.entity.setAI(false);
        this.entity.setMetadata(DUMMY_META, new FixedMetadataValue(DummyMobs.getPlugin(), 0.0));
        this.entity.setFireTicks(0);
        entity.setCustomNameVisible(true);
    }

    public UUID getPlayerUID() {
        return playerUID;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void updateLocation(@NotNull Location location){
        this.entity.teleport(location);
    }

    public void despawn(){
        if(this.entity.isValid())
            this.entity.remove();
    }

    public UUID getEntityUID(){
        return this.entity.getUniqueId();
    }

    public Optional<Player> getOwner(){
        return Optional.ofNullable(Bukkit.getPlayer(this.playerUID));
    }

    public Location getLocation(){
        return entity.getLocation();
    }

}
