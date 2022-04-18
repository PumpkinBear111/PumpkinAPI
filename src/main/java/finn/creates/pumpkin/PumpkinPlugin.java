package finn.creates.pumpkin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Used instead of org.bukkit.plugin.java.JavaPlugin for an easier setup process for spigot plugin development.
 * @author Finn
 */
public abstract class PumpkinPlugin extends JavaPlugin {
    /**
     * The instance of the command manager.
     */ public CommandManager commandManagerInstance;
    /**
     * Shows if the plugin is active or not
     */ public boolean active = false;

    /**
     * PumpkinPlugin Backend Initialization
     */ @Override public final void onEnable() {
        Log("Starting Initialization");
        Log("Pumpkin API Version Detected as: ");
        if (commandManager() != null) {
            try {
                commandManagerInstance = commandManager();
                commandManagerInstance.plugin = this;
                commandManagerInstance.command = getServer().getPluginCommand(commandName());
                PluginCommand cmd = getServer().getPluginCommand(commandManagerInstance.toString());
                assert cmd != null;
                cmd.setExecutor(commandManagerInstance);
                cmd.setTabCompleter(commandManagerInstance);
                Log("Command registered.");
            } catch (Exception e) {
                LogErr("An issue occurred while registering the command.");
            }
        } else Log("Good news! No Command needed to be registered :)");
        onAwake();
    }

    /**
     * PumpkinPlugin Backend De-initialization
     */ @Override public final void onDisable() {
        onClose();
        if (commandManager() != null) {
            try {
                getServer().getPluginCommand(commandName()).setExecutor(null);
                getServer().getPluginCommand(commandName()).setTabCompleter(null);
                Log("Command un-registered.");
            } catch (Exception e) {
                LogErr("An issue occurred while un-registering the command.");
            }
        } else Log("Good news! No Command needed to be un-registered :)");
    }

    // User Configuration

    /** */ public abstract void onAwake();
    /** Custom plugin exit. */ public abstract void onClose();
    /** @return prefix name of the plugin when logging. */ public abstract String loggingDisplayName();
    /** @return returns an instance of a command manager. */ public abstract CommandManager commandManager();
    /** @return returns the name of your plugin's command. */ public abstract String commandName();

    // Built-in Utilities
    /**
     * Send a message to the server for logging.
     * @param log message
     */ public final void Log(String log) {System.out.println("[" + loggingDisplayName() + "]: " + log);}
    /**
     * Send an error message to the server for logging.
     * @param err message
     */ public final void LogErr(String err) {System.err.println("[" + loggingDisplayName() + "]: " + err);}
    /**
     * Get the plugin's plugin.yml
     * @return the plugin.yml
     */ public final PluginDescriptionFile GetPluginYML() {return this.getDescription();}
    /**
     * Get the version of PumpkinAPI
     * @return the current PumpkinAPI version.
     */ public final String GetPumpkinVersion() {return "1.0.0";}
    /**
     * Get the plugin's display name
     * @return the plugin's display name
     */ public final String GetDisplayName() {return this.getDescription().getName();}
    /**
     * Get the plugin's description
     * @return the plugin's description
     */ public final String GetPluginDescription() {return this.getDescription().getDescription();}
    /**
     * Get the plugin's version
     * @return the plugin's version
     */ public final String GetVersion() {return this.getDescription().getVersion();}
}
