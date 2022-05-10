package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import java.text.DecimalFormat;

public class EntityInfo extends CommandWithEntity
{

    public EntityInfo() {
        super("entity info");
        setPermission("snbt.entity");
        addFlag("detail");
    }

    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        CommandResponse cr = new CommandResponse("&6--------------------------");
        boolean detail = flags.contains("detail");

        cr.addMessage("&aName&r: &6" + entity.getName());
        cr.addMessage("&aType&r: " + entity.getType());
        cr.addMessage("&aSilent&r: " + (entity.isSilent() ? "yes" : "no"));
        cr.addMessage("&aInvulnerable&r: " + (entity.isInvulnerable() ? "yes" : "no"));

        if(entity instanceof Tameable && ((Tameable) entity).isTamed()) {
            cr.addMessage("&aOwner&r: " + ((Tameable) entity).getOwner().getName());
        }

        DecimalFormat df = new DecimalFormat("#.##");
        cr.addMessage("&aLocation&r: "
                 + df.format(entity.getLocation().getX()) + " / "
                 + df.format(entity.getLocation().getY()) + " / "
                 + df.format(entity.getLocation().getZ()));

        return cr;
    }
    
}
