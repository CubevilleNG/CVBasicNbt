package org.cubeville.cvbasicnbt.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public abstract class CommandWithItem extends Command {

    public CommandWithItem(String command) {
        super(command);
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        ItemStack item = player.getInventory().getItemInMainHand();
        if(item == null || item.getType() == Material.AIR)
            throw new CommandExecutionException("Must be holding an item!");

        return executeWithItem(player, flags, parameters, baseParameters, item);
    }

    public abstract CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException;
    
}
