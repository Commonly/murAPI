package me.jdog.murapi.api;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Created by Muricans on 11/20/16.
 */
public class Title {

    /**
     *
     * @param player The player to send the title to.
     * @param text The text of the title.
     * @param fadeI Fade in.
     * @param time The time it should stay.
     * @param fadeO Fade out.
     */
    public static void sendTitle(Player player, String text, int fadeI, int time, int fadeO) {
        try {
            Object e = Packet.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = Packet.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + Color.addColor(text) + "\"}");
            Constructor<?> title = Packet.getNMSClass("PacketPlayOutTitle").getConstructor(Packet.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packet.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = title.newInstance(e, titleChat, fadeI, time, fadeO);
            Packet.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param player The player to send the subtitle to.
     * @param text The text of the subtitle.
     * @param fadeI Fade in.
     * @param time The time it should stay.
     * @param fadeO Fade out.
     */
    public static void sendSubtitle(Player player, String text, int fadeI, int time, int fadeO) {
        try {
            Object e = Packet.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object titleChat = Packet.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + Color.addColor(text) + "\"}");
            Constructor<?> title = Packet.getNMSClass("PacketPlayOutTitle").getConstructor(Packet.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packet.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = title.newInstance(e, titleChat, fadeI, time, fadeO);
            Packet.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
