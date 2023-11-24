package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;
import java.io.IOException;

public class LavaIniciar
implements CommandExecutor
{

	
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("lavainiciar"))
  {
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cVocê não tem permissão!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("Um evento já está ocorrendo!");
		  return true;
	  }
	  p.sendMessage("§aComeçando evento lava"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cO evento LAVA vai começar.");
      Bukkit.broadcastMessage("§cEscreva /event join para entrar");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento começara em 4 minutes");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
				 for (Player p1 : Bukkit.getOnlinePlayers()) {
				      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				      }
			}
		}, 1200L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento começara em 3 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
				 for (Player p1 : Bukkit.getOnlinePlayers()) {
				      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				      }
			}
		}, 2400L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento começara em 2 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
			}
		}, 3600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento começara 1 minuto");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
			}
		}, 4800L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("Lava");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			});
			 	Bukkit.broadcastMessage("§cTeleportando pessoas para o evento!");
			 	EventoType evento = EventoType.getEventoByName("Lava");
			    Bukkit.broadcastMessage("§aIniciando a explicação do evento §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
                EventoUtils.started = true;
			}}, 6000L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§c§lO evento iniciou!");
				Bukkit.broadcastMessage("§c§lBoa sorte!");
				EventoUtils.getEventoPlayers().forEach(p2 -> {
		EventoUtils.pvp = false;
     	EventoUtils.damage = true;
     	EventoUtils.build = false;
     	EventoUtils.tp = false;
     	p2.playSound(p2.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
    		
    		
    		p2.getInventory().setHeldItemSlot(0);
    		ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
    		
  		  
  		  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
  		  
  		  ItemStack item = new ItemStack(Material.BOWL, 64);
  		  p2.getInventory().setItem(14, vermelho);
  		  p2.getInventory().setItem(15, marrom);
  		  p2.getInventory().setItem(13, item);
  		p2.getInventory().setItem(23, vermelho);
		  p2.getInventory().setItem(24, marrom);
		  p2.getInventory().setItem(22, item);
  		  for (int i = 0; i < 33; i++) {
  				p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
  			}
    		p2.updateInventory();
			}); {
			}}}, 7200L);
	}
  Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
		public void run() {
			if (!EventoUtils.evento || !EventoUtils.started) {
				  return;
			  }
			EventoUtils.getEventoPlayers().forEach(p ->  {
				if (p != null && EventoUtils.getEventoPlayers().size() != 0) {
            	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
                HelixWarp.SPAWN.send(p);
                p.chat("/spawn");
                p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
                
                if (EventoUtils.getEventoPlayers().size() == 1) {
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aGanhador: " + EventoUtils.getEventoPlayersNames());
                	p.setHealth(20);
                	p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
                }
                p.sendMessage("§cO evento acabou porque o tempo expirou.");
                p.sendMessage("§cDuração: §a40 minutos");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 48000L);
  
return false;
      };
      {

}
{

}
}
