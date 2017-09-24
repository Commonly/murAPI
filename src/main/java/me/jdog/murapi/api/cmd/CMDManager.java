/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.api.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muricans on 12/7/16.
 */
public class CMDManager implements CommandExecutor {
    private static List<CMD> cmdBaseMap = new ArrayList<>();

    public CMDManager() {

    }

    /**
     * Side notes: You no longer need to do mainclass.getCommand(cmdName).setExecutor(new CMDManager());
     * @param cmd The command.
     * @param plugin Class extending JavaPlugin.
     */
    public static void registerCommand(CMD cmd, JavaPlugin plugin) {
        cmdBaseMap.add(cmd);
        plugin.getCommand(cmd.getName()).setExecutor(new CMDManager());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (CMD c : cmdBaseMap) {
            if (command.getName().equalsIgnoreCase(c.getName())) {
                c.execute(sender, command, label, args);
                return true;
            }
        }
        return true;
    }
}
