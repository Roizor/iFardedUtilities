package ifarded.lol.ifu.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ServerPingListener implements Listener {
	@EventHandler
	public void onServerPing(final ServerListPingEvent p) {
		p.motd(
				Component.text("         ")
						.append(
							Component.text("iFarded Industries ")
							.color(TextColor.color(TextColor.fromHexString("#00FF00")))
						)
						.append(
								Component.text("[1.9 - 1.20]")
										.color(TextColor.fromHexString("#FF0000"))
						)
						.appendNewline()
						.append(Component.text("        "))
						.append(
								Component.text("GRIEFING IS A BANNABLE")
										.color(TextColor.fromHexString("#00AAAA"))
										.decorate(TextDecoration.BOLD)
						)
						.append(Component.text(" - "))
						.append(
							Component.text("OFFENSE")
							.color(TextColor.fromHexString("#FFFF55"))
							.decorate(TextDecoration.BOLD)
						)
				);
	}
}
