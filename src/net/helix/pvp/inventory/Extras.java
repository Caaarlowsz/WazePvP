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
import net.helix.pvp.Caixas;
import net.helix.pvp.evento.SoupTypeGUI;

public class Extras implements Listener {

	private final static String inventoryName = "Extras";
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.VINE, 1);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(player, InventoryType.HOPPER, inventoryName);
		

		inventory.setItem(1, new ItemBuilder("§aCaixas", Material.CHEST).nbt("cx")
				.toStack()
		);
		inventory.setItem(2, new ItemBuilder("§aPerfil", Material.SKULL_ITEM).nbt("pf")
				.toStack()
		);
		inventory.setItem(3, new ItemBuilder("§aOpções", Material.STORAGE_MINECART).nbt("op")
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
		if (!ItemBuilder.has(event.getCurrentItem(), "cx")) {
			return;
		}
		Bukkit.dispatchCommand(player, "crates");
	}
	@EventHandler
	public void onInvClCck2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "pf")) {
			return;
		}
		StatusGUI.openGUI(player, player);
	}
	@EventHandler
	public void onInvCblCck2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "op")) {
			return;
		}
		SoupTypeGUI.openGUI(player);
	}

	
	public static String getInventoryName() {
		return inventoryName;
	}
}

