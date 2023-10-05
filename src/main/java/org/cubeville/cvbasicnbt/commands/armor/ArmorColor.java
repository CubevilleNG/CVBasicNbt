package org.cubeville.cvbasicnbt.commands.armor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterColor;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ArmorColor extends CommandWithItem
{
    public ArmorColor() {
        super("armor color");
        setPermission("snbt.armor");
        addBaseParameter(new CommandParameterColor());
        addFlag("add");
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        if(!(item.getItemMeta() instanceof LeatherArmorMeta))
            throw new CommandExecutionException("&cMust be holding &6leather armor&c!");

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        Color newCol = (Color) baseParameters.get(0);
        if(flags.contains("add")) {
            meta.setColor(meta.getColor().mixColors(newCol));
        }
        else {
            meta.setColor(newCol);
        }
        item.setItemMeta(meta);
        
        if(flags.contains("add")) {
            return new CommandResponse("&aColor &6" + ((Color) baseParameters.get(0)).toString() + "&a added to armor color.");
        }
        else {
            return new CommandResponse("&aArmor color changed to &6" + ((Color) baseParameters.get(0)).toString());
        }
    }
}
