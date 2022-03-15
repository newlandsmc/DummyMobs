package com.semivanilla.dummymobs.config;

import com.semivanilla.dummymobs.ConfigPath;
import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.abstracts.AbstractConfig;
import com.semivanilla.dummymobs.interfaces.PluginFiles;
import de.leonhard.storage.Config;

public class Configuration extends AbstractConfig implements PluginFiles {

    private int holoOffsetX,holoOffsetY,holoOffsetZ;

    public Configuration(DummyMobs plugin) {
        super(plugin);
    }

    @Override
    public boolean initConfig() {
        this.file = new Config("config.yml",plugin.getDataFolder().getPath());
        return isFileInitialized();
    }

    @Override
    public long reload() {
        long time = System.currentTimeMillis();
        initConfig();
        loadConfiguration();
        return System.currentTimeMillis() - time;
    }

    @Override
    public void loadConfiguration() {
        this.holoOffsetX = file.getInt(ConfigPath.HOLO_OFFSET_X.path());
        this.holoOffsetY = file.getInt(ConfigPath.HOLO_OFFSET_Y.path());
        this.holoOffsetZ = file.getInt(ConfigPath.HOLO_OFFSET_Z.path());
    }

    public int getHoloOffsetX() {
        return holoOffsetX;
    }

    public void setHoloOffsetX(int holoOffsetX) {
        this.holoOffsetX = holoOffsetX;
    }

    public int getHoloOffsetY() {
        return holoOffsetY;
    }

    public void setHoloOffsetY(int holoOffsetY) {
        this.holoOffsetY = holoOffsetY;
    }

    public int getHoloOffsetZ() {
        return holoOffsetZ;
    }

    public void setHoloOffsetZ(int holoOffsetZ) {
        this.holoOffsetZ = holoOffsetZ;
    }
}
