package ifarded.lol.ifu.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.kyori.adventure.text.Component;

public class PlayerStopSittingEvent extends Event {
   private static final HandlerList handlers = new HandlerList();
   private Player player;
   private ArmorStand seat;
   private Component message;

   public PlayerStopSittingEvent(Player player, ArmorStand seat, Component message) {
      this.player = player;
      this.seat = seat;
      this.message = message;
   }

   public Player getPlayer() {
      return this.player;
   }

   public ArmorStand getSeat() {
      return this.seat;
   }

   public Component getMessage() {
      return this.message;
   }

   public void setMessage(Component message) {
      this.message = message;
   }

   public HandlerList getHandlers() {
      return handlers;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }
}
