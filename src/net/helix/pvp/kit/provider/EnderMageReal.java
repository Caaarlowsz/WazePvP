package net.helix.pvp.kit.provider;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class EnderMageReal extends KitHandler {


public void onKitEndermage(Location portal, Player p1, Player p2) {
    p1.teleport(portal.clone().add(0.0D, 1.0D, 0.0D));
    p2.teleport(portal.clone().add(0.0D, 1.0D, 0.0D));
    p1.setNoDamageTicks(100);
    p2.setNoDamageTicks(100);
    p2.sendMessage(ChatColor.GREEN + "Você foi puxado por um Endermage");
    p1.sendMessage(ChatColor.GREEN + "Você puxou players com seu Endermage");
    p2.getWorld().playEffect(p2.getLocation(), Effect.ENDER_SIGNAL, 9);
    p1.getWorld().playEffect(portal, Effect.ENDER_SIGNAL, 9);
    p2.playSound(p2.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
    p1.playSound(portal, Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
  }
  
  private boolean isEnderable(Location portal, Location player) {
    return (Math.abs(portal.getX() - player.getX()) < 2.5D && Math.abs(portal.getZ() - player.getZ()) < 2.5D && 
      Math.abs(portal.getY() - player.getY()) > 3.0D);
  }
  
  @EventHandler
  public void onKitEndermage(PlayerInteractEvent e) {
    final Player mage = e.getPlayer();
    if (!KitManager.getPlayer(mage.getName()).hasKit(this)) {
    	
      return; 
    }
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return; 
  }
    if (mage.getItemInHand() == null) {
      return; 
    }
    if (mage.getItemInHand().getType() != Material.ENDER_STONE) {
      return; 
    }
    else if (mage.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
    	mage.sendMessage("§cNão use o EnderMage no spawn!");
		return;
	 }
    e.setCancelled(true);
    mage.setItemInHand(new ItemStack(Material.AIR));
    mage.updateInventory();
    final Block b = e.getClickedBlock();
    final Location bLoc = b.getLocation();
    final Material material = b.getType();
    final BlockState bs = b.getState();
    b.setType(Material.ENDER_STONE);
    (new BukkitRunnable() {
        int time = 5;
        
        public void run() {
          this.time--;
          for (Player target : Bukkit.getOnlinePlayers()) {
            if (target != mage && !target.isDead() && !target.getInventory().contains(Material.PORTAL) && 
              isEnderable(bLoc, target.getLocation())) {
              cancel();
              b.setType(material);
              b.setData(bs.getBlock().getData());
              onKitEndermage(bLoc, mage, target);
              if (!mage.getInventory().contains(Material.ENDER_STONE) && KitManager.getPlayer(mage.getName()).hasKit())
                mage.getInventory().addItem(new ItemStack[] { new ItemStack( Material.ENDER_STONE) }); 
              return;
            } 
          } 
          if (this.time == 0) {
            cancel();
            b.setType(material);
            b.setData(bs.getBlock().getData());
            if (!mage.getInventory().contains(Material.ENDER_STONE) && KitManager.getPlayer(mage.getName()).hasKit())
                mage.getInventory().addItem(new ItemStack[] { new ItemStack( Material.ENDER_STONE) }); 
          } 
        }
      }).runTaskTimer(HelixPvP.getInstance(), 0L, 20L);
  }
}