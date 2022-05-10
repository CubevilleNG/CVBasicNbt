package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class EntityMoveHere extends CommandWithEntity
{

    public EntityMoveHere() {
        super("entity move here");
        setPermission("snbt.entity");
    }

    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        if(!(sender instanceof Player)) throw new CommandExecutionException("Command can not be used on console.");
        Player player = (Player)sender;
        
        entity.teleport(player.getLocation());

        return new CommandResponse("&aEntity moved.");
    }
}
