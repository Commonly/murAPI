package me.jdog.murapi.api.cmd;

import me.jdog.murapi.api.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Muricans on 12/7/16.
 */
public abstract class CMD {
    private String name;

    /**
     *
     * @param name The commands name.
     */
    public CMD(String name) {
       this.name = name;
    }

    /**
     *
     * @return Return the name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The commands name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param sender The sender of the command.
     * @param command The command itself.
     * @param label The commands label.
     * @param args The commands args.
     * @return True or false
     */
    public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);

}
