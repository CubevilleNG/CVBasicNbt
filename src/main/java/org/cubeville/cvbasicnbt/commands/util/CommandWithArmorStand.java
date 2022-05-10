package org.cubeville.cvbasicnbt.commands.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public abstract class CommandWithArmorStand extends Command {

    public CommandWithArmorStand(String command) {
        super(command);
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        if(CommandMap.contains(player) == false || !(CommandMap.get(player) instanceof ArmorStand))
            throw new CommandExecutionException("Please select an armor stand!");

        ArmorStand armorstand = (ArmorStand) CommandMap.get(player);

        return executeWithArmorStand(player, flags, parameters, baseParameters, armorstand);
    }

    public abstract CommandResponse executeWithArmorStand(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ArmorStand armorstand)
        throws CommandExecutionException;
    
}
    
