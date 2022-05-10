package org.cubeville.cvbasicnbt.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;

public class EntityUtils {
	
    public static List<Entity> getEntitiesByType(Collection<Entity> entityCollection, EntityType... types) {
        List<Entity> entities = new ArrayList<>();
		
        if (entityCollection == null) {
            return null;
        }
		
        for(Entity entity: entityCollection) {
            for(EntityType type: types) {
                if(entity.getType() == type) {
                    entities.add(entity);
                    break;
                }
            }
        }
        return entities;
		
    }

    public static List<Entity> filterEntityByUUID(Collection<Entity> entityCollection, UUID uuid) {
        List<Entity> ret = new ArrayList<>();
        for(Entity entity: entityCollection) {
            if(entity.getUniqueId() != uuid) {
                ret.add(entity);
            }
        }
        return ret;
    }
    
    public static Entity getNearestEntity(Location loc, List<Entity> entities) {
    	Entity nearestEntity = null;
    	double distance = 10000;
    	
    	if (entities == null) {
            return null;
    	}
    	
    	for(Entity entity: entities) {
            System.out.println("Near entity: " + entity.getType() + " at " + entity.getLocation());
            if (entity.getLocation().distance(loc) < distance) {
                nearestEntity = entity;
                distance = entity.getLocation().distance(loc);
            }
    	}
    	
        return nearestEntity;
    	
    }
	
}
