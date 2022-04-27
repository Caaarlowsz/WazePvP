package net.helix.pvp.warp.provider;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpHandle;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Spawn extends WarpHandle {

    @Override
    public void execute(Player player) {
        Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
                HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getWorld().getSpawnLocation();
        player.teleport(spawnLocation);

        DamageUtil.denyAllDamage(player.getName());
        KitManager.getPlayer(player.getName()).removeKit();
        HelixTitle.clearTitle(player);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.ADVENTURE);
        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
        player.setFireTicks(0);
        player.setFoodLevel(20);
        player.setAllowFlight(player.hasPermission("kombo.cmd.fly"));
        player.setFlying(player.hasPermission("kombo.cmd.fly"));
        player.getInventory().setHeldItemSlot(0);

        player.getInventory().setItem(0, new ItemBuilder("§eKits disponíveis", Material.CHEST)
                .nbt("spawn-item", "kits")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .toStack()
        );

        player.getInventory().setItem(1, new ItemBuilder("§eWarps disponíveis", Material.COMPASS)
                .nbt("spawn-item", "warps")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .toStack()
        );

        player.getInventory().setItem(8, new ItemBuilder("§eLoja de Kits", Material.DIAMOND)
                .nbt("spawn-item", "shop")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .toStack()
        );
        player.getInventory().setItem(7, new ItemBuilder("§eStatus", Material.SKULL_ITEM)
                .nbt("spawn-item", "status")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .toStack()
        );
    }
}