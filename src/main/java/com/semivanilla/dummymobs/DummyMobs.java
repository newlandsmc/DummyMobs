package com.semivanilla.dummymobs;

import com.semivanilla.dummymobs.config.Configuration;
import com.semivanilla.dummymobs.manager.DummyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DummyMobs extends JavaPlugin {

    private static DummyMobs plugin;

    private Configuration configuration;
    private DummyManager dummyManager;

    @Override
    public void onEnable() {
        plugin = this;
        this.configuration = new Configuration(this);
        this.dummyManager = new DummyManager(this);

        if(!configuration.initConfig()){
            getLogger().severe("Unable to initialize config file. The plugin will be disabled!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        configuration.loadConfiguration();
    }

    @Override
    public void onDisable() {

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public DummyManager getDummyManager() {
        return dummyManager;
    }

    public static DummyMobs getPlugin() {
        return plugin;
    }
}
