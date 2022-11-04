package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemEnchantmentsClear extends CommandWithItem {

    public ItemEnchantmentsClear() {
        super("item enchant clear");
        setPermission("snbt.item");
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        for(Enchantment e: item.getEnchantments().keySet()) {
            item.removeEnchantment(e);
        }

        return new CommandResponse("&aAll enchantments removed.");
    }
}
