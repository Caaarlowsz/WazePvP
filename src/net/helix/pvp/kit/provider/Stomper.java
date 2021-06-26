package net.helix.pvp.kit.provider;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Stomper extends KitHandler {
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}

		if (event.getDamage() >= 4.0D) {
			player.getNearbyEntities(5.0, 5.0, 5.0).stream().filter(
					entity -> entity.getType().equals(EntityType.PLAYER) 
						&& KitManager.getPlayer(entity.getName()).hasKit()
			).forEach(entity -> {
				Player target = (Player) entity;
				target.damage(target.isSneaking() ? 6.0D : event.getDamage(), player);
			});
		}
		player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
		event.setDamage(4.0D);
	}
}
