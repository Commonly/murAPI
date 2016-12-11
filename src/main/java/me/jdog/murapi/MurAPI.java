package me.jdog.murapi;

import com.connorlinfoot.bountifulapi.Actionbar;
import me.jdog.murapi.api.cmd.CMDManager;
import me.jdog.murapi.api.cmd.Mur;
import me.jdog.murapi.api.gui.GuiManager;
import me.jdog.murapi.api.logger.LogType;
import me.jdog.murapi.api.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Muricans on 11/19/16.
 */
public class MurAPI extends JavaPlugin {
    /**
     * An instance of the class MurAPI.
     */
    private static MurAPI instance;
    public static MurAPI getInstance() {
        return instance;
    }

    public static String sv_version = Bukkit.getServer().getVersion();
    private Logger logger = Logger.getInstance();

    @Override
    public void onEnable() {
        instance = this;
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
