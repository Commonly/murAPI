package me.jdog.murapi;

import me.jdog.murapi.api.Color;
import me.jdog.murapi.api.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Muricans on 11/19/16.
 */
public class MurAPI extends JavaPlugin {
    private String version = getDescription().getVersion();
    private String sv_version = Bukkit.getServer().getVersion();
    private Logger logger = this.getLogger();

    public void onEnable() {
        logger.info("MurAPI has been enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("murapi")) {
            sender.sendMessage(Color.addColor("&c[mur] &amurAPI version: " + version + " &dBukkit/Spigot version: " + sv_version));
            return true;
        }
        return false;
    }
}
