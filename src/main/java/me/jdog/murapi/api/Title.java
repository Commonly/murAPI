/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.api;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Created by Muricans on 11/20/16.
 */
public class Title {

    /**
     * @param player The player to send the title to.
     * @param text   The text of the title.
     * @param fadeI  Fade in.
     * @param time   The time it should stay.
     * @param fadeO  Fade out.
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
     * @param player The player to send the subtitle to.
     * @param text   The text of the subtitle.
     * @param fadeI  Fade in.
     * @param time   The time it should stay.
     * @param fadeO  Fade out.
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

    /**
     * @param player       The player to send the title and subtitle to.
     * @param titleText    The text of the title.
     * @param subtitleText The text of the subtitle.
     * @param fadeI        Fade in.
     * @param time         The time it should stay.
     * @param fadeO        Fade out.
     */
    public static void sendTitle(Player player, String titleText, String subtitleText, int fadeI, int time, int fadeO) {
        if (titleText != null && subtitleText != null) {
            sendTitle(player, titleText, fadeI, time, fadeO);
            sendSubtitle(player, subtitleText, fadeI, time, fadeO);
        } else if (titleText == null && subtitleText != null) {
            sendSubtitle(player, subtitleText, fadeI, time, fadeO);
        } else if (titleText != null && subtitleText == null) {
            sendTitle(player, titleText, fadeI, time, fadeO);
        }
    }

}
