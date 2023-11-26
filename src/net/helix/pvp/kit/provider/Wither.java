package net.helix.pvp.kit.provider;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;


public class Wither extends KitHandler { 

	public HelixPvP plugin;
	private Object e;
	public static ArrayList<String> Tempo = new ArrayList();
	
	@Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.SKULL_ITEM)
                .displayName("§8Wither Item")
                .nbt("cancel-drop")
                .nbt("kit-handler", "wither")
                .toStack()
        );
    }
	@EventHandler
	
	  
	  public void clicar(PlayerInteractEvent e)
	  {
	    ItemStack especial = new ItemStack(Material.SKULL_ITEM);
	    ItemMeta especial1 = especial.getItemMeta();
	    especial1.setDisplayName("§8Wither Item");
	    especial.setItemMeta(especial1);
	    final Player p = e.getPlayer();
	    if ((e.getPlayer().getItemInHand().isSimilar(especial)) && (KitManager.getPlayer(p.getName()).hasKit(this))){
	    	if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || 
	    	        (e.getAction() == Action.RIGHT_CLICK_AIR)) {
	    	        e.setCancelled(true);
	    	      }
	    
	    	      if (inCooldown(p)) {
	    	      
	    	        sendMessageCooldown(p);
	    	        return;
	    	      }
	    	      Location loc = p.getLocation();
	    	      loc.setY(loc.getY() + 2.5D);
	    	      Entity witherhead = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.WITHER_SKULL);
	    	      Entity witherhead2 = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.WITHER_SKULL);
	    	      Entity witherhead3 = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.WITHER_SKULL);
	    	      Entity witherhead4 = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.WITHER_SKULL);
	    	      Entity witherhead5 = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.WITHER_SKULL);
	    	      witherhead.setVelocity(p.getEyeLocation().getDirection().multiply(2.0D));
	    	      witherhead2.setVelocity(p.getEyeLocation().getDirection().multiply(2.0D));
	    	      witherhead3.setVelocity(p.getEyeLocation().getDirection().multiply(2.0D));
	    	      witherhead4.setVelocity(p.getEyeLocation().getDirection().multiply(2.0D));
	    	      witherhead5.setVelocity(p.getEyeLocation().getDirection().multiply(2.0D));
	    	      for (Entity ent : p.getNearbyEntities(5.0, 5.0, 5.0)) {
	    	  		/*  50 */           if ((ent instanceof Player))
	    	  		/*     */           {
	    	  		/*  52 */             Player plr = (Player)ent;
	    	  		Kangaroo.darEfeito(plr, PotionEffectType.WITHER, 5, 1);
	    	  		}
	    	      }
					p.sendMessage(ChatColor.GREEN + "Você usou o kit Wither!");
	    	      addCooldown(p, 40);
	  
	    	      
				
	    	      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
	    	    	  
	    	    	  
	    	      
					@Override
					public void run() {

							p.sendMessage(ChatColor.GREEN + "Você ja pode usar o Wither novamente!");
						}
					}
				, 10 * 40L);
	    	      
	  
}
	  }
	  
}

	

