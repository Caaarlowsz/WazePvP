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

public class ALL implements Listener {

	private final static String inventoryName = "Menu Geral";
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.VINE, 1);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(player, InventoryType.HOPPER, inventoryName);
		

		inventory.setItem(0, new ItemBuilder("§aKits primários", Material.CHEST).nbt("kit1")
				.toStack()
		);
		inventory.setItem(1, new ItemBuilder("§aKits secundários", Material.CHEST).nbt("kit2")
				.toStack()
		);
		inventory.setItem(2, new ItemBuilder("§aLoja", Material.EMERALD).nbt("shop")
				.toStack()
		);
		inventory.setItem(3, new ItemBuilder("§aWarps", Material.COMPASS).nbt("warps")
				.toStack()
		);
		inventory.setItem(4, new ItemBuilder("§aExtras", Material.PAPER).nbt("extras")
				.toStack()
		);
		
		player.openInventory(inventory);
	}
	@EventHandler
	public void onInvClick2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "shop")) {
			return;
		}
		ShopGUI.open(player);
	}
	@EventHandler
	public void onInvClCck2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit1")) {
			return;
		}
		KitsInventory.open(player);
	}
	@EventHandler
	public void onInvCblCck2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit2")) {
			return;
		}
		KitsInventory2.open(player);
	}
	@EventHandler
	public void onInvClickb(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "warps")) {
			return;
		}
		WarpsInventory.open(player);
	}
	@EventHandler
	public void onInvClickvb(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "extras")) {
			return;
		}
		Extras.open(player);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
}

