package org.cubeville.cvbasicnbt.commands.armorstand;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.entity.ArmorStand.LockType;

import org.cubeville.commons.utils.BlockUtils;

import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

public class ArmorStandBulkProperties extends Command
{
    public ArmorStandBulkProperties() {
        super("armorstand bulk");
        addBaseParameter(new CommandParameterEnumeratedString(new HashSet<>(Arrays.asList("visible", "baseplate", "smol", "small", "marker", "arms", "lock", "gravity"))));
        addBaseParameter(new CommandParameterBoolean());

        addParameter("r", false, new CommandParameterString());        
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        String what = (String) baseParameters.get(0);

        boolean value = true;
        if(baseParameters.size() == 2) value = (boolean) baseParameters.get(1);

        boolean we = false;
        double radius = 0.0;
        {
            String r = (String) parameters.get("r");
            if(r.equals("we")) {
                we = true;
            }
            else {
                radius = Double.valueOf(r);
            }
        }

        List<Entity> entities;
        if(we)
            entities = BlockUtils.getEntitiesInWESelection(player);
        else
            entities = BlockUtils.getEntitiesWithinRadius(player.getLocation(), radius);
        entities = BlockUtils.filterEntitiesByType(entities, EntityType.ARMOR_STAND);
        List<ArmorStand> armorstands = new ArrayList<>();
        for(Entity e: entities)
            armorstands.add((ArmorStand) e);
        
        if(entities.size() == 0) {
            throw new CommandExecutionException("No armor stands found.");
        }
        
        int cnt = 0;
        for(ArmorStand armorstand: armorstands) {
            cnt++;
            if(what.equals("visible"))
                armorstand.setVisible(value);
            else if(what.equals("baseplate"))
                armorstand.setBasePlate(value);
            else if(what.equals("smol") || what.equals("small"))
                armorstand.setSmall(value);
            else if(what.equals("marker"))
                armorstand.setMarker(value);
            else if(what.equals("arms"))
                armorstand.setArms(value);
            else if(what.equals("gravity"))
                armorstand.setGravity(value);
            else if(what.equals("lock")) {
                if(value) {
                    armorstand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING);
                    armorstand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING);
                    armorstand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING);
                    armorstand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING);
                    armorstand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING);
                    armorstand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING);
                }
                else {
                    armorstand.removeEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorstand.removeEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING);
                    armorstand.removeEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING);
                    armorstand.removeEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING);
                    armorstand.removeEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING);
                    armorstand.removeEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING);
                    armorstand.removeEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING);
                }
            }
        }

        return new CommandResponse("&a" + cnt + " armor stands changed.");
    }
}

