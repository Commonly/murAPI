package me.jdog.murapi.api.cmd;

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

    public static CMD getCMD(int id) {
        return cmdBaseMap.get(id);
    }

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
