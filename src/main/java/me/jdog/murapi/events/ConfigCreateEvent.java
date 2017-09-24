/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.murapi.events;

import me.jdog.murapi.api.config.Config;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

/**
 * Created by Muricans on 1/3/17.
 */
public class ConfigCreateEvent extends Event {

    private static HandlerList handlers = new HandlerList();

    private String configName;
    private Plugin plugin;
    private Config config;
    private boolean cancelled = false;

    public ConfigCreateEvent(String configName, Plugin plugin, Config config) {
        this.configName = configName;
        this.plugin = plugin;
        this.config = config;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getConfigName() {
        return configName;
    }

    public String getConfigClassVersion() {
        return config.configVersion;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
