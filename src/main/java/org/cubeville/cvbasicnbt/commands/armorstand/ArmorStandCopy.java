package org.cubeville.cvbasicnbt.commands.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandWithArmorStand;
import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class ArmorStandCopy extends CommandWithArmorStand
{
    public ArmorStandCopy() {
        super("armorstand copy");
        setPermission("snbt.armorstand");
    }

    @Override
    public CommandResponse executeWithArmorStand(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ArmorStand armorstand)
        throws CommandExecutionException {

        ArmorStand newstand = (ArmorStand) armorstand.getLocation().getWorld().spawnEntity(armorstand.getLocation(), EntityType.ARMOR_STAND);
        
        newstand.setGravity(armorstand.hasGravity());
        newstand.setVisible(armorstand.isVisible());
        newstand.setInvulnerable(armorstand.isInvulnerable());
        newstand.setBasePlate(armorstand.hasBasePlate());
        newstand.setSmall(armorstand.isSmall());
        newstand.setMarker(armorstand.isMarker());
        newstand.setArms(armorstand.hasArms());

        newstand.setBodyPose(armorstand.getBodyPose());
        newstand.setBoots(armorstand.getBoots());
        newstand.setChestplate(armorstand.getChestplate());
        newstand.setHeadPose(armorstand.getHeadPose());
        newstand.setHelmet(armorstand.getHelmet());
        newstand.setItemInHand(armorstand.getItemInHand());
        newstand.getEquipment().setItemInOffHand(armorstand.getEquipment().getItemInOffHand());
        newstand.setLeftArmPose(armorstand.getLeftArmPose());
        newstand.setRightArmPose(armorstand.getRightArmPose());
        newstand.setLeggings(armorstand.getLeggings());
        newstand.setLeftLegPose(armorstand.getLeftLegPose());
        newstand.setRightLegPose(armorstand.getRightLegPose());

        CommandMap.put(player, newstand);
        
        return new CommandResponse("&aArmor stand duplicated.");
    }
}
