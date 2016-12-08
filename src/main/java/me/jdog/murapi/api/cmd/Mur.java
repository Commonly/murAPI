package me.jdog.murapi.api.cmd;

import me.jdog.murapi.MurAPI;
import me.jdog.murapi.api.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Muricans on 12/7/16.
 */
public class Mur extends CMD {

    public Mur() {
        super("murapi");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Color.addColor("&c[mur] &amurAPI version: " + MurAPI.getInstance().getDescription().getVersion() + " &dBukkit/Spigot version: " + MurAPI.sv_version));
        return false;
    }
}
