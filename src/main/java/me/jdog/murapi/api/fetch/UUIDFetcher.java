/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.api.fetch;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Josh on 2/4/17.
 *
 * Side notes: Taken from MSG v2.5
 */
public class UUIDFetcher {
    private String playerName;

    /**
     * @param playerName The player name to get UUID from.
     */
    public UUIDFetcher(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the players UUID.
     *
     * @return The UUID of the player.
     */
    public UUID getUUID() {
        return Bukkit.getServer().getOfflinePlayer(playerName).getUniqueId();
    }

    /**
     * Get the players UUID in string format.
     *
     * @return The UUID of the player in string format.
     */
    public String getUUIDAsString() {
        return getUUID().toString();
    }

    /**
     * Get the players name.
     *
     * @return The players name from the string inputted.
     */
    public String getPlayerName() {
        return getOfflinePlayerByString().getName();
    }

    /**
     * Check if the player has joined before, this method uses the players uuid and not their name.
     *
     * @return UUID has played before or not.
     */
    public boolean hasUUIDJoined() {
        return getOfflinePlayerByUUID().hasPlayedBefore();
    }

    /**
     * Check if the player has joined before, this method uses the players name and not their uuid.
     *
     * @return Player has played before or not.
     */
    public boolean hasPlayerJoined() {
        return getOfflinePlayerByString().hasPlayedBefore();
    }

    /**
     * Get the players name.
     *
     * @return The players name from the uuid of the string inputted.
     */
    public String getPlayerNameByUUID() {
        return getOfflinePlayerByUUID().getName();
    }

    /**
     * Get the player.
     *
     * @return The player from the UUID from the string inputted.
     */
    public OfflinePlayer getOfflinePlayerByUUID() {
        return Bukkit.getServer().getOfflinePlayer(getUUID());
    }

    /**
     * Get the player.
     *
     * @return The player from the string inputted.
     */
    public OfflinePlayer getOfflinePlayerByString() {
        return Bukkit.getServer().getOfflinePlayer(playerName);
    }

    public Player getPlayerByUUID() {
        return Bukkit.getServer().getPlayer(getUUID()).getPlayer();
    }

    public Player getPlayerByString() {
        return Bukkit.getServer().getPlayer(playerName).getPlayer();
    }

}
