package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

public class OneVsOneIniciar
implements CommandExecutor, Listener
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;

  
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cVocê não tem permissão!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("A event is already occouring!");
		  return true;
	  }
	  else if (HelixPvP.getInstance().getEventManager().isRunningRDM()) {
       p.sendMessage("The 1v1 event is already occouring");
       return true;
	  
  } else {
	  p.sendMessage("§aStarting event 1v1"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cO evento 1v1 foi iniciado.");
      Bukkit.broadcastMessage("§cUse /event join para entrar");
      Bukkit.broadcastMessage("§cO evento começara em 2 minutos.");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("1v1");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			      p2.sendMessage("O evento vai começar");
			});
			 	Bukkit.broadcastMessage("§cTeleportando pessoas para o evento!");
			 	EventoType evento = EventoType.getEventoByName("1v1");
			 
			    Bukkit.broadcastMessage("§aIniciando explicação do evento §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
                EventoUtils.started = true;
			}}, 1200L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoUtils.pvp = true;
				 EventoUtils.damage = true;
				 EventoUtils.build = false;
				 EventoUtils.tp = false;
				Bukkit.broadcastMessage("§c§lO evento vai começar!");
				Bukkit.broadcastMessage("§c§lBoa sorte!");
			 	HelixPvP.getInstance().getEventManager().setRdmAutomatic(new RDMAutomatic());	
		 		Bukkit.getConsoleSender().sendMessage("RDM Automatic iniciado");
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
			 		 if (!RDMAutomatic.iniciou) {
			 		RDMAutomatic.iniciou = true;
			 		HelixPvP.getInstance().getEventManager().getRdmAutomatic().setGameType(RDMAutomatic.GameType.STARTING);
			 		Bukkit.getConsoleSender().sendMessage("Variavel de inicio do evento 1v1 setada como true");
			 		 }
			 		
			 		
			 		
              
			 	 
			
			 	 });
			}}, 1800L);
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
                if (HelixPvP.getInstance().getEventManager().isRunningRDM()) {
                	HelixPvP.getInstance().getEventManager().setRdmAutomatic(null);
                }
                p.sendMessage("§cO evento acabou porque o tempo expirou.");
                p.sendMessage("§cDuração: §a1 hora e 20 minutos");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 96000L);
  
return false;
      };
      {


}
{

}
}

