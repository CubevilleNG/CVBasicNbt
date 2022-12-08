package org.cubeville.cvbasicnbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

public class PotionType extends Command {

    public PotionType() {
        super("potion type");
        setPermission("snbt.potion");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
            throws CommandExecutionException {

        ItemStack oldItem = player.getInventory().getItemInMainHand();
        if (oldItem.getType() != Material.POTION && oldItem.getType() != Material.LINGERING_POTION &&
                oldItem.getType() != Material.SPLASH_POTION && oldItem.getType() != Material.TIPPED_ARROW) {
            throw new CommandExecutionException("&cHeld item must be a &6Potion&c!");
        }

        String oldType = player.getInventory().getItemInMainHand().getType().name().toLowerCase();
        String newType = (String) baseParameters.get(0);
        ItemStack newItem = new ItemStack(Material.valueOf(newType.toUpperCase()), oldItem.getAmount());

        newItem.setItemMeta(oldItem.getItemMeta());

        player.getInventory().setItemInMainHand(newItem);

        return new CommandResponse("&aPotion Item changed from &6" + oldType + "&a to &6" + newType.toLowerCase());
    }

}
