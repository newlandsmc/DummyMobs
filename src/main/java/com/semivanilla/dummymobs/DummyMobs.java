package com.semivanilla.dummymobs;

import com.semivanilla.dummymobs.commands.DummyCommand;
import com.semivanilla.dummymobs.config.Configuration;
import com.semivanilla.dummymobs.listener.EntityDamageListener;
import com.semivanilla.dummymobs.listener.PlayerQuitListener;
import com.semivanilla.dummymobs.manager.DummyManager;
import com.semivanilla.dummymobs.model.Dummies;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DummyMobs extends JavaPlugin {

    private static DummyMobs plugin;

    private Configuration configuration;
    private DummyManager dummyManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        plugin = this;
        this.configuration = new Configuration(this);
        this.dummyManager = new DummyManager(this);
        /*
        if(!configuration.initConfig()){
            getLogger().severe("Unable to initialize config file. The plugin will be disabled!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        configuration.loadConfiguration();
         */

        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        commandManager.register(new DummyCommand(this));

    }

    @Override
    public void onDisable() {
        for(Dummies dummy : dummyManager.getAllDummies()){
            dummy.despawn();
        }
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
