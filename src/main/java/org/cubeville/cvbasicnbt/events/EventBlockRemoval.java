package org.cubeville.cvbasicnbt.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EventBlockRemoval implements Listener {
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        removeBlock(event.getBlock());
    }
    
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        removeBlock(event.getBlock());
    }
    
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        removeBlock(event.getBlock());
    }
    
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        removeBlock(event.getBlock());
    }
    
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        removeBlock(event.getBlock());
    }
    
    public void removeBlock(Block block) {
        if(CommandMap.containsObject(block)) {
            for (Player player: Bukkit.getOnlinePlayers()) {
                if(CommandMap.get(player) != null && CommandMap.get(player).equals(block)) {
                    player.sendMessage(ColorUtils.addColor("&cSelected block has been removed! Entity deselected."));
                }
            }
            CommandMap.removeObject(block);
        }
    }
}
