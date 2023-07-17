package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ifarded.lol.ifu.IFPlayer;

public class PlayerQuit implements Listener {
   @EventHandler
   public void onPlayerQuit(PlayerQuitEvent e) {
      IFPlayer player = new IFPlayer(e.getPlayer());
      if (player.isSitting()) {
         player.setSitting(false);
      }

   }
}

