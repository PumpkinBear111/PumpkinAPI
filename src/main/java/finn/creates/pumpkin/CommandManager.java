package finn.creates.pumpkin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CommandManager implements CommandExecutor, TabCompleter {
    abstract String commandName();
    abstract String permissionNode();
    abstract ChatColor primaryColor();
    abstract ChatColor secondaryColor();
    abstract ActivityCommandName alwaysActive();

    PumpkinPlugin plugin = null;

    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("help");
            arguments.add("about");
            if (sender.hasPermission(permissionNode())) arguments.add("admin");
        }
        return arguments;
    }

    public String toString() {
        return commandName().toLowerCase(Locale.ROOT);
    }
}

enum ActivityCommandName {
    NONE,
    SET_ACTIVE,
    CHALLENGE,
    ENABLE_DISABLE,
}