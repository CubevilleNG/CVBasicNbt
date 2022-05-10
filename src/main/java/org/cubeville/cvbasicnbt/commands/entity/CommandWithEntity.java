package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.commands.CommandParameterUUID;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public abstract class CommandWithEntity extends BaseCommand {

    public CommandWithEntity(String command) {
        super(command);
        addParameter("uuid", true, new CommandParameterUUID());
    }

    @Override
    public CommandResponse execute(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Entity entity;
        boolean selectedByUUID = false;
        
        if(parameters.get("uuid") != null) {
            UUID uuid = (UUID) parameters.get("uuid");
            entity = Bukkit.getEntity(uuid);
            if(entity == null)
                throw new CommandExecutionException("No entity with that UUID found!");
            selectedByUUID = true;
        }
        else {
            if(!(sender instanceof Player))
                throw new CommandExecutionException("Can't be used on console without UUID parameter!");
            Player player = (Player) sender;
            if(CommandMap.contains(player) && CommandMap.get(player) instanceof Entity)
                entity = (Entity) CommandMap.get(player);
            else
                throw new CommandExecutionException("Please select an entity!");
        }
        
        return executeWithEntity(sender, flags, parameters, baseParameters, entity, selectedByUUID);
    }

    public abstract CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException;

}
