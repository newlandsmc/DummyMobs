package com.semivanilla.dummymobs.commands;

import com.semivanilla.dummymobs.DummyMobs;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Command("dummy")
public class DummyCommand extends CommandBase {

    private final DummyMobs plugin;

    public DummyCommand(DummyMobs plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onDummyCommand(final Player player){
        if(!plugin.getDummyManager().hasDummy(player)){
            plugin.getDummyManager().spawn(player);
        }else {
            plugin.getDummyManager().getIfPresent(player).get().updateLocation(player.getLocation());
        }
    }

    @SubCommand("leave")
    public void onCommandLeave(final Player player){
        if(!plugin.getDummyManager().hasDummy(player)){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou don't own a dummy!"));
            return;
        }

        plugin.getDummyManager().remove(player);
    }
}
