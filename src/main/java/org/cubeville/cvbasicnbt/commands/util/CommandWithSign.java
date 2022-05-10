package org.cubeville.cvbasicnbt.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.block.Sign;
import org.bukkit.block.Block;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public abstract class CommandWithSign extends Command {

    public CommandWithSign(String command) {
        super(command);
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        if(CommandMap.contains(player) == false || !(CommandMap.get(player) instanceof Block))
            throw new CommandExecutionException("Please select a sign!");

        Block block = (Block) CommandMap.get(player);

        if(!(block.getState() instanceof Sign))
            throw new CommandExecutionException("Please select a sign!");

        Sign sign = (Sign) block.getState();
        CommandResponse response = executeWithSign(player, flags, parameters, baseParameters, sign);
        sign.update();

        return response;
    }

    public abstract CommandResponse executeWithSign(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Sign sign)
        throws CommandExecutionException;
        
}
