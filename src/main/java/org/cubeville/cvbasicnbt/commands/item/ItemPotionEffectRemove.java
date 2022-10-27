package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterPotionEffectType;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemPotionEffectRemove extends CommandWithItem {

    public ItemPotionEffectRemove() {
        super("item potion remove");
        setPermission("snbt.item.potioneffects");
        addBaseParameter(new CommandParameterPotionEffectType());        
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();
        if(!(meta instanceof PotionMeta))
            throw new CommandExecutionException("Must be holding a potion-like item.");

        PotionMeta pmeta = (PotionMeta) meta;
        PotionEffectType type = (PotionEffectType) baseParameters.get(0);
        if(!pmeta.hasCustomEffect(type))
            return new CommandResponse("&aItem doesn't have this effect.");
        
        pmeta.removeCustomEffect(type);

        return new CommandResponse("&aRemoved potion effect.");
    }

}
