/*
 * Copyright (c) 2016 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.api.gui;

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
    private static Map<String, GuiBase> guiBaseMap = new HashMap<>();

    public GuiManager() {

    }

    /**
     * @param id The ID of the GUI.
     * @return The GUI that has the ID specified.
     */
    public static GuiBase getGui(String id) {
        return guiBaseMap.get(id);
    }

    /**
     * @param id The ID of the GUI.
     * @param guiBase The GUI.
     */
    public static void registerGui(String id, GuiBase guiBase) {
        guiBaseMap.put(id, guiBase);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            if (e.getInventory().getName() != null) {
                for (GuiBase g : guiBaseMap.values()) {
                    if (e.getInventory().getName().equals(g.inventory.getName())) {
                        e.setCancelled(true);
                        g.click(e.getCurrentItem(), (Player) e.getWhoClicked());
                    }
                }
            }
        }
    }

}
