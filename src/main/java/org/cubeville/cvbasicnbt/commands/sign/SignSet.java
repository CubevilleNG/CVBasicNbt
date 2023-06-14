package org.cubeville.cvbasicnbt.commands.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;

import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.cvbasicnbt.commands.CommandWithSign;
import org.cubeville.commons.commands.CommandParameterInteger;

public class SignSet extends CommandWithSign
{

    public SignSet() {
        super("sign set");
        setPermission("snbt.sign");
        addBaseParameter(new CommandParameterInteger());
        addBaseParameter(new CommandParameterString());
        addOptionalBaseParameter(new CommandParameterString());
        addOptionalBaseParameter(new CommandParameterString());
        addOptionalBaseParameter(new CommandParameterString());
        addParameter("side", true, new CommandParameterString());
    }

    @Override
    public CommandResponse executeWithSign(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters, Sign sign)
        throws CommandExecutionException {

        int firstLine = (int) baseParameters.get(0);

        if(firstLine < 1 || firstLine + baseParameters.size() > 6) {
            throw new CommandExecutionException("Invalid line number!");
        }
        Side side = Side.FRONT;
        if(parameters.containsKey("side")) {
            if(parameters.get("side").toString().equalsIgnoreCase("front")) {
                side = Side.FRONT;
            } else if(parameters.get("side").toString().equalsIgnoreCase("back")) {
                side = Side.BACK;
            } else {
                throw new CommandExecutionException("Invalid side! Use side:front or side:back!");
            }
        }

        for(int i = 0; i < baseParameters.size() - 1; i++) {
            sign.getSide(side).setLine(i + firstLine - 1, ColorUtils.addColorWithoutHeader((String) baseParameters.get(i + 1)));
        }

        return new CommandResponse("&aSign text changed.");
    }
}
