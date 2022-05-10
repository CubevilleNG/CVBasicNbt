package org.cubeville.cvbasicnbt.commands.selection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class ObjectDeselect extends Command {

    public ObjectDeselect() {
        super("deselect");
        setPermission("snbt.selection");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
                                   List<Object> baseParameters) throws CommandExecutionException {

        CommandMap.remove(player);
        CommandMap.stopSelecting(player);
        return new CommandResponse("&aSelecting cancelled, selection reset.");

    }

}

