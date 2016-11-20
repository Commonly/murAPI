package me.jdog.murapi.api;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Created by Muricans on 11/19/16.
 */
public class Color {
    public static String addColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String addColor(String text, Plugin plugin) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(text));
    }
}
