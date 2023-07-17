package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ifarded.lol.ifu.IFPlayer;

public class PlayerDeath implements Listener {
   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent e) {
      IFPlayer player = new IFPlayer(e.getEntity());
      if (player.isSitting()) {
         player.setSitting(false);
      }

   }
}