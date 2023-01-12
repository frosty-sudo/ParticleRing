package particle.plugin;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	
	public static ParticleRing ring = null;
	
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	}
	
	
	
	boolean cando = true;
	int speed = 3;
	int numberOfPoints = 10;
	int height = 1;
	static int blocksAway = 3;
	
	
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
    	
    	Player player = Bukkit.getPlayer(sender.getName());
    	
    	
        if (command.getName().equalsIgnoreCase("frosty")) {
        	Location loc = player.getLocation();
        	ParticleRing ring = new ParticleRing(speed, numberOfPoints, height, blocksAway, player, this);
        	return true;
        	
        }

    	
    	
    	
    	return false;
    }
}
