package org.cubeville.cvbasicnbt.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public static Entity getNearestEntity(Location loc, List<Entity> entities, int skipCount) {
    	if (entities == null) {
            return null;
    	}

        List<EntityDistance> elist = new ArrayList<>();
        for(Entity entity: entities) {
            EntityDistance ed = new EntityDistance(entity, entity.getLocation().distance(loc));
            elist.add(ed);
        }
        Collections.sort(elist);

        if(skipCount < 0 || skipCount >= elist.size()) {
            return null;
        }
        return elist.get(skipCount).entity;
    }

}
