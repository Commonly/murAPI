/*
 * Copyright (c) 2016 Josh
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
     * @param id The ID of the command.
     * @return Return the command that holds the specified ID
     */
    public static CMD getCMD(int id) {
        return cmdBaseMap.get(id);
    }


    /**
     * @param id  ID to get that command.
     * @param cmd The command.
     */
    public static void registerCommand(int id, CMD cmd) {
        cmdBaseMap.put(id, cmd);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (CMD c : cmdBaseMap.values()) {
            if (command.getName().equalsIgnoreCase(c.getName())) {
                c.execute(sender, command, label, args);
                return true;
            }
        }
        return true;
    }
}
