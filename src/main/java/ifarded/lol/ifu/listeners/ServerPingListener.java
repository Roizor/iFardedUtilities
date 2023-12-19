package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import ifarded.lol.ifu.IFUtilities;
import ifarded.lol.ifu.util.IFDeco;
import net.kyori.adventure.text.Component;

public class ServerPingListener implements Listener {
	@EventHandler
	public void onServerPing(final ServerListPingEvent p) {
		p.motd(
				Component.text(IFUtilities.getPlugin().getConfig().getString("first-pad"))
						.append(
							Component.text("iFarded Industries ")
							.color(IFDeco.GREEN)
						)
						.append(
								Component.text("[1.9 - 1.20]")
										.color(IFDeco.RED)
						)
						.appendNewline()
						.append(Component.text("        "))
						.append(
								Component.text("GRIEFING IS A")
										.color(IFDeco.GOLD)
										.decorate(IFDeco.BOLD)
						)
						.append(Component.text(" - ")
								.color(IFDeco.GRAY)
								.decorate(IFDeco.BOLD))
						.append(
							Component.text("BANNABLE OFFENSE")
							.color(IFDeco.DARK_AQUA)
							.decorate(IFDeco.BOLD)
						)
				);
	}
}
