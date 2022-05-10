package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterShort;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemDurability extends CommandWithItem {

    public ItemDurability() {
        super("item durability");
        setPermission("snbt.item");
        addParameter("set", true, new CommandParameterShort());
        addParameter("unbreakable", true, new CommandParameterBoolean());
        addFlag("max");
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        if(!(parameters.containsKey("set") || flags.contains("max") || parameters.containsKey("unbreakable"))) {
            return new CommandResponse("&aCurrent durability: " + item.getDurability());
        }

        if (parameters.containsKey("set") && !flags.contains("max")) {
            item.setDurability((short) (item.getType().getMaxDurability() - ((short) parameters.get("set"))));
        } else if (!parameters.containsKey("set") && flags.contains("max")) {
            item.setDurability((short) 0);
        }

        if (parameters.containsKey("unbreakable")) {
            ItemMeta meta = item.getItemMeta();
            meta.setUnbreakable((boolean) parameters.get("unbreakable"));
            item.setItemMeta(meta);
        }

        return new CommandResponse("&aItem durability changed.");
    }
    
}
