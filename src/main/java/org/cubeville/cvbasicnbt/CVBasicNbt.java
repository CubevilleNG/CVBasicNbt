package org.cubeville.cvbasicnbt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.cubeville.commons.commands.CommandParser;

import org.cubeville.cvbasicnbt.commands.armor.*;
import org.cubeville.cvbasicnbt.commands.armorstand.*;
import org.cubeville.cvbasicnbt.commands.entity.*;
import org.cubeville.cvbasicnbt.commands.item.*;
import org.cubeville.cvbasicnbt.commands.mob.*;
import org.cubeville.cvbasicnbt.commands.selection.*;
import org.cubeville.cvbasicnbt.commands.sign.*;
import org.cubeville.cvbasicnbt.events.*;

public class CVBasicNbt extends JavaPlugin {

    CommandParser commandParser;

    public void onEnable() {

        commandParser = new CommandParser();
        commandParser.addCommand(new ArmorColor());
        commandParser.addCommand(new ArmorStandPoses());
        commandParser.addCommand(new ArmorStandProperties());
        commandParser.addCommand(new EntityInfo());
        commandParser.addCommand(new EntityMove());
        commandParser.addCommand(new EntityMoveHere());
        commandParser.addCommand(new EntityRemove());
        commandParser.addCommand(new EntityRotate());
        commandParser.addCommand(new EntityBoolean());
        commandParser.addCommand(new ItemAttributesAdd());
        commandParser.addCommand(new ItemAttributesRemove());
        commandParser.addCommand(new ItemAttributesShow());
        commandParser.addCommand(new ItemDurability());
        commandParser.addCommand(new ItemEnchantmentsClear());
        commandParser.addCommand(new ItemEnchantmentsAdd());
        commandParser.addCommand(new ItemEnchantmentsRemove());
        commandParser.addCommand(new ItemFlags());
        commandParser.addCommand(new ItemLore());
        commandParser.addCommand(new ItemName());
        commandParser.addCommand(new ItemPotionEffectAdd());
        commandParser.addCommand(new ItemPotionEffectRemove());
        commandParser.addCommand(new MobEquipment());
        commandParser.addCommand(new ObjectDeselect());
        commandParser.addCommand(new ObjectSelect());
        commandParser.addCommand(new ObjectSelectNearest());
        commandParser.addCommand(new SignGet());
        commandParser.addCommand(new SignSet());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EventBlockRemoval(), this);
        pm.registerEvents(new EventEntityDeath(), this);
        pm.registerEvents(new EventPlayerInteract(), this);
        pm.registerEvents(new EventPlayerInteractEntity(), this);
        pm.registerEvents(new EventPlayerQuit(), this);
 
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("snbt")) {
            return commandParser.execute(sender, args);
        }
        return false;
    }
        
}
