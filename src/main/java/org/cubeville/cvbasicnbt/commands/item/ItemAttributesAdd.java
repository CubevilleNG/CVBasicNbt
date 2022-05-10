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

public class ItemAttributesAdd extends CommandWithItem {

    public ItemAttributesAdd() {
        super("item attributes add");
        setPermission("snbt.item.attributes");
        addBaseParameter(new CommandParameterEnum(Attribute.class));
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterEnum(AttributeModifier.Operation.class));
        addBaseParameter(new CommandParameterDouble());
        addParameter("slot", true, new CommandParameterEnum(EquipmentSlot.class));
    }

    @Override
    public CommandResponse executeWithItem(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, ItemStack item)
        throws CommandExecutionException {

        ItemMeta meta = item.getItemMeta();

        Attribute attribute = (Attribute) baseParameters.get(0);
        String name = (String) baseParameters.get(1);
        AttributeModifier.Operation operation = (AttributeModifier.Operation) baseParameters.get(2);
        double amount = (double) baseParameters.get(3);
        EquipmentSlot slot = (EquipmentSlot) parameters.get("slot");

        try {
            if(!meta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), name, amount, operation, slot))) {
                throw new CommandExecutionException("Can't add this attribute modifier.");
            }
        }
        catch(RuntimeException e) {
            throw new CommandExecutionException("Can't add this attribute modifier.");
        }

        item.setItemMeta(meta);
        
        return new CommandResponse("Attribute modifier added.");
    }

}
