package org.cubeville.cvbasicnbt.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EventPlayerInteract implements Listener {
    
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!CommandMap.isSelecting(player)) return;
        event.setCancelled(true);
			
        if(event.getClickedBlock() == null) return;
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(event.getHand() != EquipmentSlot.HAND) return;

        CommandMap.put(player, event.getClickedBlock());
        player.sendMessage(ColorUtils.addColor("&aBlock &6" + event.getClickedBlock().getType().name() + "&a selected!"));
        CommandMap.stopSelecting(player);
    }

}
