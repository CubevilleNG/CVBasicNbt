package org.cubeville.cvbasicnbt.commands.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public abstract class CommandWithMob extends Command {

    public CommandWithMob(String command) {
        super(command);
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        if(CommandMap.contains(player) == false || !(CommandMap.get(player) instanceof LivingEntity))
            throw new CommandExecutionException("Please select a mob!");

        LivingEntity mob = (LivingEntity) CommandMap.get(player);

        return executeWithMob(player, flags, parameters, baseParameters, mob);
    }

    public abstract CommandResponse executeWithMob(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, LivingEntity mob)
        throws CommandExecutionException;
    
}
    
