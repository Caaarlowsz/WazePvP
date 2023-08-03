package net.helix.pvp.kit.provider;


	import java.util.ArrayList;
	import java.util.Arrays;
	import org.bukkit.Material;
	import org.bukkit.entity.Entity;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.entity.EntityDamageEvent;
	import org.bukkit.event.player.PlayerInteractEvent;
	import org.bukkit.event.player.PlayerMoveEvent;
	import org.bukkit.plugin.Plugin;
	import org.bukkit.scheduler.BukkitRunnable;
	import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

	public class Deshfire extends KitHandler {
	  ArrayList<String> fall;

	  
	  @EventHandler
	  public void onDeshfireClick(PlayerInteractEvent event) {
	    final Player p = event.getPlayer();
	    if (p.getItemInHand() == null)
	      return; 
	    if (p.getItemInHand().getType() != Material.REDSTONE_BLOCK)
	      return; 
	    if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)) {
	    	return;
	    }
	    if (net.helix.pvp.cooldown1.HelixCooldown2.hasCooldown(p, KitManager.getPlayer(p.getName()).getKit().getName())) {
	        sendMessageCooldown(p);
	        return;
	      }
	    else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
			p.sendMessage("§cNão use o Desh no spawn!");
			return;
		 }
	      event.setCancelled(true);
	      addCooldown(p, 35);
	      /*  76 */       p.setVelocity(p.getEyeLocation().getDirection().multiply(6).add(new Vector(0, 0, 0)));
	      for (Entity pertos : p.getNearbyEntities(8.0D, 8.0D, 8.0D)) {
	    	  /*  80 */         if ((pertos instanceof Player))
	    	  /*     */         {
	    	  /*  82 */           Player perto = (Player)pertos;
	    	  /*  83 */           ((Player)pertos).damage(4.0D);
	    	  /*  84 */           pertos.setVelocity(new Vector(0.1D, 0.0D, 0.1D));
	    	  /*  85 */           pertos.setFireTicks(250);
	    	  /*     */         }
	      p.setLastDamageCause(new EntityDamageEvent((Entity)p, EntityDamageEvent.DamageCause.CUSTOM, 0));
	      }
	  }
	
	  
	  
	  }
	  
	