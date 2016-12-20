package me.jdog.murapi.api.config;

import me.jdog.murapi.MurAPI;
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
    private String name;

    public Config(Plugin p, String name) {
        this.p = p;
        this.name = name;
    }

    private Map<FileConfiguration, File> files = new HashMap<>();

    public FileConfiguration get() {
        File file = new File(p.getDataFolder(), name);
        if(!file.exists()) {
            create();
        }
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
        save(file, fileConfig);
        files.put(fileConfig, file);
        return fileConfig;
    }

    public void create() {
        File file = new File(p.getDataFolder(), name);
        if(!file.exists()) {
            try {
                p.getDataFolder().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
        files.put(fileConfig, file);
        save(file, fileConfig);
    }

    public void save(FileConfiguration fileConfig) {
        save(files.get(fileConfig), fileConfig);
    }

    private void save(File file, FileConfiguration fileConfig) {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        FileConfiguration fileConfiguration = get();
        fileConfiguration.set(path, value);
        save(fileConfiguration);
    }

    public void addDefault(String path, Object value) {
        FileConfiguration fileConfiguration = get();
        fileConfiguration.addDefault(path, value);
        fileConfiguration.options().copyDefaults(true);
        save(fileConfiguration);
    }

    public int getInt(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getInt(path);
    }

    public String getString(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getString(path);
    }

    public List<String> getStringList(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getStringList(path);
    }

    public boolean getBoolean(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getBoolean(path);
    }

    public Object get(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.get(path);
    }

    public Configuration getDefaults(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getDefaults();
    }

    public ConfigurationOptions options() {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.options();
    }

    public long getLong(String path) {
        FileConfiguration fileConfiguration = get();
        return fileConfiguration.getLong(path);
    }

}
