package ifarded.lol.ifu.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import ifarded.lol.ifu.IFPlayer;

public class PlayerTeleport implements Listener {
   @EventHandler
   public void onPlayerTeleport(PlayerTeleportEvent e) {
      IFPlayer player = new IFPlayer(e.getPlayer());
      if (player.isSitting()) {
         player.setSitting(false);
      }

   }

   @EventHandler
   public void onPlayerDismount(EntityDismountEvent event) {
      if (event.getEntity() instanceof Player) {
         IFPlayer player = new IFPlayer((Player)event.getEntity());
         if (player.isSitting()) {
            player.setSitting(false);
         }
      }

   }
}