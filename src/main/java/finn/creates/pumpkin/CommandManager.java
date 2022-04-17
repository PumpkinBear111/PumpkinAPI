package finn.creates.pumpkin;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This is used to easily manage the commands.
 * @author Finn
 */
public abstract class CommandManager implements CommandExecutor, TabCompleter {
    /**
     * @return the name of the permission node for admin commands to be visible.
     */ abstract String adminPermissions();
    /**
     * @return the color for headings and subheadings in commands
     */ abstract ChatColor primaryColor();
    /**
     * @return the type of command for enabling or disabling stuff
     */ abstract ActivityCommandName alwaysActive();

    /**
     * The main PumpkinPlugin instance
     */ public PumpkinPlugin plugin = null;
    /**
     * An instance of the command for reference
     */ public PluginCommand command = null;

    /**
     * On command sent
     */ @Override public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            UnavailableArgument(sender);
        } else {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "help": Help(sender);
                case "about": About(sender);
                case "admin": {
                    if (sender.hasPermission(adminPermissions())) {

                    }
                    else UnavailableArgument(sender);
                }
                default: UnavailableArgument(sender);
            }
        }
        return true;
    }

    /**
     * On command argument autocomplete
     */ @Override public final List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("help");
            arguments.add("about");
            if (sender.hasPermission(plugin.adminPermission())) arguments.add("admin");
        }
        if (args.length == 2 && args[1].toLowerCase(Locale.ROOT).equals("admin")) {
            arguments.add("help");
            arguments.add("about");
            if (alwaysActive() == ActivityCommandName.SET_ACTIVE) arguments.add("set_active");
            if (alwaysActive() == ActivityCommandName.CHALLENGE) arguments.add("challenge");
            if (alwaysActive() == ActivityCommandName.ENABLE_DISABLE && plugin.active) arguments.add("disable");
            if (alwaysActive() == ActivityCommandName.ENABLE_DISABLE && !plugin.active) arguments.add("enable");
        }
        return arguments;
    }

    /**
     * @return the name of the command
     */ @Override public final String toString() {
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

/**
 * This can be NONE, SET_ACTIVE, CHALLENGE, or ENABLE_DISABLE. Used to set the form that the enable/disable command will show as.
 * @author Finn
 */
enum ActivityCommandName {
    NONE,
    SET_ACTIVE,
    CHALLENGE,
    ENABLE_DISABLE,
}