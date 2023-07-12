package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import ifarded.lol.ifu.IFColors;
import ifarded.lol.ifu.IFUtilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ServerPingListener implements Listener {
	@EventHandler
	public void onServerPing(final ServerListPingEvent p) {
		p.motd(
				Component.text(IFUtilities.getPlugin().getConfig().getString("first-pad"))
						.append(
							Component.text("iFarded Industries ")
							.color(TextColor.color(TextColor.fromHexString(IFColors.DARK_GREEN)))
						)
						.append(
								Component.text("[1.9 - 1.20]")
										.color(TextColor.fromHexString(IFColors.RED))
						)
						.appendNewline()
						.append(Component.text("        "))
						.append(
								Component.text("GRIEFING IS A BANNABLE")
										.color(TextColor.fromHexString(IFColors.DARK_AQUA))
										.decorate(TextDecoration.BOLD)
						)
						.append(Component.text(" - "))
						.append(
							Component.text("OFFENSE")
							.color(TextColor.fromHexString(IFColors.YELLOW))
							.decorate(TextDecoration.BOLD)
						)
				);
	}
}
