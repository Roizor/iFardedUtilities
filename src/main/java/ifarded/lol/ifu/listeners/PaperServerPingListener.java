package ifarded.lol.ifu.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PaperServerPingListener implements Listener{
    @EventHandler
    public void onPaperServerListPing(PaperServerListPingEvent event){
      event.setVersion("IFARDED!");
    }
}