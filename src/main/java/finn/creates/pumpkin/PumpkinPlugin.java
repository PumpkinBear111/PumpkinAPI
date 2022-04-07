package finn.creates.pumpkin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PumpkinPlugin extends JavaPlugin {
    public CommandManager commandManagerInstance;
    public boolean active = false;

    // Plugin Open/Close
    @Override
    public final void onEnable() {
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

    @Override
    public final void onDisable() {
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
    public abstract String loggingDisplayName();
    public abstract CommandManager commandManager();
    public abstract String commandName();
    public abstract void onAwake();
    public abstract void onClose();

    // Built-in Utilities
    public final void Log(String log) {System.out.println("[" + loggingDisplayName() + "]: " + log);}
    public final void LogErr(String err) {System.err.println("[" + loggingDisplayName() + "]: " + err);}
    public final PluginDescriptionFile GetPluginYML() {return this.getDescription();}
    public final String GetDisplayName() {return this.getDescription().getName();}
    public final String GetPluginDescription() {return this.getDescription().getDescription();}
    public final String GetVersion() {return this.getDescription().getVersion();}
}
