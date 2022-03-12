package com.semivanilla.dummymobs.config;

import com.semivanilla.dummymobs.DummyMobs;
import com.semivanilla.dummymobs.abstracts.AbstractConfig;
import com.semivanilla.dummymobs.interfaces.PluginFiles;
import de.leonhard.storage.Config;

public class Configuration extends AbstractConfig implements PluginFiles {

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

    }
}
