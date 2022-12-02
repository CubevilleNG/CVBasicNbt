package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class EntityName extends CommandWithEntity
{
    public EntityName() {
        super("entity name");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterString());
    }

    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        String name = (String) baseParameters.get(0);
        entity.setCustomName(ColorUtils.addColorWithoutHeader(name));
        if(name.equals(""))
            entity.setCustomNameVisible(false);
        else
            entity.setCustomNameVisible(true);
        return new CommandResponse("&aEntity name changed to &r" + name);
    }
}
