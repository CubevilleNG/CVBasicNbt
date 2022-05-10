package org.cubeville.cvbasicnbt.commands.selection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class ObjectSelect extends Command {

    public ObjectSelect() {
        super("select");
        setPermission("snbt.selection");
        addParameter("player", true, new CommandParameterOnlinePlayer());
        addFlag("self");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
                                   List<Object> baseParameters) throws CommandExecutionException {
        if (flags.contains("self") && !parameters.containsKey("player")) {
            CommandMap.put(player, player);
            return new CommandResponse("&aSelected &6Self&a!");
        } else if (!flags.contains("self") && parameters.containsKey("player") && player.hasPermission("snbt.selection.player")) {
            CommandMap.put(player, (Player) parameters.get("player"));
            return new CommandResponse("&aSelected &6" + ((Player) parameters.get("player")).getName());
        }
		
        if (!CommandMap.isSelecting(player)) {
            CommandMap.setSelecting(player);
            return new CommandResponse("&aYou may now select an &6Object&a!");
        } else {
            throw new CommandExecutionException("&cYou are already selecting an &6Object&c!");
        }
    }

}
