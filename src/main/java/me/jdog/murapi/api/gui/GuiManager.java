package me.jdog.murapi.api.gui;

import me.jdog.murapi.MurAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muricans on 11/20/16.
 */
public class GuiManager implements Listener {
    public static Map<Integer, GuiBase> guiBaseMap = new HashMap<>();

    public GuiManager() {

    }

    /**
     *
     * @param id The ID of the GUI.
     * @return The GUI that has the ID specified.
     */
    public static GuiBase getGui(int id) {
        return guiBaseMap.get(id);
    }

    /**
     *
     * @param id The ID of the GUI.
     * @param guiBase The GUI.
     */
    public static void registerGui(int id, GuiBase guiBase) {
        guiBaseMap.put(id, guiBase);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            if(e.getInventory().getName() != null) {
                for (GuiBase g : guiBaseMap.values()) {
                    if(e.getInventory().getName().equals(g.inventory.getName())) {
                        e.setCancelled(true);
                        g.click(e.getCurrentItem(), (Player) e.getWhoClicked());
                    }
                }
            }
        }
    }

}
