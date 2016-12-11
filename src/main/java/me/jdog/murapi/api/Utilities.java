package me.jdog.murapi.api;

import me.jdog.murapi.MurAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

/**
 * Created by Muricans on 12/9/16.
 */
public class Utilities {
    private static Utilities instance = new Utilities();
    private Utilities() {
    }
    public static Utilities getInstance() {
        return instance;
    }

    /**
     *
     * @param plugin The plugins main class.
     * @return The plugins version.
     */
    public String getVersion(Plugin plugin) {
        return plugin.getDescription().getVersion();
    }

    /**
     *
     * @return The server version.
     */
    public String getServerVersion() {
        return Bukkit.getServer().getVersion();
    }

    /**
     *
     * @return The bukkit version of the server.
     */
    public String getBukkitVersion() {
        return Bukkit.getServer().getBukkitVersion();
    }
}
