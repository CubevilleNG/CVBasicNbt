package org.cubeville.cvbasicnbt.commands.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.util.EulerAngle;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.entity.ArmorStand;
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
        addFlag("uuid");
    }

    private String serializeEulerAngle(EulerAngle angle) {
        return Math.round(angle.getX() * 100.0) / 100.0 + ", "
            + Math.round(angle.getY() * 100.0) / 100.0 + ", "
            + Math.round(angle.getZ() * 100.0) / 100.0;
    }
    
    public CommandResponse executeWithEntity(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Entity entity, boolean selectedByUUID)
        throws CommandExecutionException {

        if(flags.contains("uuid")) {
            return new CommandResponse(entity.getUniqueId().toString());
        }
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

        if(entity.getType() == EntityType.ARMOR_STAND) {
            ArmorStand as = (ArmorStand) entity;
            String props = as.isVisible() ? "visible" : "invisible";
            props += ", ";
            props += as.hasBasePlate() ? "baseplate" : "no baseplate";
            props += ", ";
            props += as.isSmall() ? "smol" : "normal size";
            props += ", ";
            props += as.isMarker() ? "marker" : "no marker";
            props += ", ";
            props += as.hasArms() ? "arms" : "no arms";
            props += ", ";
            props += as.hasGravity() ? "falling" : "floating";            
            cr.addMessage("&aProperties&r: " + props);

            String locks = "";
            for(EquipmentSlot slot: Arrays.asList(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HAND, EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.OFF_HAND)) {
                if(as.hasEquipmentLock(slot, ArmorStand.LockType.REMOVING_OR_CHANGING)) {
                    if(locks.length() > 0) locks += ", ";
                    locks += slot;
                }
            }
            cr.addMessage("&aRem./Chg. Locks&r: " + (locks.length() > 0 ? locks.toLowerCase() : "none"));

            cr.addMessage("&ePoses:");
            cr.addMessage("&aBody&r: " + serializeEulerAngle(as.getBodyPose()));
            cr.addMessage("&aHead&r: " + serializeEulerAngle(as.getHeadPose()));
            if(as.hasArms()) {
                cr.addMessage("&aLeft arm&r: " + serializeEulerAngle(as.getLeftArmPose()));
                cr.addMessage("&aRight arm&r: " + serializeEulerAngle(as.getRightArmPose()));
            }
            cr.addMessage("&aLeft leg&r: " + serializeEulerAngle(as.getLeftLegPose()));
            cr.addMessage("&aRight leg&r: " + serializeEulerAngle(as.getRightLegPose()));
        }

        return cr;
    }
    
}
