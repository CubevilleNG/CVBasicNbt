package org.cubeville.cvbasicnbt.commands.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

public class CommandMap {
	
    private static Map<String, Object> commandMap = new HashMap<>();
    private static Set<String> isSelecting = new HashSet<>();
    
    public static void remove(Player player) {
        commandMap.remove(player.getName());
    }
    
    public static void removeObject(Object object) {
        if(containsObject(object)) {
            commandMap.entrySet().removeIf(entry -> (object.equals(entry.getValue())));
        }
    }
    
    public static void put(Player player, Object value) {
        commandMap.put(player.getName(), value);
    }

    public static boolean contains(Player player) {
        return commandMap.containsKey(player.getName()) && commandMap.get(player.getName()) != null;
    }

    public static boolean containsObject(Object value) {
        return commandMap.containsValue(value);
    }

    public static Object get(Player player) {
        return commandMap.get(player.getName());
    }

    public static void setSelecting(Player player) {
        isSelecting.add(player.getName());
    }

    public static void stopSelecting(Player player) {
        isSelecting.remove(player.getName());
    }
    
    public static boolean isSelecting(Player player) {
        return isSelecting.contains(player.getName());
    }
}
