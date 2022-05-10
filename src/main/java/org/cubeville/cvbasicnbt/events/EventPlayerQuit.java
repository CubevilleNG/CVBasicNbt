package org.cubeville.cvbasicnbt.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EventPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CommandMap.remove(event.getPlayer());
    }
}
