package org.cubeville.cvbasicnbt.commands.armorstand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.entity.ArmorStand.LockType;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandWithArmorStand;

public class ArmorStandProperties extends CommandWithArmorStand
{
    public ArmorStandProperties() {
        super("armorstand");
        addBaseParameter(new CommandParameterEnumeratedString(new HashSet<>(Arrays.asList("visible", "baseplate", "smol", "marker", "arms", "lock"))));
        addBaseParameter(new CommandParameterBoolean());
    }

    @Override
    public CommandResponse executeWithArmorStand(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ArmorStand armorstand)
        throws CommandExecutionException {

        String what = (String) baseParameters.get(0);
        boolean value = (Boolean) baseParameters.get(1);

        if(what.equals("visible"))
            armorstand.setVisible(value);
        else if(what.equals("baseplate"))
            armorstand.setBasePlate(value);
        else if(what.equals("smol"))
            armorstand.setSmall(value);
        else if(what.equals("marker"))
            armorstand.setMarker(value);
        else if(what.equals("arms"))
            armorstand.setArms(value);
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

        return new CommandResponse("&aAnyone know a meaningful success message?");
    }
}
