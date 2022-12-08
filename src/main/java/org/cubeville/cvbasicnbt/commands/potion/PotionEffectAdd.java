package org.cubeville.cvbasicnbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterPotionEffectType;
import org.cubeville.commons.commands.CommandResponse;

public class PotionEffectAdd extends Command {

    public PotionEffectAdd() {
        super("potion add");
        setPermission("snbt.potion");
        addBaseParameter(new CommandParameterPotionEffectType());
        addParameter("level", true, new CommandParameterInteger());
        addParameter("duration", true, new CommandParameterInteger());
        // TODO Auto-generated constructor stub
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
            throws CommandExecutionException {

        ItemStack oldItem = player.getInventory().getItemInMainHand();
        if (oldItem.getType() != Material.POTION && oldItem.getType() != Material.LINGERING_POTION &&
                oldItem.getType() != Material.SPLASH_POTION && oldItem.getType() != Material.TIPPED_ARROW) {
            throw new CommandExecutionException("&cHeld item must be a &6Potion&c!");
        }

        PotionEffectType potionType = (PotionEffectType) baseParameters.get(0);

        int dur = 100;
        int level = 0;
        if(parameters.containsKey("duration")) {
            dur = (int) parameters.get("duration") * 20;
        }
        if(parameters.containsKey("level")) {
            level = (int) parameters.get("level") - 1;
        }
        if(oldItem.getType() == Material.LINGERING_POTION) {
            dur *= 4;
        }
        else if(oldItem.getType() == Material.TIPPED_ARROW) {
            dur *= 8;
        }

        PotionEffect potion = new PotionEffect(potionType, dur, level);
        PotionMeta meta = (PotionMeta) oldItem.getItemMeta();
        meta.addCustomEffect(potion, true);
        oldItem.setItemMeta(meta);

        player.getInventory().setItemInMainHand(oldItem);

        return new CommandResponse("&aPotion Effect Type &6" + potionType.getName().toLowerCase() + " &awith level:&6" + (level + 1) + " &aand&6 duration:&6" + dur + " &aadded to potion.");
    }

}
