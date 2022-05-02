package finn.creates.pumpkin;

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
     */ public abstract String adminPermission();
    /**
     * @return the color for headings and subheadings in commands
     */ public abstract ChatColor primaryColor();
    /**
     * @return the type of command for enabling or disabling stuff
     */ public abstract ActiveCommandName setActiveCommand();

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
            sender.sendMessage(args[0]);
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "help" -> Help(sender);
                case "about" -> About(sender);
                case "admin" -> {
                    if (sender.hasPermission(adminPermission())) {
                        if (args.length >= 2) {
                            switch (args[1]) {
                                case "help" -> AdminHelp(sender);
                                case "set_inactive" -> ChangeActivity(sender, ActiveCommandName.SET_ACTIVE, false);
                                case "set_active" -> ChangeActivity(sender, ActiveCommandName.SET_ACTIVE, true);
                                    //case "challenge": ChangeActivity(sender, ActiveCommandName.CHALLENGE);
                                case "disable" -> ChangeActivity(sender, ActiveCommandName.ENABLE_DISABLE, false);
                                case "enable" -> ChangeActivity(sender, ActiveCommandName.ENABLE_DISABLE, true);
                                default -> AdminHelp(sender);
                            }
                        } else UnavailableAdminArgument(sender);
                    } else UnavailableArgument(sender);
                }
                default -> UnavailableArgument(sender);
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
        sender.sendMessage(primaryColor() + "-----" + plugin.getDisplayName() + " Command Help-----");
        sender.sendMessage(primaryColor() + "/" + command.getName() + " help" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get a list of commands.");
        sender.sendMessage(primaryColor() + "/" + command.getName() + " about" + ChatColor.GRAY + " - " + ChatColor.WHITE + "For plugin information");
        if (sender.hasPermission(adminPermission())) sender.sendMessage(ChatColor.RED + "/" + command.getName() + " admin" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Manage " + plugin.getDisplayName() + ".");
    }
    private void AdminHelp(CommandSender sender) {
        sender.sendMessage(primaryColor() + "-----" + plugin.getDisplayName() + ChatColor.RED + " Admin" + primaryColor() + " Help-----");
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
        sender.sendMessage(primaryColor() + "-----" + plugin.getDisplayName() + "-----");
        sender.sendMessage(ChatColor.WHITE + plugin.getPluginDescription());
        sender.sendMessage(primaryColor() + "Type " + ChatColor.GOLD + "/" + command.getName().toLowerCase(Locale.ROOT) + " help" + primaryColor() + " for help.");
    }
    private void ChangeActivity(CommandSender sender, ActiveCommandName type, boolean to) {
        if (plugin.active == to) {
            if (to) sender.sendMessage(ChatColor.GOLD + "This hasn't been changed.");
        } else {
            plugin.active = to;
            if (to) sender.sendMessage(ChatColor.GREEN + "Enabled!");
            if (to) sender.sendMessage(ChatColor.RED + "Disabled.");
        }
    }
}