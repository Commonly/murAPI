package me.jdog.murapi.api.config;

import me.jdog.murapi.events.ConfigCreateEvent;
import me.jdog.murapi.exceptions.InvalidFileException;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Muricans on 12/17/16.
 * <p>
 * Feel free to copy class
 *
 * @since 1.3-pre
 */

public class Config {
    public String configVersion = "0.3.0";
    private Plugin p;
    private File file;
    private FileConfiguration fileConfig;
    private String name;
    @Deprecated
    private Map<FileConfiguration, File> files = new HashMap<>();

    public Config(Plugin p, String name) {
        boolean exception = false;
        try {
            if (!name.endsWith(".yml")) {
                exception = true;
                throw new InvalidFileException();
            }
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        if (!exception) {
            this.p = p;
            this.name = name;
            file = new File(p.getDataFolder(), name);
            fileConfig = YamlConfiguration.loadConfiguration(file);
        }
    }

    /**
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
        ConfigCreateEvent configCreateEvent = new ConfigCreateEvent(name, p, this);
        Bukkit.getServer().getPluginManager().callEvent(configCreateEvent);
        try { // don't know if this will cause an exception.
            if (configCreateEvent.isCancelled()) return;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void reload() {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * @param path  The path to be selected.
     * @param value What to set the path to.
     */
    public void set(String path, Object value) {
        fileConfig.set(path, value);
        save();
    }

    /**
     * @param path  The path to be selected.
     * @param value What to set the path to.
     */
    public void addDefault(String path, Object value) {
        fileConfig.addDefault(path, value);
        fileConfig.options().copyDefaults(true);
        save();
    }

    /**
     * @param path The path to get the integer from.
     * @return The integer in that path.
     */
    public int getInt(String path) {
        return fileConfig.getInt(path);
    }

    /**
     * @param path The path to get the string from.
     * @return The string in that path.
     */
    public String getString(String path) {
        return fileConfig.getString(path);
    }

    /**
     * @param path The path to get the String List from.
     * @return The string list in that path.
     */
    public List<String> getStringList(String path) {
        return fileConfig.getStringList(path);
    }

    /**
     * @param path The path to get the boolean from.
     * @return The boolean in that path.
     */
    public boolean getBoolean(String path) {
        return fileConfig.getBoolean(path);
    }

    /**
     * @param path The path to get the object from.
     * @return The object in that path.
     */
    public Object get(String path) {
        return fileConfig.get(path);
    }

    /**
     * @param path Path to get defaults from.
     * @return Defaults from selected path.
     */
    public Configuration getDefaults(String path) {
        return fileConfig.getDefaults();
    }

    public ConfigurationOptions options() {
        OfflinePlayer player = Bukkit.getOfflinePlayer("x");
        if (!file.exists()) {
            create();
        }
        return fileConfig.options();
    }

    /**
     * @param path The path to get the long from.
     * @return The long from selected path.
     */
    public long getLong(String path) {
        return fileConfig.getLong(path);
    }

    /**
     * @param path The path to check.
     * @return
     */
    public boolean isSet(String path) {
        return fileConfig.isSet(path);
    }

    public FileConfiguration getFile() {
        if (!file.exists()) {
            create();
        }
        return fileConfig;
    }

    /**
     * @param path The path to get the color from.
     * @return The color in that path.
     */
    public Color getColor(String path) {
        return fileConfig.getColor(path);
    }

    public void saveResource(boolean set) {
        p.saveResource(name, set);
    }

    /**
     * You can make the YAML file in the resources folder and it'll load it from there.
     * Unfortunately this will not generate the comments you put in the YAML file.
     */
    public void loadFromJar() {
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(p.getResource(name), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                fileConfig.setDefaults(defConfig);
                defConfigStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
