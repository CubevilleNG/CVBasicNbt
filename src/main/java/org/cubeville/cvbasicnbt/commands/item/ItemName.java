package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemName extends CommandWithItem
{
    public ItemName() {
        super("item name");
        setPermission("snbt.item");
        addFlag("append");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();

        boolean append = flags.contains("append");

        String parameter = (String) baseParameters.get(0);
        if(parameter.toLowerCase().equals("get")) {
            if(append) throw new CommandExecutionException("Can't get and append at the same time.");
            
            String name = meta.getDisplayName();
            name = ColorUtils.reverseColor(name.replace("\"", "\\\""));
            String json = "[\"\",{\"text\":\"" + name + "\",\"clickEvent\":{\"action\":\"copy_to_clipboard\",\"value\":\"" + name + "\"}},{\"text\":\" §r§a(click to copy to clipboard)\"}]";
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
                                               "tellraw " + player.getName() + " " + json);
            return new CommandResponse(true);
        }

        parameter = ColorUtils.addColor(parameter);

        if(append) {
            meta.setDisplayName(meta.getDisplayName() + parameter);
        }
        else {
            meta.setDisplayName(ColorUtils.addColor(parameter));
        }
        item.setItemMeta(meta);

        return new CommandResponse("&aItem name set to &r" + baseParameters.get(0));
    }

}
