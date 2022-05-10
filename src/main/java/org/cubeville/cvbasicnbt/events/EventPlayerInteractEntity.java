package org.cubeville.cvbasicnbt.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EventPlayerInteractEntity implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.isCancelled()) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getRightClicked() instanceof Player) return;
        
        Player player = event.getPlayer();
        if(!CommandMap.isSelecting(player)) return;
        
        event.setCancelled(true);

        Entity entity = event.getRightClicked();
        CommandMap.put(player, entity);
        CommandMap.stopSelecting(player);
        
        if (entity.getCustomName() != null) {
            event.getPlayer().sendMessage(ColorUtils.addColor("&aMob &6" + entity.getCustomName() + "&a selected!"));
        } else {
            event.getPlayer().sendMessage(ColorUtils.addColor("&aMob &6" + entity.getName() + "&a selected!"));
        }

    }
}
