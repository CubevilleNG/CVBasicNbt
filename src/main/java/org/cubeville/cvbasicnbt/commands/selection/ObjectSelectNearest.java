package org.cubeville.cvbasicnbt.commands.selection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;

import org.cubeville.cvbasicnbt.CVBasicNbt;
import org.cubeville.cvbasicnbt.utils.EntityUtils;
import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class ObjectSelectNearest extends Command {

    public ObjectSelectNearest() {
        super("select nearest");
        setPermission("snbt.selection");
        addParameter("entity", true, new CommandParameterEnum(EntityType.class));
        addParameter("block", true, new CommandParameterEnum(Material.class));
        addParameter("radius", true, new CommandParameterInteger());
        addParameter("next", true, new CommandParameterInteger());
        addFlag("glow");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
                                   List<Object> baseParameters) throws CommandExecutionException {
        int radius = 10;
		
        if (parameters.containsKey("radius")) {
            radius = (int) parameters.get("radius");
        }
		
        if (radius > 25) {
            throw new CommandExecutionException("&cA radius of &6" + radius + " &cis too large. Please use an integer below 25!");
        }
		
        CommandResponse cr = new CommandResponse();
		
        if (parameters.containsKey("entity") && !parameters.containsKey("block")) {
            if(parameters.get("entity") == EntityType.PLAYER && (!(player.hasPermission("snbt.selection.player")))) 
                throw new CommandExecutionException("&cNo permission to select players.");
            List<Entity> entities = EntityUtils.filterEntityByUUID(player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius), player.getUniqueId());
            int next = parameters.containsKey("next") ? (Integer) parameters.get("next") : 0;
            Entity entity = EntityUtils.getNearestEntity(player.getLocation(), EntityUtils.getEntitiesByType(entities, (EntityType) parameters.get("entity")), next);
            if (entity != null) {
                if(flags.contains("glow")) {
                    entity.setGlowing(true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            entity.setGlowing(false);
                        }
                    }.runTaskLater(CVBasicNbt.getInstance(), 20);
                }
                CommandMap.put(player, entity);
                if (entity.getCustomName() != null) {
                    cr.setBaseMessage("&aEntity &6" + entity.getCustomName() + " &aselected at location " + entity.getLocation().getBlockX() + "," + entity.getLocation().getBlockY() + "," + entity.getLocation().getBlockZ());
                } else {
                    cr.setBaseMessage("&aEntity &6" + entity.getName() + " &aselected at location " + entity.getLocation().getBlockX() + "," + entity.getLocation().getBlockY() + "," + entity.getLocation().getBlockZ());
                }
            } else {
                throw new CommandExecutionException("&cNo entities in radius of &6" + radius);
            }
        } else if (!parameters.containsKey("entity") && parameters.containsKey("block")) {
            Block block = BlockUtils.getNearestBlock(BlockUtils.getBlocksByType(BlockUtils.getBlocksInRadius(player.getLocation(), radius), (Material) parameters.get("block")), player.getLocation());
            if (block != null) {
                CommandMap.put(player, block);
                cr.setBaseMessage("&aEntity &6" + block.getType() + " &aselected at location " + block.getX() + "," + block.getY() + "," + block.getZ());
            } else {
                throw new CommandExecutionException("&cNo entities in radius of &6" + radius);
            }
        } else {
            throw new CommandExecutionException("&cPlease select a block or entity!");
        }
		
        return cr;
    }

}
