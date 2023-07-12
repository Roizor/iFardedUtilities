package ifarded.lol.ifu.cmds;

import java.util.List;

import ifarded.lol.ifu.IFUtilities;
import net.kyori.adventure.text.Component;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Civilization implements CommandExecutor, TabCompleter {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(Component.text(
		IFUtilities.PREFIX + "Sending you to the civilization!"));
		Player p = (Player) sender;
		p.teleport(new Location(p.getWorld(), -80, 64, 92));
		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return null;
	}
}
