package ifarded.lol.ifu;

import java.util.ArrayList;
import java.util.List;

import ifarded.lol.ifu.listeners.OpListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class IFUCmd implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(IFUtilities.PREFIX + "Not enough arguments!");
            sender.sendMessage(IFUtilities.PREFIX + "Try /ifu <help|reload>");
            return true;
        }
        switch (args[0]) {
            case "reload":
            case "rl":
                rl(sender);
                return true;
            case "help":
                sender.sendMessage(IFUtilities.PREFIX);
                sender.sendMessage("IFU v" + (IFUtilities.getPlugin()).CURRENT_VERSION);
                sender.sendMessage("/ifu reload Reloads the config");
                return true;
        }
        sender.sendMessage(IFUtilities.PREFIX + "Command does not exist!");
        sender.sendMessage("Do /ifu help");
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> commands = new ArrayList<>();
            if (sender.hasPermission("ifu.reload") &&
                    sender.isOp() && IFUtilities.getPlugin().getConfig().getBoolean("op-can-use-plugin")) {
                commands.add("rl");
                commands.add("reload");
            }
            return commands;
        }
        return null;
    }

    private static void rl(CommandSender sender) {
        if (sender.hasPermission("ifu.reload")) {
            IFUtilities.getPlugin().createConfig();
            IFUtilities.getPlugin().reloadConfig();
            OpListener.allowedOperators = IFUtilities.getPlugin().getConfig().getStringList("allowed-operators");
            sender.sendMessage(IFUtilities.PREFIX + "Reloaded config!");
            IFUtilities.checkGroups();
            Bukkit.getOnlinePlayers().forEach(Player::updateCommands);
        }
    }
}
