package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import ifarded.lol.ifu.IFArmorStand;

public class PlayerArmorStandManipulate implements Listener {
   @EventHandler
   public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
      IFArmorStand ifas = new IFArmorStand(e.getRightClicked());
      if (ifas.isSeat()) {
         e.setCancelled(true);
      }

   }
}
