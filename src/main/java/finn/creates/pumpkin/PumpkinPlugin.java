package finn.creates.pumpkin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PumpkinPlugin extends JavaPlugin {
    // Plugin Open/Close
    @Override
    public void onEnable() {
        try {
            CommandManager commandInstance = commandManager();
            commandInstance.plugin = this;
            PluginCommand cmd = getServer().getPluginCommand(commandInstance.toString());
            assert cmd != null;
            cmd.setExecutor(commandInstance);
            cmd.setTabCompleter(commandInstance);
        } catch (Exception e) {
            LogErr("An issue occurred while registering the commands.");
        }
        onAwake();
    }

    @Override
    public void onDisable() {
        onClose();
    }

    // User Configuration
    public abstract String loggingDisplayName();
    public abstract CommandManager commandManager();
    public abstract void onAwake();
    public abstract void onClose();

    // Built-in Utilities
    public void Log(String log) {System.out.println("[" + loggingDisplayName() + "]: " + log);}
    public void LogErr(String err) {System.err.println("[" + loggingDisplayName() + "]: " + err);}
    public void Version() {}
    public PluginDescriptionFile GetPluginYML() {return this.getDescription();}
    public String GetDisplayName() {return this.getDescription().getName();}
    public String GetPluginDescription() {return this.getDescription().getDescription();}
    public String GetVersion() {return this.getDescription().getVersion();}
    public String GetCommand() {return this.getDescription().getCommands().keySet().toArray(new String[0])[0];}
}
