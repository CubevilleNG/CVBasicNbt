package org.cubeville.cvbasicnbt.commands.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemLore extends CommandWithItem {

    public ItemLore() {
        super("item lore");
        setPermission("snbt.item");
        for(int i = 0; i < 10; i++) {
            addOptionalBaseParameter(new CommandParameterString());
        }
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        List<String> lore = null;
        if(baseParameters.size() > 0) {
            lore = new ArrayList<>();
            for(Object l: baseParameters) {
                lore.add(ColorUtils.addColor((String) l));
            }
        }
        
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return new CommandResponse("&aItem lore changed.");
    }
    
}
