package org.cubeville.cvbasicnbt.commands.entity;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvbasicnbt.commands.util.CommandMap;
import org.cubeville.cvbasicnbt.utils.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityRide extends Command {

    public EntityRide() {
        super("entity ride");
        setPermission("snbt.entity");
        addFlag("reverse");
        addFlag("stack");
        addFlag("unstack");
        addFlag("armorstand");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {

        if (checkMoreThanOne(flags.contains("reverse"), flags.contains("stack"), flags.contains("unstack"), flags.contains("armorstand"))) {
            throw new CommandExecutionException("Incompatible parameters.");
        }
        /*if (flags.contains("reverse") && !flags.contains("stack") && flags.contains("unstack")) {
            for (Entity e : player.getPassengers()) {
                player.removePassenger(e);
            }
            return null;
        }*/
        if(!CommandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        } else if(!(CommandMap.get(player) instanceof Entity)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        }

        Entity target = (Entity) CommandMap.get(player);

        if(flags.contains("reverse")) {
            if(player.getPassengers().isEmpty()) {
                player.addPassenger(target);
            } /*else if(player.getPassengers().get(player.getPassengers().size() - 1) != target) {
                player.getPassengers().get(player.getPassengers().size() - 1).addPassenger(target);
            }*/else {
                boolean lastOne = false;
                Entity e = player;
                while(!lastOne) {
                    e = e.getPassengers().get(e.getPassengers().size() - 1);
                    if(e.getPassengers().isEmpty()) {
                        lastOne = true;
                    }
                }
                if(e != target) {
                    e.addPassenger(target);
                }
            }
        } else if(flags.contains("stack")) {
            //TODO ?
        } else if(flags.contains("unstack")) {
            for (Entity e : target.getPassengers()) {
                target.removePassenger(e);
            }
        } else if(flags.contains("armorstand")) {
            ArmorStand armorStand = (ArmorStand) EntityUtils.getNearestEntity(player.getLocation(),
                    EntityUtils.getEntitiesByType(player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10), EntityType.ARMOR_STAND), 0);
            if(armorStand == null) throw new CommandExecutionException("No armor stand within 10 blocks radius found.");
            armorStand.addPassenger(target);
        } else {
            if(!target.getPassengers().contains(player)) {
                if(target.getPassengers().isEmpty()) {
                    target.addPassenger(player);
                } else {
                    target.getPassengers().get(target.getPassengers().size() - 1).addPassenger(player);
                }
            }
        }
        return new CommandResponse("&aEntity ride successfully executed!");
    }
}

