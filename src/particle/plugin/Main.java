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
	
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	}
	
	
	double heightCounter = 0;
	boolean cando = true;
	int speed = 3;
	int numberOfPoints = 10;
	int height = 1;
	int blocksAway = 3;
	
	public int calc_b(int degree) {
		double procent = (double) (degree) / 360;
		//Bukkit.broadcastMessage("Procent is " + procent);
		if (procent >= 0.3333333 && procent <= 1) {
			return (int) (255*(Math.abs(-1*Math.abs(-1*Math.abs(Math.abs(3*procent)-1)+1)+1)));
		}
		else {
			return 0;
		}
	}
	
	public int calc_g(int degree) {
		double procent = (double) (degree) / 360;
		if (procent >= 0 && procent <= 0.66666666) {
			return (int) (255*(Math.abs(-1*Math.abs(-1*Math.abs(Math.abs((3*procent)-1)-1)+1)+1)));
		}
		else return 0;
	}
	
	public int calc_r(int degree) {
		double procent = (double) (degree) / 360;
		if (procent >= 0.66666 && procent <= 1) {
			return (int) (255*(Math.abs(-1*Math.abs(-1*Math.abs(Math.abs((3*procent)-1)-1)+1)+1)));
		}
		else if (procent >= 0 && procent <= 0.33333) {
			return (int) (255*(Math.abs(-1*Math.abs(-1*Math.abs(Math.abs(3*procent)-1)+1)+1)));
		}
		else {
			return 0;
		}
	}
	int speedCounter = 0;
	
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
    	Player player = Bukkit.getPlayer(sender.getName());
    	
    	
        if (command.getName().equalsIgnoreCase("frosty")) {
        	Location loc = player.getLocation();
        	new BukkitRunnable() {
        		@Override
        		public void run() {
        			speedCounter+=speed;
        			heightCounter+=0.005;
        			//Bukkit.broadcastMessage((-4*Math.pow(height/100-0.5, 2)+1)*10+"");
        			if (heightCounter >= height) { heightCounter = 0; }
        			if (speedCounter >= 360/numberOfPoints) { speedCounter = 0; }
        			for (int i = 0; i<360; i+=360/numberOfPoints) {
        				//Bukkit.broadcastMessage("Blue is " + calc_b(i+speedCounter) + " and procent is " + ((double)(i+speedCounter)/360));
                    	player.spawnParticle(Particle.REDSTONE, loc.clone().add(blocksAway*Math.cos(Math.toRadians(i+speedCounter)), (-(Math.cos(2*Math.PI*heightCounter)*0.5+0.5)+1)*2, blocksAway*Math.sin(Math.toRadians(i+speedCounter))), 0, new Particle.DustOptions(
                    			Color.fromRGB(calc_r(i+speedCounter), calc_g(i+speedCounter), calc_b(i+speedCounter)), 1));
        			}
        			//cancel();
        		}
        	}.runTaskTimer(instance, 1, 0);
        	return true;
        }

    	
    	
    	
    	return false;
    }

    public class TabCompletion implements TabCompleter{
        @Override
        public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
            if(cmd.getName().equalsIgnoreCase("frosty") && args.length >= 0){
                if(sender instanceof Player){
                    Player player = (Player) sender;

                    List<String> list = new ArrayList<>();
                    list.add("help");

                    if(player.hasPermission("RELOAD")) {
                        list.add("reload");
                    }
                    return list;

                }
            }
            return null;
        }
    }
}
