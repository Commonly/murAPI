/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.api;

import me.jdog.murapi.api.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Created by Muricans on 11/19/16.
 */
public class Color {
    /**
     * @param input The text to add color.
     * @return Returns the text with color.
     */
    public static String addColor(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * @param input  The line of the config to add color.
     * @param plugin The plugins main class.
     * @return The line from config with color.
     */
    @Deprecated
    public static String addColor(String input, Plugin plugin) {
        return addColor(plugin.getConfig().getString(input));
    }

    /**
     *
     * @param input The line of the config to add color.
     * @param config The config.
     * @return The line from config with color.
     */
    public static String addColor(String input, Config config) {
        return addColor(config.getString(input));
    }

    /**
     *
     * @param input The line of the config to add color.
     * @param fileConfiguration The config.
     * @return The line from config with color.
     */
    public static String addColor(String input, FileConfiguration fileConfiguration) {
        return addColor(fileConfiguration.getString(input));
    }

    /**
     * @param input The string to strip.
     * @return The string w/o color.
     */
    public static String strip(String input) {
        return ChatColor.stripColor(input);
    }

}
