/*
* This is free to add to your projects
*/

package me.jdog.murapi.api.config;

import me.jdog.murapi.MurAPI;
import org.bukkit.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Muricans on 12/17/16.
 */
public class Config {
    private Plugin p;
    private File file;
    private FileConfiguration fileConfig;

    private String name;
    private Map<FileConfiguration, File> files = new HashMap<>();

    public Config(Plugin p, String name) {
        this.p = p;
        this.name = name;
        file = new File(p.getDataFolder(), name);
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    /**
     *
     * @return The current config.
     */
    @Deprecated
    public FileConfiguration get() {
        if (!file.exists()) {
            create();
        }
        save(file, fileConfig);
        files.put(fileConfig, file);
        return fileConfig;
    }

    public void create() {
        if (!file.exists()) {
            try {
                p.getDataFolder().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        files.put(fileConfig, file);
        save(file, fileConfig);
    }

    @Deprecated
    public void save(FileConfiguration fileConfig) {
        save(files.get(fileConfig), fileConfig);
    }

    @Deprecated
    private void save(File file, FileConfiguration fileConfig) {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path The path to be selected.
     * @param value What to set the path to.
     */
    public void set(String path, Object value) {
        fileConfig.set(path, value);
        save();
    }

    /**
     *
     * @param path The path to be selected.
     * @param value What to set the path to.
     */
    public void addDefault(String path, Object value) {
        fileConfig.addDefault(path, value);
        fileConfig.options().copyDefaults(true);
        save();
    }

    /**
     *
     * @param path The path to get the integer from.
     * @return The integer in that path.
     */
    public int getInt(String path) {
        return fileConfig.getInt(path);
    }

    /**
     *
     * @param path The path to get the string from.
     * @return The string in that path.
     */
    public String getString(String path) {
        return fileConfig.getString(path);
    }

    /**
     *
     * @param path The path to get the String List from.
     * @return The string list in that path.
     */
    public List<String> getStringList(String path) {
        return fileConfig.getStringList(path);
    }

    /**
     *
     * @param path The path to get the boolean from.
     * @return The boolean in that path.
     */
    public boolean getBoolean(String path) {
        return fileConfig.getBoolean(path);
    }

    /**
     *
     * @param path The path to get the object from.
     * @return The object in that path.
     */
    public Object get(String path) {
        return fileConfig.get(path);
    }

    /**
     *
     * @param path Path to get defaults from.
     * @return Defaults from selected path.
     */
    public Configuration getDefaults(String path) {
        return fileConfig.getDefaults();
    }

    public ConfigurationOptions options() {
        return fileConfig.options();
    }

    /**
     *
     * @param path The path to get the long from.
     * @return The long from selected path.
     */
    public long getLong(String path) {
        return fileConfig.getLong(path);
    }

    /**
     *
     * @param path The path to check.
     * @return
     */
    public boolean isSet(String path) {
        return fileConfig.isSet(path);
    }

    public FileConfiguration getFile() {
        if(!file.exists()) {
            create();
        }
        return fileConfig;
    }

    /**
     *
     * @param path The path to get the color from.
     * @return The color in that path.
     */
    public Color getColor(String path) {
        return fileConfig.getColor(path);
    }

    public void saveResource(boolean set) {
        p.saveResource(name, set);
    }

}
