package org.cubeville.cvbasicnbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.CVBasicNbt;

public class EntityBoolean extends CommandWithEntity
{

    public EntityBoolean() {
        super("entity");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterEnumeratedString("silent", "invulnerable", "glow", "gravity", "visualfire", "autoremove"));
        addOptionalBaseParameter(new CommandParameterEnumeratedString("true", "false", "temp"));
    }

    @Override
    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        String action = (String) baseParameters.get(0);
        
        boolean parameter = true;
        boolean temporary = false;
        if(baseParameters.size() > 1) {
            if(((String) baseParameters.get(1)).equals("false"))
                parameter = false;
            if(((String) baseParameters.get(1)).equals("temp")) {
                temporary = true;
                if(!action.equals("glow"))
                    throw new CommandExecutionException("temp only works for glow");
            }
        }
        
        if(action.equals("silent"))
            entity.setSilent(parameter);
        else if(action.equals("invulnerable"))
            entity.setInvulnerable(parameter);
        else if(action.equals("glow"))
            entity.setGlowing(parameter);
        else if(action.equals("gravity"))
            entity.setGravity(parameter);
        else if(action.equals("visualfire"))
            entity.setVisualFire(parameter);
        else if(action.equals("autoremove")) {
            if(entity instanceof LivingEntity && (entity instanceof ArmorStand) == false)
                ((LivingEntity) entity).setRemoveWhenFarAway(parameter);
            else
                throw new CommandExecutionException("autoremove can only be set on living entities");
        }
        
        if(action.equals("glow") && temporary == true) {
            new BukkitRunnable() {
                public void run() {
                    entity.setGlowing(false);
                }
            }.runTaskLater(CVBasicNbt.getInstance(), 40);
        }
        
        return new CommandResponse("&aEntity's " + action + " status set to " + (parameter ? "true" : "false"));
    }
}
