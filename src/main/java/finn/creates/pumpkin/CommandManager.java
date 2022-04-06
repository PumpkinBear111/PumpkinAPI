package finn.creates.pumpkin;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CommandManager implements CommandExecutor, TabCompleter {
    abstract ChatColor primaryColor();
    abstract ChatColor secondaryColor();
    abstract ActivityCommandName alwaysActive();

    public PumpkinPlugin plugin = null;
    public PluginCommand command = null;

    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("help");
            arguments.add("about");
            if (sender.hasPermission(command.getPermission())) arguments.add("admin");
        }
        return arguments;
    }

    public final String toString() {
        return command.getName().toLowerCase(Locale.ROOT);
    }
}

enum ActivityCommandName {
    NONE,
    SET_ACTIVE,
    CHALLENGE,
    ENABLE_DISABLE,
}