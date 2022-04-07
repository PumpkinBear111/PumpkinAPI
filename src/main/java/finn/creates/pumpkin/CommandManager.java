package finn.creates.pumpkin;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CommandManager implements CommandExecutor, TabCompleter {
    abstract String adminPermissions();
    abstract ChatColor primaryColor();
    //@Deprecated abstract ChatColor secondaryColor();
    abstract ActivityCommandName alwaysActive();

    public PumpkinPlugin plugin = null;
    public PluginCommand command = null;

    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            UnavailableArgument(sender);
        } else {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "help": Help(sender);
                case "about": About(sender);
                case "admin": {
                    if (sender.hasPermission(adminPermissions())) {}
                    else UnavailableArgument(sender);
                }
                default: UnavailableArgument(sender);
            }
        }
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

    @Override public final String toString() {
        return command.getName().toLowerCase(Locale.ROOT);
    }

    private void UnavailableArgument(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "[" + primaryColor() + plugin.getName() + ChatColor.GOLD + "]: " + ChatColor.WHITE + "Please select an argument!");
        sender.sendMessage(ChatColor.GOLD + "Do" + ChatColor.RED + "/" + command.getName() + " help " + ChatColor.GOLD + "for a list of commands.");
    }

    private void Help(CommandSender sender) {
        throw new NotImplementedException();
    }

    private void About(CommandSender sender) {
        throw new NotImplementedException();
    }
}

enum ActivityCommandName {
    NONE,
    SET_ACTIVE,
    CHALLENGE,
    ENABLE_DISABLE,
}