package ifarded.lol.ifu.listeners;

import java.util.List;

import ifarded.lol.ifu.IFUtilities;
import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class OpListener implements Listener {
    private BukkitTask opChecker;

    public static List<String> allowedOperators = IFUtilities.getPlugin().getConfig().getStringList("allowed-operators");

    @EventHandler
    public void onOPServer(ServerCommandEvent e) {
        if ((e.getCommand().toLowerCase().startsWith("op ") || e.getCommand().toLowerCase().startsWith("minecraft:op ")) && IFUtilities.getPlugin().getConfig().getBoolean("op-protection"))
            if (isForbiddenOperator(e.getCommand())) {
                e.getSender().sendMessage(IFUtilities.PREFIX + IFUtilities.getColoredConfigString("op-message"));
                        e.setCancelled(true);
            }
    }

    @EventHandler
    public void onOpPlayer(PlayerCommandPreprocessEvent e) {
        if ((e.getPlayer().isOp() || e.getPlayer().hasPermission("*")) && (e.getMessage().toLowerCase().startsWith("/op ") || e.getMessage().toLowerCase().startsWith("/minecraft:op ")) && IFUtilities.getPlugin().getConfig().getBoolean("op-protection") &&
                isForbiddenOperator(e.getMessage())) {
            e.getPlayer().sendMessage(IFUtilities.PREFIX + IFUtilities.getColoredConfigString("op-message"));
                    e.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerStart(ServerLoadEvent e) {
        if (this.opChecker != null)
            return;
        this.opChecker = (new BukkitRunnable() {
            public void run() {
                if (IFUtilities.getPlugin().getConfig().getBoolean("op-protection"))
                    Bukkit.getOperators().forEach(offlinePlayer -> {
                        if (!OpListener.allowedOperators.contains(offlinePlayer.getName())) {
                            offlinePlayer.setOp(false);
                            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null)
                                offlinePlayer.getPlayer().kick(Component.text(IFUtilities.getColoredConfigString("unauthorized-operator-kick-message")));
                        }
                    });
            }
        }).runTaskTimer(IFUtilities.getPlugin(), 300L, 40L);
    }

    private boolean isForbiddenOperator(String command) {
        if (command.charAt(0) == '/')
            command = command.replaceFirst("/", "");
        for (String allowedOperator : allowedOperators) {
            if (command.equalsIgnoreCase("op " + allowedOperator) || command.equalsIgnoreCase("minecraft:op " + allowedOperator))
                return false;
        }
        return true;
    }
}
