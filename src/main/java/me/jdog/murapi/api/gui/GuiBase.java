package me.jdog.murapi.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Muricans on 11/20/16.
 */
public abstract class GuiBase {
    /**
     * The inventory.
     */
    public Inventory inventory;

    /**
     *
     * @param name The name of the inventory.
     * @param size The size of the inventory.
     */
    public GuiBase(String name, int size) {
        inventory = Bukkit.createInventory(null, size, name);
        this.registerItems();
    }

    public abstract void registerItems();

    /**
     *
     * @param itemStack The item in the gui.
     * @param player The player who's clicking.
     */
    public abstract void click(ItemStack itemStack, Player player);

}
