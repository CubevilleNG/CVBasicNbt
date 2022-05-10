package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.CommandParameterFloat;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class EntityRotate extends CommandWithEntity
{

    public EntityRotate() {
        super("entity rotate");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterFloat());
        addFlag("pitch");
    }

    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        Location location = entity.getLocation();
        float amount = (float) baseParameters.get(0);

        if(flags.contains("pitch")) {
            float newPitch = location.getPitch() + amount;
            if(newPitch > 90) newPitch = 90;
            if(newPitch < -90) newPitch = -90;
            location.setPitch(newPitch);
        }
        else {
            float newYaw = location.getYaw() + amount;
            while(newYaw < 0f) newYaw += 360f;
            while(newYaw >= 360f) newYaw -= 360f;
            location.setYaw(newYaw);
        }

        entity.teleport(location);

        return new CommandResponse("&aEntity rotated.");
    }
}
