package org.cubeville.cvbasicnbt.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventArmorStandBreak implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onArmorStandBreak(EntityDamageEvent event) {
        if(event.getEntity().isInvulnerable()) {
            event.setCancelled(true);
        } else if(event.getEntityType() == EntityType.ARMOR_STAND && !((ArmorStand) event.getEntity()).isVisible()) {
            event.setCancelled(true);
        }
    }
}
