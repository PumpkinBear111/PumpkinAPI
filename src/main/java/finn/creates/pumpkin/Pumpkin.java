package finn.creates.pumpkin;

import org.bukkit.ChatColor;

public final class Pumpkin extends PumpkinPlugin {
    @Override public void onAwake() {

    }
    @Override public void onClose() {

    }

    @Override public String loggingDisplayName() {
        return "Pumpkin";
    }

    @Override public CommandManager commandManager() {
        return new CommandManager() {
            @Override
            String adminPermission() {
                return "finn.creates.pumpkin.admin";
            }

            @Override
            ChatColor primaryColor() {
                return ChatColor.GOLD;
            }

            @Override
            ActiveCommandName setActiveCommand() {
                return ActiveCommandName.NONE;
            }
        };
    }

    @Override public String commandName() {
        return "pumpkin";
    }

}
