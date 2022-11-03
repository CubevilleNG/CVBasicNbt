package org.cubeville.cvbasicnbt.utils;

import org.bukkit.entity.Entity;

class EntityDistance implements Comparable<EntityDistance> {
    public double distance;
    public Entity entity;
    EntityDistance(Entity entity, double distance) {
        this.entity = entity;
        this.distance = distance;
    }
    public int compareTo(EntityDistance otherEntity) {
        if(otherEntity.distance == distance) return 0;
        if(otherEntity.distance > distance) return -1;
        return 1;
    }
}
    
