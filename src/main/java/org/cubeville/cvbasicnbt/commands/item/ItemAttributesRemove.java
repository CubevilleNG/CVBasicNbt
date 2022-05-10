package org.cubeville.cvbasicnbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterDouble;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.cvbasicnbt.commands.CommandWithItem;

public class ItemAttributesRemove extends CommandWithItem {

    public ItemAttributesRemove() {
        super("item attributes remove");
        setPermission("snbt.item.attributes");
        addBaseParameter(new CommandParameterEnum(Attribute.class));
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier((Attribute) baseParameters.get(0));
        item.setItemMeta(meta);

        return new CommandResponse("&aAll attribute modifiers of attribute " + ((Attribute) baseParameters.get(0)).toString().toLowerCase() + " removed.");
    }
}
