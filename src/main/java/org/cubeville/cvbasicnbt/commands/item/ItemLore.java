package org.cubeville.cvbasicnbt.commands.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemLore extends CommandWithItem {

    public ItemLore() {
        super("item lore");
        setPermission("snbt.item");

        addParameter("replace", true, new CommandParameterInteger());
        addParameter("insert", true, new CommandParameterInteger());
        addFlag("add");
        addFlag("append");
        
        for(int i = 0; i < 10; i++) {
            addOptionalBaseParameter(new CommandParameterString());
        }
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        if(flags.contains("add")) throw new CommandExecutionException("Command syntax has changed");
        
        List<String> plore = new ArrayList<>();
        for(Object l: baseParameters) {
            plore.add(ColorUtils.addColor((String) l));
        }
        
        ItemMeta meta = item.getItemMeta();

        List<String> lore;
        
        lore = meta.getLore();
        boolean insert = parameters.containsKey("insert");
        boolean append = flags.contains("append");
        int offset = 0;
        if(insert)
            offset = (Integer) parameters.get("insert");
        else
            if(parameters.containsKey("set"))
                offset = (Integer) parameters.get("set");
        // TODO: Originally contained "-1"? Test
        
        for(int i = 0; i < plore.size(); i++) {
            if(insert)
                lore.add(i + offset, plore.get(i));
            else {
                if(append)
                    lore.set(i + offset, lore.get(i + offset) + plore.get(i));
                else
                    lore.set(i + offset, plore.get(i));
            }
        }
        
        meta.setLore(lore);
        item.setItemMeta(meta);
        
        return new CommandResponse("&aItem lore changed.");
    }
    
}
