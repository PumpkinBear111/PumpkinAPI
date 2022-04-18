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
     */ abstract String adminPermission();
    /**
     * @return the color for headings and subheadings in commands
     */ abstract ChatColor primaryColor();
    /**
     * @return the type of command for enabling or disabling stuff
     */ abstract ActiveCommandName setActiveCommand();

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
                    if (sender.hasPermission(adminPermission())) {
                        if (args.length >= 2) {
                            switch (args[1]) {
                                case "help": AdminHelp(sender);
                                case "set_active": ChangeActivity(sender, ActiveCommandName.SET_ACTIVE);
                                //case "challenge": ChangeActivity(sender, ActiveCommandName.CHALLENGE);
                                case "enable": ChangeActivity(sender, ActiveCommandName.ENABLE_DISABLE);
                                case "disable": ChangeActivity(sender, ActiveCommandName.ENABLE_DISABLE);
                                default: AdminHelp(sender);
                            }
                        } else UnavailableAdminArgument(sender);
                    } else UnavailableArgument(sender);
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
            if (sender.hasPermission(adminPermission())) arguments.add("admin");
        }
        else if (args.length == 2 && args[0].toLowerCase(Locale.ROOT).equals("admin") && sender.hasPermission(adminPermission())) {
            arguments.add("help");
            if (setActiveCommand() == ActiveCommandName.SET_ACTIVE && plugin.active) arguments.add("set_active");
            if (setActiveCommand() == ActiveCommandName.SET_ACTIVE && !plugin.active) arguments.add("set_inactive");
            //if (setActiveCommand() == ActiveCommandName.CHALLENGE) arguments.add("challenge");
            if (setActiveCommand() == ActiveCommandName.ENABLE_DISABLE && plugin.active) arguments.add("disable");
            if (setActiveCommand() == ActiveCommandName.ENABLE_DISABLE && !plugin.active) arguments.add("enable");
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
    private void UnavailableAdminArgument(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "[" + primaryColor() + plugin.getName() + ChatColor.GOLD + "]: " + ChatColor.WHITE + "Please select an argument!");
        sender.sendMessage(ChatColor.GOLD + "Do" + ChatColor.RED + "/" + command.getName() + " admin help " + ChatColor.GOLD + "for a list of commands.");
    }
    private void Help(CommandSender sender) {
        sender.sendMessage(primaryColor() + "-----" + plugin.GetDisplayName() + " Command Help-----");
        sender.sendMessage(primaryColor() + "/" + command.getName() + " help" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get a list of commands.");
        sender.sendMessage(primaryColor() + "/" + command.getName() + " about" + ChatColor.GRAY + " - " + ChatColor.WHITE + "For plugin information");
        if (sender.hasPermission(adminPermission())) sender.sendMessage(ChatColor.RED + "/" + command.getName() + " admin" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Manage " + plugin.GetDisplayName() + ".");
    }
    private void AdminHelp(CommandSender sender) {
        sender.sendMessage(primaryColor() + "-----" + plugin.GetDisplayName() + ChatColor.RED + " Admin" + primaryColor() + " Help-----");
        sender.sendMessage(primaryColor() + "/" + command.getName() + " admin help" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get a list of admin commands.");
        if (setActiveCommand() == ActiveCommandName.SET_ACTIVE && plugin.active)
            sender.sendMessage(primaryColor() + "/" + command.getName() + " admin set_inactive" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Disable the plugin.");
        if (setActiveCommand() == ActiveCommandName.SET_ACTIVE && !plugin.active)
            sender.sendMessage(primaryColor() + "/" + command.getName() + " admin set_active" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Enable the plugin.");
        if (setActiveCommand() == ActiveCommandName.ENABLE_DISABLE && plugin.active)
            sender.sendMessage(primaryColor() + "/" + command.getName() + " admin disable" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Disable the plugin.");
        if (setActiveCommand() == ActiveCommandName.ENABLE_DISABLE && !plugin.active)
            sender.sendMessage(primaryColor() + "/" + command.getName() + " admin enable" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Enable the plugin.");
    }
    private void About(CommandSender sender) {
        throw new NotImplementedException();
    }
    private void ChangeActivity(CommandSender sender, ActiveCommandName type) {
        throw new NotImplementedException();
    }
}

/**
 * This can be NONE, SET_ACTIVE, CHALLENGE, or ENABLE_DISABLE. Used to set the form that the enable/disable command will show as.
 * @author Finn
 */
enum ActiveCommandName {
    NONE,
    SET_ACTIVE,
    //CHALLENGE,
    ENABLE_DISABLE,
}