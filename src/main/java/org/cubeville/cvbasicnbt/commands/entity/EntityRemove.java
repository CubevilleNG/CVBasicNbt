package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class EntityRemove extends CommandWithEntity
{

    public EntityRemove() {
        super("entity remove");
        setPermission("snbt.entity");
    }

    @Override
    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        entity.remove();
        if(sender instanceof Player) {
            CommandMap.remove((Player) sender);
        }
        
        return new CommandResponse("&aEntity removed.");
    }
}
