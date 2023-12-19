package ifarded.lol.ifu;

import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

public class IFArmorStand {
   private IFUtilities ifu;
   private ArmorStand armorstand;

   public IFArmorStand(ArmorStand armorStand) {
      this.armorstand = armorStand;
      this.ifu = (IFUtilities)JavaPlugin.getPlugin(IFUtilities.class);
   }

   public boolean isSeat() {
      return this.ifu.getSeats().containsValue(this.armorstand);
   }

   public ArmorStand getBukkitArmorStand() {
      return this.armorstand;
   }
}