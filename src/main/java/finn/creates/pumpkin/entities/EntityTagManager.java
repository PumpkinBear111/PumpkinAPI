package finn.creates.pumpkin.entities;

import finn.creates.pumpkin.extras.NotEnabledException;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Manages entity tags.
 * @author Finn
 */
public final class EntityTagManager extends BukkitRunnable implements Listener {
    public final boolean enabled;

    public EntityTagManager(boolean isEnabled) {
        enabled = isEnabled;
    }

    public ArrayList<EntityTag> tags = new ArrayList<>();

    @EventHandler void breedEvent(EntityBreedEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.breedEvent(event);
        }
    }
    @EventHandler void combustEvent(EntityCombustEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.combustEvent(event);
        }
    }
    @EventHandler void damageByBlockEvent(EntityDamageByBlockEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageByBlockEvent(event);
        }
    }
    @EventHandler void damageByEntityEvent(EntityDamageByEntityEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageByEntityEvent(event);
        }
    }
    @EventHandler void damageEvent(EntityDamageEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.damageEvent(event);
        }
    }
    @EventHandler void deathEvent(EntityDeathEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.deathEvent(event);
        }
    }
    @EventHandler void regainHealthEvent(EntityRegainHealthEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.regainHealthEvent(event);
        }
    }
    @EventHandler void tameEvent(EntityTameEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : tags) {
            if (tag.entities.contains(event.getEntity())) tag.tameEvent(event);
        }
    }
    @EventHandler void teleportEvent(EntityTeleportEvent event) {
        if (!enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
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
}
