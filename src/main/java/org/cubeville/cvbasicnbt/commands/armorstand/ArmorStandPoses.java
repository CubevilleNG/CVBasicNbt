package org.cubeville.cvbasicnbt.commands.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEulerAngle;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.AdvancedSlots;

import org.cubeville.cvbasicnbt.commands.util.CommandWithArmorStand;

public class ArmorStandPoses extends CommandWithArmorStand
{
    public ArmorStandPoses() {
        super("armorstand pose");
        setPermission("snbt.armorstand");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterEulerAngle());
    }

    @Override
    public CommandResponse executeWithArmorStand(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ArmorStand armorstand)
        throws CommandExecutionException {

        boolean set = AdvancedSlots.setAngleByName(armorstand, (String) baseParameters.get(0), (EulerAngle) baseParameters.get(1));
        if (set) {
            return new CommandResponse("&aAngle Set to &6" + (String) baseParameters.get(0) + "&c!");
        } else {
            throw new CommandExecutionException("&cValid armor pose parts are: &fhead&c,&fbody&c,&fleft_arm&c,&fright_arm&c,&fleft_leg&c,&fright_leg");
        }
    }
    
}
