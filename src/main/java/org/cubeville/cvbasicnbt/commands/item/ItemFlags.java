package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemFlags extends CommandWithItem {

    public ItemFlags() {
        super("item flags");
        setPermission("snbt.item");

        addBaseParameter(new CommandParameterEnumeratedString("add", "remove", "show", "clear"));

        for(int i = 0; i < 7; i++) {
            addOptionalBaseParameter(new CommandParameterEnum(ItemFlag.class));
        }
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();

        String operation = (String) baseParameters.get(0);

        if(operation.equals("show")) {
            if(baseParameters.size() > 1) throw new CommandExecutionException("Item flags show command has no parameters.");
            String flaglist = "";
            for(ItemFlag flag: meta.getItemFlags()) {
                if(!flaglist.isEmpty()) flaglist += ", ";
                flaglist += flag.toString().toLowerCase();
            }
            if(flaglist.isEmpty())
                return new CommandResponse("&aThis item has no flags.");
            else
                return new CommandResponse("&aItem flags: " + flaglist);
        }

        if(((String) baseParameters.get(0)).equals("clear")) {
            if(baseParameters.size() > 1) throw new CommandExecutionException("Item flags clear command has no parameters.");
            if(meta.getItemFlags().size() == 0) throw new CommandExecutionException("Item has no flags.");
            for(ItemFlag flag: meta.getItemFlags()) {
                meta.removeItemFlags(flag);
            }
            return new CommandResponse("&aFlags removed.");
        }
        
        if(baseParameters.size() < 2) throw new CommandExecutionException("Come on, you gotta give me something to work with!");

        boolean add = ((String)baseParameters.get(0)).equals("add");
        int changed = 0;
        for(int i = 1; i < baseParameters.size(); i++) {
            ItemFlag flag = (ItemFlag) baseParameters.get(i);
            if(add) {
                if(!meta.hasItemFlag(flag)) {
                    meta.addItemFlags(flag);
                    changed++;
                }
            }
            else {
                if(meta.hasItemFlag(flag)) {
                    meta.removeItemFlags(flag);
                    changed++;
                }
            }
        }

        item.setItemMeta(meta);
        
        return new CommandResponse("&aFlags " + (add ? "added" : "removed") + ": " + changed);
    }

}
