package me.jdog.murapi.api;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Created by Muricans on 11/19/16.
 */
public class Color {
    /**
     *
     * @param text The text to add color.
     * @return Returns the text with color.
     */
    public static String addColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     *
     * @param text The line of the config to add color.
     * @param plugin The plugins main class.
     * @return
     */
    public static String addColor(String text, Plugin plugin) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(text));
    }
}
