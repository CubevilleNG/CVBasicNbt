package org.cubeville.cvbasicnbt.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.hanging.HangingBreakEvent;


import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EventEntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        removeEntity(event.getEntity());
    }
    
    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        removeEntity(event.getEntity());
    }
    
    @EventHandler
    public void onHangingBreak(HangingBreakEvent event) {
        removeEntity(event.getEntity());
    }
    
    public void removeEntity(Entity entity) {
        CommandMap.removeObject(entity);
    }
}
