package particle.plugin;

import java.beans.EventSetDescriptor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Listeners implements Listener {
	
	
	public static int blockDistance;
	
	
	private final Main plugin;
	
	public Listeners(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onHotbarClick(PlayerDropItemEvent e) {
		Bukkit.broadcastMessage("Dropped");
		
		
		
		
		
		if(e.getItemDrop().getItemStack().getType().equals(Material.BLAZE_ROD)) {
			e.setCancelled(true);
			if(blockDistance == 0) {
				Main.blocksAway = 4;
				blockDistance++;
			} else if (blockDistance == 1) {
				Main.blocksAway = 6;
				blockDistance++;
			} else {
				Main.blocksAway = 3;
				blockDistance = 0;
			}
		}
	}
	
	
	
}