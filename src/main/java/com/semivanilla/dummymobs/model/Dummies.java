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
import java.util.Random;
import java.util.UUID;

public class Dummies {

    public static final String DUMMY_META_MOB;
    public static final String DUMMY_META_HOLO;
    private static final Random RANDOM;

    static {
        DUMMY_META_MOB = "dummy-mob";
        DUMMY_META_HOLO = "dummy-holo";
        RANDOM = new Random(System.currentTimeMillis());

    }

    private final UUID playerUID;
    private final LivingEntity entity;
    private double totalDamageDealt;
    private int strikeGiven;

    public Dummies(UUID playerUID, Location currentLocation) {
        this.playerUID = playerUID;
        this.totalDamageDealt = 0.0;
        this.strikeGiven = 0;
        Location location = currentLocation.clone();
        location.setYaw(0.0F);
        location.setPitch(0.0F);
        this.entity = (LivingEntity) currentLocation.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        this.entity.setAI(false);
        this.entity.setMetadata(DUMMY_META_MOB, new FixedMetadataValue(DummyMobs.getPlugin(), 0.0));
        this.entity.setVisualFire(false);
        this.entity.setFireTicks(0);
        entity.setCustomNameVisible(false);
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

    public void update(double damage){
        this.totalDamageDealt = damage + totalDamageDealt;
        this.strikeGiven+=1;

        final Location newHoloLocation = entity.getLocation().clone();
        newHoloLocation.add((RANDOM.nextBoolean() ? 1 : -1) * RANDOM.nextFloat(DummyMobs.getPlugin().getConfiguration().getHoloOffsetX()),
                -1 * RANDOM.nextFloat(DummyMobs.getPlugin().getConfiguration().getHoloOffsetY()),
                (RANDOM.nextBoolean() ? -1 : 1) * RANDOM.nextFloat(DummyMobs.getPlugin().getConfiguration().getHoloOffsetZ()));


        final LivingEntity entity = (LivingEntity) newHoloLocation.getWorld().spawnEntity(newHoloLocation, EntityType.ARMOR_STAND);
        entity.setInvisible(true);
        entity.setCustomNameVisible(true);
        entity.setCustomName(String.format("%.2f",damage));
        entity.setGravity(false);
        entity.setCollidable(false);
        entity.setInvulnerable(true);
        entity.setAI(false);
        entity.setMetadata(DUMMY_META_HOLO, new FixedMetadataValue(DummyMobs.getPlugin(), "0"));

        final UUID uuid = entity.getUniqueId();

        DummyMobs.getPlugin().getServer().getScheduler().runTaskLater(DummyMobs.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(entity.isValid()) {
                    entity.remove();
                }
            }
        },40);
    }


}
