package org.cubeville.cvbasicnbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

public class PotionEffectRemove extends Command {

    public PotionEffectRemove() {
        super("potion remove");
        setPermission("snbt.potion");
        addBaseParameter(new CommandParameterInteger());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
            throws CommandExecutionException {

        ItemStack oldItem = player.getInventory().getItemInMainHand();
        if (oldItem.getType() != Material.POTION && oldItem.getType() != Material.LINGERING_POTION &&
                oldItem.getType() != Material.SPLASH_POTION && oldItem.getType() != Material.TIPPED_ARROW) {
            throw new CommandExecutionException("&cHeld item must be a &6Potion&c!");
        }

        PotionMeta meta = (PotionMeta) oldItem.getItemMeta();

        if (!(meta.getCustomEffects().size() >= (int) baseParameters.get(0)) || (int) baseParameters.get(0) <= 0) {
            throw new CommandExecutionException("&cThe line &6" + baseParameters.get(0) + " &cdoes not exist for this potion.");
        }

        PotionEffectType type = meta.getCustomEffects().get((int) baseParameters.get(0) - 1).getType();
        meta.removeCustomEffect(type);
        oldItem.setItemMeta(meta);
        player.getInventory().setItemInMainHand(oldItem);

        return new CommandResponse("&aPotion Effect Type &6" + type + " &afrom line &6" + baseParameters.get(0) + " &aremoved from potion.");
    }
}
