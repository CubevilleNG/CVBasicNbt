package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;


public class EntitySetOwner extends CommandWithEntity
{
    public EntitySetOwner() {
        super("entity set owner");
        setPermission("snbt.entity");
    }

    @Override
    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {
        return null;
    }
    
}
