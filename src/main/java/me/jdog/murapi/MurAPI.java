/*
 * Copyright (c) 2016
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi;

import com.connorlinfoot.bountifulapi.Actionbar;
import me.jdog.murapi.api.cmd.CMDManager;
import me.jdog.murapi.api.cmd.Mur;
import me.jdog.murapi.api.config.Config;
import me.jdog.murapi.api.gui.GuiManager;
import me.jdog.murapi.api.logger.LogType;
import me.jdog.murapi.api.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Created by Muricans on 11/19/16.
 */
public class MurAPI extends JavaPlugin {
    public static String sv_version = Bukkit.getServer().getVersion();
    /**
     * An instance of the class MurAPI.
     */
    private static MurAPI instance;
    public Config config = new Config(this, "config.yml");
    private Logger logger = Logger.getInstance();

    public static MurAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        config.create();
        config.addDefault("debug", true);
        if (config.getBoolean("debug")) {
            getLogger().log(Level.CONFIG, "Debug mode: TRUE");
        } else if (!config.getBoolean("debug")) {
            getLogger().log(Level.CONFIG, "Debug mode: FALSE");
        }

        Actionbar.enable();
        CMDManager.registerCommand(0, new Mur());
        getCommand("murapi").setExecutor(new CMDManager());
        getServer().getPluginManager().registerEvents(new GuiManager(), this);
        logger.log(LogType.INFO, "murAPI has been enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
