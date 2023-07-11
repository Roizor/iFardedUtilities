package ifarded.lol.ifu.listeners;

import java.util.List;

import ifarded.lol.ifu.IFUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onLoginOpCheck(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (p.isOp() && IFUtilities.getPlugin().getConfig().getBoolean("op-protection")) {
            List<String> allowedOperatorList = IFUtilities.getPlugin().getConfig().getStringList("allowed-operators");
            if (!allowedOperatorList.contains(p.getName())) {
                String message = IFUtilities.getColoredConfigString("unauthorized-operator-kick-message");
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(message);
                IFUtilities.getPlugin().getServer().getConsoleSender().sendMessage(message);
                p.setOp(false);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("Welcome to the iFarded Industries Minecraft Server!!!");
    }
}
