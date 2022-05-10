package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemAttributesShow extends CommandWithItem {

    public ItemAttributesShow() {
        super("item attributes show");
        setPermission("snbt.item.attributes");
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();
        Multimap<Attribute,AttributeModifier> list = meta.getAttributeModifiers();

        if(list == null) throw new CommandExecutionException("&cItem has no attribute modifiers.");

        CommandResponse ret = new CommandResponse();
        
        for(Attribute a: list.keySet()) {
            ret.addMessage("&e" + a.toString().toLowerCase() + "&r:");
            for(AttributeModifier am: list.get(a)) {
                ret.addMessage("- " + am.getName().toLowerCase() + ": &a" + am.getAmount() + "&r, &b" + am.getOperation().toString().toLowerCase() + "&r, &d" + (am.getSlot() == null ? "No slot" : am.getSlot().toString().toLowerCase()));
            }
        }
            
        return ret;
    }
}
