package com.semivanilla.dummymobs.config;

import com.semivanilla.dummymobs.ConfigPath;
import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.abstracts.AbstractConfig;
import com.semivanilla.dummymobs.interfaces.PluginFiles;
import de.leonhard.storage.Config;

public class Configuration extends AbstractConfig implements PluginFiles {

    private float holoOffsetX,holoOffsetY,holoOffsetZ;

    public Configuration(DummyMobs plugin) {
        super(plugin);
    }

    @Override
    public boolean initConfig() {
        this.file = new Config("config.yml",plugin.getDataFolder().getPath(), getResource("config.yml"));
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
        this.holoOffsetX = file.getFloat(ConfigPath.HOLO_OFFSET_X.path());
        this.holoOffsetY = file.getFloat(ConfigPath.HOLO_OFFSET_Y.path());
        this.holoOffsetZ = file.getFloat(ConfigPath.HOLO_OFFSET_Z.path());

    }

    public float getHoloOffsetX() {
        return holoOffsetX;
    }

    public float getHoloOffsetY() {
        return holoOffsetY;
    }

    public float getHoloOffsetZ() {
        return holoOffsetZ;
    }

}
