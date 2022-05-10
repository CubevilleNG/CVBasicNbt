package org.cubeville.cvbasicnbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.AdvancedSlots;

import org.cubeville.cvbasicnbt.commands.util.CommandWithMob;

public class MobEquipment extends CommandWithMob
{
    public MobEquipment() {
        super("mob equipment");
        setPermission("snbt.mob.equipment");
        addBaseParameter(new CommandParameterEnum(EquipmentSlot.class));
    }

    @Override
    public CommandResponse executeWithMob(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, LivingEntity mob)
        throws CommandExecutionException {

        ItemStack item = player.getInventory().getItemInMainHand();
        EquipmentSlot slot = (EquipmentSlot) (baseParameters.get(0));
        
        item = AdvancedSlots.setEquipmentByName(mob, slot, item, false, 0.0f);
        player.getInventory().setItemInMainHand(item);
        return new CommandResponse("&aItem for &6" + slot.name() + " &afor &6" + mob.getCustomName() + " &aset to " + item.getType());
    }

}
