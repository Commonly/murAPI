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

import me.jdog.murapi.MurAPI;
import me.jdog.murapi.api.Color;
import me.jdog.murapi.api.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Muricans on 12/7/16.
 */
public class Mur implements CMD {

    @Override
    public String getName() {
        return "murapi";
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args.length == 1) {
            sender.sendMessage(Color.addColor("&c[mur] &amurAPI version: " + Utilities.getInstance().getVersion(MurAPI.getInstance()) + " &dBukkit/Spigot version: " + MurAPI.sv_version));
            return true;
        }

        if (args[0].equalsIgnoreCase("debug")) {
            if (args[1].equalsIgnoreCase("true")) {
                MurAPI.getInstance().config.set("debug", true);
                sender.sendMessage(Color.addColor("&cDebug mode: &7TRUE &fYou will now receive debug alerts from murAPI"));
                return true;
            } else if (args[1].equalsIgnoreCase("false")) {
                MurAPI.getInstance().config.set("debug", false);
                sender.sendMessage(Color.addColor("&cDebug mode: &7FALSE &fYou will no longer receive debug alerts from murAPI"));
                return true;
            } else if (args[1].equalsIgnoreCase("status")) {
                if (MurAPI.getInstance().config.getBoolean("debug")) {
                    sender.sendMessage(Color.addColor("&cDebug status: &7TRUE &fYou will get console alerts from murAPI"));
                } else if (!MurAPI.getInstance().config.getBoolean("debug")) {
                    sender.sendMessage(Color.addColor("&cDebug status: &7FALSE &fYou will not get console alerts from murAPI"));
                }
                return true;
            } else {
                sender.sendMessage(Color.addColor("&cInvalid usage of 'debug' &7Correct usage: &f/murapi debug [true/false/status]"));
                return true;
            }
        }
        return false;
    }
}
