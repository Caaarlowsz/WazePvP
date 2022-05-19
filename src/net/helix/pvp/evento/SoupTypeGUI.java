package net.helix.pvp.evento;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class SoupTypeGUI implements Listener {
	public static HashMap<String, Boolean> savecocoa = new HashMap();
	public static HashMap<String, Boolean> compass = new HashMap();
    public static final ItemStack ICON = ItemUtils.getCustomItemStack(Material.NETHER_STAR, "�6Op��es", (String) null);

    @EventHandler
    private void onInteractSoupType(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasItem() && event.getItem().isSimilar(SoupTypeGUI.ICON)) {
            SoupTypeGUI.openGUI(player);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null && event.getInventory().getName().contains("�6Op��es")) {
            if (event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                player.closeInventory();
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cogumelos")) {
                    if (!savecocoa.containsKey(player.getName())) {
                        player.sendMessage("�cO seu estilo de sopa cogumelo j� � o selecionado.");
                    } else {
                        savecocoa.remove(player.getName());
                        player.sendMessage("�aVoc� alterou seu estilo de sopa para: �eCOGUMELOS�a.");
                    }
                }
                    else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cocoa Beans")) {
                    if (savecocoa.containsKey(player.getName())) {
                        player.sendMessage("�cO seu estilo de sopa cocoa j� � o selecionado.");
                    } else {
                    	savecocoa.put(player.getName(), true);
                         player.sendMessage("�aVoc� alterou seu estilo de sopa para: �eCOCOA BEAN�a.");
                    }
                    }
                         else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("B�ssola")) { 
                	  if (!compass.containsKey(player.getName())) {
                		  player.sendMessage("�cA b�ssola foi desativada.");
                		  compass.put(player.getName(), true);
                	  } else {
                		  player.sendMessage("�aA b�ssola foi ativada.");
                		  compass.remove(player.getName());
                }
            }
            }
                }
                
            
        }
        
       
    

    public static void openGUI(Player player) {

        final Inventory inv = Bukkit.createInventory(null, 9, "�6Op��es");
        if (!savecocoa.containsKey(player.getName())) {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "�aCogumelos", Arrays.asList("�7Voc� receber� cogumelos.", " ", "�a�oSelecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "�cCocoa Beans", Arrays.asList("�7Voc� receber� cocoa beans.", " ", "�c�oN�o selecionado")));
        } else {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "�cCogumelos", Arrays.asList("�7Voc� receber� cogumelos.", " ", "�c�oN�o selecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "�aCocoa Beans", Arrays.asList("�7Voc� receber� cocoa beans.", " ", "�a�oSelecionado")));
        }
        if (!compass.containsKey(player.getName())) {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "�cB�ssola", Arrays.asList("�7B�ssola.", " ", "�aAtivado")));
        } else {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "�cB�ssola", Arrays.asList("�7Bússola.", " ", "�cDesativado")));
        }
        player.openInventory(inv);
    }

}