package org.cubeville.cvbasicnbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class PotionEffectClear extends Command {

    public PotionEffectClear() {
        super("potion clear");
        setPermission("snbt.potion");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
            throws CommandExecutionException {

        ItemStack oldItem = player.getInventory().getItemInMainHand();
        if (oldItem.getType() != Material.POTION && oldItem.getType() != Material.LINGERING_POTION &&
                oldItem.getType() != Material.SPLASH_POTION && oldItem.getType() != Material.TIPPED_ARROW) {
            throw new CommandExecutionException("&cHeld item must be a &6Potion&c!");
        }
        oldItem.setItemMeta(null);

        player.getInventory().setItemInMainHand(oldItem);

        return new CommandResponse("&aPotion effects cleared from potion.");
    }

}
