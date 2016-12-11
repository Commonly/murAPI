package me.jdog.murapi.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Muricans on 11/20/16.
 */
public class Packet {

    /**
     *
     * @param player The player to send the packet to.
     * @param packet The packet to send.
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnect = handle.getClass().getField("playerConnection").get(handle);
            playerConnect.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnect, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name Class name.
     * @return The class.
     */
    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
