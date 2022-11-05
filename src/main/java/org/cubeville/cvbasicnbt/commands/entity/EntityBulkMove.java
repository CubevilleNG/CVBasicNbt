package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.util.Vector;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterVector;
import org.cubeville.commons.commands.CommandParameterString;

import org.cubeville.commons.utils.BlockUtils;

public class EntityBulkMove extends Command
{
    public EntityBulkMove() {
        super("entity bulk move");
        setPermission("snbt.entity");

        addBaseParameter(new CommandParameterVector());
        
        addParameter("r", false, new CommandParameterString());
        addParameter("type", false, new CommandParameterEnum(EntityType.class));
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Location loc = player.getLocation();
        boolean we = false;
        double radius = 0.0;
        {
            String r = (String) parameters.get("r");
            if(r.equals("we")) {
                we = true;
            }
            else {
                radius = Double.valueOf(r);
            }
        }

        EntityType type = (EntityType) parameters.get("type");

        Vector mv = (Vector) baseParameters.get(0);

        Vector min = null;
        Vector max = null;
        if(we) {
            min = BlockUtils.getWESelectionMin(player).toVector();
            max = BlockUtils.getWESelectionMax(player).toVector();
        }
        
        int cnt = 0;
        for(Entity e: loc.getWorld().getEntities()) {
            if(e.getType() != type) continue;
            Location l = e.getLocation();
            if(we == false && l.distance(loc) > radius) continue;
            if(we == true && l.toVector().isInAABB(min, max) == false) continue;
            cnt++;
            l.add(mv);
            e.teleport(l);
        }

        return new CommandResponse("&a" + cnt + " entities of type " + type + " moved.");
    }
}
