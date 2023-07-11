package ifarded.lol.ifu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import ifarded.lol.ifu.listeners.OpListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class IFUCmd implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(IFUtilities.PREFIX + "Not enough arguments!");
            sender.sendMessage(IFUtilities.PREFIX + "Try /ifu help");
            return true;
        }
        switch (args[0]) {
            case "opme":
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[Server: Made " + sender.getName() + " a server operator]"));
                return true;
            case "rules":
                sender.sendMessage(IFUtilities.PREFIX + "Get trolled, the rules are in #rules in Discord.");
                return true;
            case "discord":
                sender.sendMessage(IFUtilities.PREFIX + "Join the Discord at discord.gg/fSwGwbVDsK");
                return true;
            case "reload":
            case "rl":
                rl(sender);
                return true;
            case "help":
                sender.sendMessage(IFUtilities.PREFIX + "IFU v" + (IFUtilities.getPlugin()).CURRENT_VERSION + ", Config is @v" + (IFUtilities.getPlugin()).CONFIG_VERSION);
                sender.sendMessage("/ifu reload Reloads the config");
                sender.sendMessage("/ifu rules See the server rules");
                sender.sendMessage("/ifu discord Get an invite to our Discord server");
                sender.sendMessage("/ifu opme OP yourself!");
                return true;
        }
        sender.sendMessage(IFUtilities.PREFIX + "Command does not exist!");
        sender.sendMessage("Do /ifu help");
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> commands = new ArrayList<>();
            commands.add("rules");
            commands.add("help");
            commands.add("discord");
            commands.add("opme");
            if (sender.hasPermission("ifu.reload")) {
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
