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

public class ItemPotionEffectAdd extends CommandWithItem {

    public ItemPotionEffectAdd() {
        super("item potion add");
        setPermission("snbt.item.potioneffects");
        addBaseParameter(new CommandParameterPotionEffectType());
        addBaseParameter(new CommandParameterInteger());
        addOptionalBaseParameter(new CommandParameterInteger());
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();
        if(!(meta instanceof PotionMeta))
            throw new CommandExecutionException("Must be holding a potion-like item.");

        PotionMeta pmeta = (PotionMeta) meta;

        PotionEffectType type = (PotionEffectType) baseParameters.get(0);
        int duration = (Integer) baseParameters.get(1) * 20;
        if(duration < 0) duration = 0;
        int amplifier = 0;
        if(baseParameters.size() == 3)
            amplifier = (Integer) baseParameters.get(2) - 1;
        if(amplifier < 0) amplifier = 0;
        
        PotionEffect effect = new PotionEffect(type, duration, amplifier);
        pmeta.addCustomEffect(effect, true);
        item.setItemMeta(pmeta);
        
        return new CommandResponse("&aPotion effect added.");
    }
}
