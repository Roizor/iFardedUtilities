package ifarded.lol.ifu.listeners;

import ifarded.lol.ifu.IFUtilities;
import ifarded.lol.ifu.util.CommandBlocker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] command = e.getMessage().split(" ");
        if (command[0].equalsIgnoreCase("/pl") || command[0].equalsIgnoreCase("/bukkit:pl") || command[0].equalsIgnoreCase("/plugins") || command[0].equalsIgnoreCase("/bukkit:plugins")) {
            if (!p.hasPermission("*") && !p.hasPermission("ifu.bypass")) {
                String message = IFUtilities.getColoredConfigString("/pl-message");
                if (!message.equals("none"))
                    p.sendMessage(message);
                e.setCancelled(true);
                return;
            }
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/ifu-ifo")) {
            e.setCancelled(true);
            p.sendMessage(IFUtilities.PREFIX + "iFU Version " + IFUtilities.getPlugin().CURRENT_VERSION + " by roighteously");
            return;
        }
        if (CommandBlocker.isBlocked(e.getMessage(), IFUtilities.getGroup(p))) {
            String message = IFUtilities.getColoredConfigString("blocked-command-message");
            if (!message.equals("none"))
                p.sendMessage(message);
            e.setCancelled(true);
        }
    }
}
