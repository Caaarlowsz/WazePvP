package net.helix.pvp.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;

public class ShopGUI implements Listener {

	private final static String inventoryName = "Categorias - Loja";
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.VINE, 1);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(player, InventoryType.HOPPER, inventoryName);
		

		inventory.setItem(1, new ItemBuilder("§cLoja de kit 1", Material.CHEST).nbt("shop")
				.toStack()
		);
		inventory.setItem(3, new ItemBuilder("§cLoja de kit 2", Material.CHEST).nbt("shop2")
				.toStack()
		);
		inventory.setItem(2, new ItemBuilder("§cResetar seu kdr", Material.NETHER_STAR).nbt("shop3")
				.toStack()
		);
		
		player.openInventory(inventory);
	}
	@EventHandler
	public void onInvClick2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(ShopGUI.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "shop")) {
			return;
		}
		ShopInventory.open(player);
	}
	@EventHandler
	public void onInvClCck2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(ShopGUI.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "shop3")) {
			return;
		}
		ShopKDRGUI.openGUI(player);
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(ShopGUI.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "shop2")) {
			return;
		}
		ShopInventory2.open(player);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
}
