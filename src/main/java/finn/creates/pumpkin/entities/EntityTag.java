package finn.creates.pumpkin.entities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.*;
import java.util.ArrayList;

/**
 * A way to tag entities and run code on their events.
 * @author Finn
 */
public abstract class EntityTag {
    public ArrayList<Entity> entities = new ArrayList<>();
    /**
     * The Entity Tag's Name
     */ public abstract String name();

    /**
     *
     * @param entity The referenced entity
     */ public void onAdded(Entity entity) {}
    /**
     *
     * @param entity The referenced entity
     */ public void onRemoved(Entity entity) {}
    /**
     *
     * @param entity The referenced entity
     */ public void eachTick(Entity entity) {}

    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void breedEvent(EntityBreedEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void combustEvent(EntityCombustEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void damageByBlockEvent(EntityDamageByBlockEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void damageByEntityEvent(EntityDamageByEntityEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void damageEvent(EntityDamageEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void deathEvent(EntityDeathEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void regainHealthEvent(EntityRegainHealthEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void tameEvent(EntityTameEvent event) {}
    /**
     * When an event happens on an entity.
     * @param event The Event
     */ public void teleportEvent(EntityTeleportEvent event) {}


    /**
     * Kills all entities. This will also call onRemoved() for each.
     * This will not cause any entity drops, and will just cause the entity to disappear.
    */ public final void killAll() {
        for (Entity entity : (ArrayList<Entity>) entities.clone()) {
            onRemoved(entity);
            entities.remove(entity);
            entity.remove();
        }
    }
}
