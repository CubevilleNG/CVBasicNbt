package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

public class EntityBoolean extends CommandWithEntity
{

    public EntityBoolean() {
        super("entity");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterEnumeratedString("silent", "invulnerable", "glowing", "gravity", "visualfire"));
        addOptionalBaseParameter(new CommandParameterBoolean());
    }

    @Override
    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        boolean parameter = true;
        if(baseParameters.size() > 1) parameter = (boolean) baseParameters.get(1);

        String action = (String) baseParameters.get(0);
        if(action.equals("silent"))
            entity.setSilent(parameter);
        else if(action.equals("invulnerable"))
            entity.setInvulnerable(parameter);
        else if(action.equals("glowing"))
            entity.setGlowing(parameter);
        else if(action.equals("gravity"))
            entity.setGravity(parameter);
        else if(action.equals("visualfire"))
            entity.setVisualFire(parameter);
        
        return new CommandResponse("&aEntity's " + action + " status set to " + (parameter ? "true" : "false"));
    }
}
