package org.cubeville.cvbasicnbt.commands.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.util.CommandMap;

public class ArmorStandSummon extends Command
{
    public ArmorStandSummon() {
        super("armorstand summon");
        setPermission("snbt.armorstand");
        addFlag("invisible");
        addFlag("small");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        ArmorStand newstand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        newstand.setInvulnerable(true);
        newstand.setPersistent(true);
        newstand.setBasePlate(false);
        newstand.setGravity(false);
        newstand.setArms(true);

        if(flags.contains("small"))
            newstand.setSmall(true);
        if(flags.contains("invisible"))
            newstand.setVisible(false);
        
        CommandMap.put(player, newstand);

        return new CommandResponse("&aArmor stand spawned.");
    }
}
