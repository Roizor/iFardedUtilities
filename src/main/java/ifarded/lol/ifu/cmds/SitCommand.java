package ifarded.lol.ifu.cmds;

import net.kyori.adventure.text.Component;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ifarded.lol.ifu.IFPlayer;
import ifarded.lol.ifu.IFUtilities;
import ifarded.lol.ifu.util.IFDeco;

public class SitCommand implements CommandExecutor {
   private IFUtilities ifu;

   public SitCommand(IFUtilities ifu) {
      this.ifu = ifu;
   }

   public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage(Component.text("Only players can run this command!").color(IFDeco.RED));
         return false;
      } else if (!sender.hasPermission(this.ifu.getSitPermission())) {
         sender.sendMessage(Component.text("You don't have permission to use this command!").color(IFDeco.RED));
         return false;
      } else {
         IFPlayer player = new IFPlayer((Player)sender);
         if (player.isSitting()) {
            player.setSitting(false);
         } else if (player.getBukkitPlayer().isOnGround()) {
            player.setSitting(true);
         } else {
            player.getBukkitPlayer().sendMessage(this.ifu.getSitFailMessage());
         }

         return true;
      }
   }
}
