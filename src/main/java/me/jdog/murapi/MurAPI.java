package me.jdog.murapi;

import me.jdog.murapi.api.Color;
import me.jdog.murapi.api.cmd.CMD;
import me.jdog.murapi.api.cmd.CMDManager;
import me.jdog.murapi.api.cmd.Mur;
import me.jdog.murapi.api.gui.GuiManager;
import me.jdog.murapi.api.logger.LogType;
import me.jdog.murapi.api.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Muricans on 11/19/16.
 */
public class MurAPI extends JavaPlugin {
    private static MurAPI instance;
    public static MurAPI getInstance() {
        return instance;
    }

    public static String sv_version = Bukkit.getServer().getVersion();
    private Logger logger = Logger.getInstance();

    @Override
    public void onEnable() {
        instance = this;
        CMDManager.registerCommand(0, new Mur());
        for(CMD c : CMDManager.cmdBaseMap.values()) {
            getCommand(c.getName()).setExecutor(new CMDManager());
        }
        getServer().getPluginManager().registerEvents(new GuiManager(), this);
        logger.log(LogType.INFO, "murAPI has been enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
