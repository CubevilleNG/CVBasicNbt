package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.commands.CommandParameterVector;

public class EntityMove extends CommandWithEntity
{

    public EntityMove() {
        super("entity move");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterVector());
    }

    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        Location location = entity.getLocation();
        location = location.add((Vector) baseParameters.get(0));
        entity.teleport(location);

        return new CommandResponse("&aEntity moved.");
    }
}
