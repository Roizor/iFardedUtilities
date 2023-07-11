package ifarded.lol.ifu.listeners;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ifarded.lol.ifu.IFUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandSuggestionListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTab(PlayerCommandSendEvent event) {
        Player p = event.getPlayer();
        if (IFUtilities.getGroup(p) == null)
            return;
        String section = "groups." + IFUtilities.getGroup(p);
        boolean isListBlocking = IFUtilities.getPlugin().getConfig().getString(section + ".group-mode").equalsIgnoreCase("blacklist");
        if (!isListBlocking)
            event.getCommands().clear();
        if (!IFUtilities.getPlugin().getConfig().getStringList(section + ".included-groups").isEmpty())
            for (String includedGroup : IFUtilities.getPlugin().getConfig().getStringList(section + ".included-groups")) {
                for (String cmd : IFUtilities.getPlugin().getConfig().getStringList("groups." + includedGroup + ".commands")) {
                    List<String> cmdArgs = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                    String command = cmdArgs.get(0);
                    if (IFUtilities.getPlugin().getConfig().getString("groups." + includedGroup + ".group-mode").equalsIgnoreCase("blacklist")) {
                        if (isListBlocking)
                            event.getCommands().remove(command);
                        continue;
                    }
                    if (!isListBlocking)
                        event.getCommands().add(command);
                }
            }
        if (isListBlocking) {
            for (String cmd : IFUtilities.getPlugin().getConfig().getStringList(section + ".commands")) {
                List<String> cmdArgs = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                String command = cmdArgs.get(0);
                command = command.replace("%space%", " ");
                event.getCommands().remove(command);
            }
        } else {
            for (String cmd : IFUtilities.getPlugin().getConfig().getStringList(section + ".commands")) {
                List<String> cmdArgs = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                String command = cmdArgs.get(0);
                command = command.replace("%space%", " ");
                event.getCommands().add(command);
            }
        }
    }
}
