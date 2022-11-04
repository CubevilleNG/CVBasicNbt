package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnchantment;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemEnchantmentsAdd extends CommandWithItem {

    public ItemEnchantmentsAdd() {
        super("item enchant add");
        setPermission("snbt.item.enchantments");
        addBaseParameter(new CommandParameterEnchantment());
        addBaseParameter(new CommandParameterInteger());
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        item.addUnsafeEnchantment((Enchantment) baseParameters.get(0), (int) baseParameters.get(1));
        return new CommandResponse("&aEnchantment &6" + ((Enchantment) baseParameters.get(0)).getName().toLowerCase() + " &aadded to item!");

    }
}
