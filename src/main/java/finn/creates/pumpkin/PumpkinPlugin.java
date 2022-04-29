package finn.creates.pumpkin;

import finn.creates.pumpkin.entities.EntityTag;
import finn.creates.pumpkin.entities.EntityTagManager;
import finn.creates.pumpkin.extras.NotEnabledException;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

/**
 * Used instead of org.bukkit.plugin.java.JavaPlugin for an easier setup process for spigot plugin development.
 * @author Finn
 */
public abstract class PumpkinPlugin extends JavaPlugin {
    /**
     * The instance of the command manager.
     */ public CommandManager commandManagerInstance;
    /**
     * The instance of the entity tag manager.
     */ private final EntityTagManager entityTagManager = new EntityTagManager(requiresEntityTags());
    /**
     * Shows if the plugin is active or not
     */ public boolean active = false;

    /**
     * PumpkinPlugin Backend Initialization
     */ @Override public final void onEnable() {
        log("Starting Initialization");
        log("Pumpkin API Version Detected as: " + getPumpkinVersion());
        if (commandManager() != null) {
            try {
                commandManagerInstance = commandManager();
                commandManagerInstance.plugin = this;
                commandManagerInstance.command = getServer().getPluginCommand(commandName());
                PluginCommand cmd = getServer().getPluginCommand(commandManagerInstance.toString());
                assert cmd != null;
                cmd.setExecutor(commandManagerInstance);
                cmd.setTabCompleter(commandManagerInstance);
                log("Command registered.");
            } catch (Exception e) {
                logErr("An issue occurred while registering the command.");
            }
        } else log("Good news! No Command needed to be registered :)");
        if (requiresEntityTags()) {
            getServer().getPluginManager().registerEvents(entityTagManager, this);
            entityTagManager.runTaskTimer(this, 0, 1);
            log("Enabled Entity Tagging");
        }
        onAwake();
        log("Initialization Complete!");
    }

    /**
     * PumpkinPlugin Backend De-initialization
     */ @Override public final void onDisable() {
        onClose();
        if (commandManager() != null) {
            try {
                getServer().getPluginCommand(commandName()).setExecutor(null);
                getServer().getPluginCommand(commandName()).setTabCompleter(null);
                log("Command un-registered.");
            } catch (Exception e) {
                logErr("An issue occurred while un-registering the command.");
            }
        } else log("Good news! No Command needed to be un-registered :)");
    }

    // User Configuration

    /** */ public abstract void onAwake();
    /** Custom plugin exit. */ public abstract void onClose();
    /** @return prefix name of the plugin when logging. */ public abstract String loggingDisplayName();
    /** @return returns an instance of a command manager. */ public abstract CommandManager commandManager();
    /** @return returns the name of your plugin's command. */ public abstract String commandName();
    /** @return returns the name of your plugin's command. */ public abstract boolean requiresEntityTags();

    // Built-in Utilities
    /**
     * Send a message to the server for logging.
     * @param log message
     */ public final void log(String log) {System.out.println("[" + loggingDisplayName() + "]: " + log);}
    /**
     * Send an error message to the server for logging.
     * @param err message
     */ public final void logErr(String err) {System.err.println("[" + loggingDisplayName() + "]: " + err);}
    /**
     * Get the plugin's plugin.yml
     * @return the plugin.yml
     */ public final PluginDescriptionFile getPluginYML() {return this.getDescription();}
    /**
     * Get the version of PumpkinAPI
     * @return the current PumpkinAPI version.
     */ public final String getPumpkinVersion() {return "1.0.0";}
    /**
     * Get the plugin's display name
     * @return the plugin's display name
     */ public final String getDisplayName() {return this.getDescription().getName();}
    /**
     * Get the plugin's description
     * @return the plugin's description
     */ public final String getPluginDescription() {return this.getDescription().getDescription();}
    /**
     * Get the plugin's version
     * @return the plugin's version
     */ public final String getVersion() {return this.getDescription().getVersion();}

    // Entity Tagging
    /**
     * Register an entity tag.
     * @param tag Instance of the entity tag. It is suggested that you store this instance somewhere else as well for access.
     */ public void registerEntityTag(EntityTag tag) {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        entityTagManager.tags.add(tag);
    }
    /**
     * Un-register an entity tag.
     * @param tag The tag to remove.
     */ public void removeEntityTag(EntityTag tag) {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        entityTagManager.tags.remove(tag);
    }
    /**
     * Get an array of all the entity tags.
     * @return an array of all entity tags.
     */ public EntityTag[] getEntityTags() {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        return entityTagManager.tags.toArray(new EntityTag[0]);
    }
    /**
     * Get an entity tag based on it's name.
     * @param name The name of the tag you want to get.
     * @return the entity tag with the given name.
     */ public EntityTag getEntityTag(String name) {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        for (EntityTag tag : entityTagManager.tags) {
            if (tag.name().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) return tag;
        }
        return null;
    }
    /**
     * Give an EntityTag to an entity.
     * @param entity The entity that you want tagged.
     * @param tag The instance of the tag that you want the entity added to.
     */ public void addTag(Entity entity, EntityTag tag) {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        tag.entities.add(entity);
        tag.onAdded(entity);
    }
    /**
     * Remove an EntityTag from an entity.
     * @param entity The entity that you want un-tagged.
     * @param tag The instance of the tag that you want the entity removed from.
     */ public void removeTag(Entity entity, EntityTag tag) {
        if (!entityTagManager.enabled) throw new NotEnabledException("Entity Tags are not Enabled.");
        tag.entities.remove(entity);
        tag.onRemoved(entity);
    }
}
