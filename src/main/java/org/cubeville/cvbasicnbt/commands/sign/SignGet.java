package org.cubeville.cvbasicnbt.commands.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.CommandWithSign;

public class SignGet extends CommandWithSign
{
    public SignGet() {
        super("sign get");
        setPermission("snbt.sign");
    }

    @Override
    public CommandResponse executeWithSign(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Sign sign)
        throws CommandExecutionException {

        for(int i = 0; i < 4; i++) {
            String line = sign.getLine(i);
            String linenr = String.valueOf(i + 1);
            if(line != null && line.equals("") == false) {
                String setcommand = "/snbt sign set " + linenr + " \\\"" + ColorUtils.reverseColor(line) + "\\\"";
                String clip = ColorUtils.reverseColor(line);
                String o = "tellraw " + player.getName() + " [\"\",{\"text\":\"" + linenr + ". \"},{\"text\":\"[\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + setcommand + "\"}},{\"text\":\"Chat\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + setcommand + "\"}},{\"text\":\"]\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + setcommand + "\"}},{\"text\":\" \"},{\"text\":\"[\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"copy_to_clipboard\",\"value\":\"" + clip + "\"}},{\"text\":\"Clip\",\"color\":\"green\",\"clickEvent\":{\"action\":\"copy_to_clipboard\",\"value\":\"" + clip + "\"}},{\"text\":\"]\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"copy_to_clipboard\",\"value\":\"" + clip + "\"}},{\"text\":\" " + clip + "\"}]";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), o);
            }
        }
        return null;
    }
    
}
