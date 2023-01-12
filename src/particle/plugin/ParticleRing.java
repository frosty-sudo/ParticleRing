package particle.plugin;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleRing {

	private double heightCounter;
	private int speed;
	private int numberOfPoints;
	private int height;
	private int blocksAway;
	private int speedCounter;
	private Player player;
	private final Main plugin;



	public ParticleRing(int speed, int numberOfPoints, int height, int blocksAway, Player player, Main plugin) {
		this.plugin = plugin;
		this.speed = speed;
		this.numberOfPoints = numberOfPoints;
		this.height = height;
		this.blocksAway = blocksAway;
		this.player = player;
	
		
		new BukkitRunnable() {
			@Override
			public void run() {
				Location loc = player.getLocation();
				speedCounter+=speed;
				heightCounter+=0.005;
				//Bukkit.broadcastMessage((-4*Math.pow(height/100-0.5, 2)+1)*10+"");
				if (heightCounter >= height) { heightCounter = 0; }
				if (speedCounter >= 360/numberOfPoints) { speedCounter = 0; }
				for (int i = 0; i<360; i+=360/numberOfPoints) {
					//Bukkit.broadcastMessage("Blue is " + calc_b(i+speedCounter) + " and procent is " + ((double)(i+speedCounter)/360));
	            	player.spawnParticle(Particle.REDSTONE, loc.clone().add(Main.blocksAway*Math.cos(Math.toRadians(i+speedCounter)), (-(Math.cos(2*Math.PI*heightCounter)*0.5+0.5)+1)*2, Main.blocksAway*Math.sin(Math.toRadians(i+speedCounter))), 0, new Particle.DustOptions(
	            			Color.fromRGB(calc_r(i+speedCounter), calc_g(i+speedCounter), calc_b(i+speedCounter)), 1));
				}
				//cancel();
			}
		}.runTaskTimer(plugin, 1, 0);
		
	
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBlocksAway() {
		return blocksAway;
	}

	public void setBlocksAway(int blocksAway) {
		this.blocksAway = blocksAway;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

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

}
