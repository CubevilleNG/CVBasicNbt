package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterString;

import org.cubeville.commons.utils.BlockUtils;

public class EntityBulkBoolean extends Command {
    public EntityBulkBoolean() {
        super("entity bulk");
        setPermission("snbt.entity");

        addBaseParameter(new CommandParameterEnumeratedString("silent", "invulnerable", "glow", "gravity", "visualfire", "autoremove"));
        addOptionalBaseParameter(new CommandParameterBoolean());

        addParameter("r", false, new CommandParameterString());
        addParameter("type", false, new CommandParameterEnum(EntityType.class));
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        String action = (String) baseParameters.get(0);

        boolean parameter = true;
        if(baseParameters.size() == 2) parameter = (boolean) baseParameters.get(1);

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

        List<Entity> entities;
        if(we)
            entities = BlockUtils.getEntitiesInWESelection(player);
        else
            entities = BlockUtils.getEntitiesWithinRadius(player.getLocation(), radius);
        entities = BlockUtils.filterEntitiesByType(entities, type);

        if(entities.size() == 0) {
            throw new CommandExecutionException("No entities found.");
        }
        
        int cnt = 0;
        for(Entity entity: entities) {
            cnt++;
            if(action.equals("silent"))
                entity.setSilent(parameter);
            else if(action.equals("invulnerable"))
                entity.setInvulnerable(parameter);
            else if(action.equals("glow"))
                entity.setGlowing(parameter);
            else if(action.equals("gravity"))
                entity.setGravity(parameter);
            else if(action.equals("visualfire"))
                entity.setVisualFire(parameter);
        }

        return new CommandResponse("&a" + cnt + " entities changed.");
    }
}
