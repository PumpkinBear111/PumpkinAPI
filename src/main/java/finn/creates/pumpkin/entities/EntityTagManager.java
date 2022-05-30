package finn.creates.pumpkin.entities;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Manages entity tags.
 * @author Finn
 */
public final class EntityTagManager extends BukkitRunnable implements Listener {
    public ArrayList<EntityTag> tags = new ArrayList<>();

    @EventHandler void breedEvent(EntityBreedEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.breedEvent(event);
        }
    }
    @EventHandler void combustEvent(EntityCombustEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.combustEvent(event);
        }
    }
    @EventHandler void damageByBlockEvent(EntityDamageByBlockEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageByBlockEvent(event);
        }
    }
    @EventHandler void damageByEntityEvent(EntityDamageByEntityEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageByEntityEvent(event);
        }
    }
    @EventHandler void damageEvent(EntityDamageEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageEvent(event);
        }
    }
    @EventHandler void deathEvent(EntityDeathEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.deathEvent(event);
        }
    }
    @EventHandler void regainHealthEvent(EntityRegainHealthEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.regainHealthEvent(event);
        }
    }
    @EventHandler void tameEvent(EntityTameEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.tameEvent(event);
        }
    }
    @EventHandler void teleportEvent(EntityTeleportEvent event) {
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.teleportEvent(event);
        }
    }

    @Override public void run() {
        for (EntityTag tag : tags) {
            for (Entity entity : tag.entities) {
                tag.eachTick(entity);
            }
        }
    }

    // Entity Tagging
    /**
     * Register an entity tag.
     * @param tag Instance of the entity tag. It is suggested that you store this instance somewhere else as well for access.
     */ public void registerEntityTag(EntityTag tag) {
        this.tags.add(tag);
    }
    /**
     * Un-register an entity tag.
     * @param tag The tag to remove.
     */ public void removeEntityTag(EntityTag tag) {
        this.tags.remove(tag);
    }
    /**
     * Get an array of all the entity tags.
     * @return an array of all entity tags.
     */ public EntityTag[] getEntityTags() {
        return this.tags.toArray(new EntityTag[0]);
    }
    /**
     * Get an entity tag based on it's name.
     * @param name The name of the tag you want to get.
     * @return the entity tag with the given name.
     */ public EntityTag getEntityTag(String name) {
        for (EntityTag tag : this.tags) {
            if (tag.name().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) return tag;
        }
        return null;
    }
    /**
     * Give an EntityTag to an entity.
     * @param entity The entity that you want tagged.
     * @param tag The instance of the tag that you want the entity added to.
     */ public void addTag(Entity entity, EntityTag tag) {
        tag.entities.add(entity);
        tag.onAdded(entity);
    }
    /**
     * Remove an EntityTag from an entity.
     * @param entity The entity that you want un-tagged.
     * @param tag The instance of the tag that you want the entity removed from.
     */ public void removeTag(Entity entity, EntityTag tag) {
        tag.entities.remove(entity);
        tag.onRemoved(entity);
    }

    /**
     * Clears all entities from the tag.
     * @param tag The instance of the tag that you want cleared.
     */ public void clearTag(EntityTag tag) {
        for (Entity entity : tag.entities) {
            removeTag(entity, tag);
        }
        tag.entities.removeAll(tag.entities);
    }

    /**
     * Get an array of entities that have the specified entity tag applied to it.
     * @param tag The instance of the tag that you want an array of entities.
     * @return Array of entities
     */ public Entity[] getEntities(EntityTag tag) {
        return tag.entities.toArray(new Entity[1]);
    }
}
