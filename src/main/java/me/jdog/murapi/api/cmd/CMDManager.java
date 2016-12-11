package me.jdog.murapi.api.cmd;

import me.jdog.murapi.MurAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muricans on 12/7/16.
 */
public class CMDManager implements CommandExecutor {
    public static Map<Integer, CMD> cmdBaseMap = new HashMap<>();

    public CMDManager() {

    }


    /**
     *
     * @param id The ID of the command.
     * @return Return the command that holds the specified ID
     */
    public static CMD getCMD(int id) {
        return cmdBaseMap.get(id);
    }


    /**
     *
     * @param id ID to get that command.
     * @param cmd The command.
     */
    public static void registerCommand(int id, CMD cmd) {
        cmdBaseMap.put(id, cmd);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(CMD c : cmdBaseMap.values()) {
            if(command.getName().equalsIgnoreCase(c.getName())) {
                c.execute(sender, command, label, args);
                return true;
            }
        }
        return true;
    }
}
