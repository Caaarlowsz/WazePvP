package net.helix.pvp;



	import java.util.Random;
	import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
	import org.bukkit.Sound;
	import org.bukkit.command.Command;
	import org.bukkit.command.CommandExecutor;
	import org.bukkit.command.CommandSender;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.inventory.InventoryClickEvent;
	import org.bukkit.inventory.Inventory;
	import org.bukkit.inventory.ItemStack;
	import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;

	public class Caixas
	  implements Listener, CommandExecutor
	{
	  public static ItemStack vidro;
	  public static ItemStack fly;
	  public static ItemMeta flymeta;
	  public static ItemMeta vidrometa;
	  public static ItemStack vidro2;
	  public static ItemMeta vidro2meta;
	  public static ItemStack tag;
	  public static ItemMeta tagmeta;
	  public static ItemStack resetkdr;
	  public static ItemMeta resetkdrmeta;
	  public static ItemStack todososkits;
	  public static ItemMeta todososkitsmeta;
	  public static ItemStack randomkit;
	  public static ItemMeta randomkitmeta;
	  public static ItemStack randomkit3;
	  public static ItemMeta randomkit3meta;
	  public static ItemStack randomkit2;
	  public static ItemMeta randomkit2meta;
	  
	  public Caixas() {}

	  @EventHandler
	  public void warps(InventoryClickEvent e)
	  {
	    if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
	    {
	      Inventory inv = e.getInventory();
	      Player p = (Player)e.getWhoClicked();
	      if (inv.getTitle().equals("§8Crates"))
	      {
	        p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0F, 5.0F);
	        e.setCancelled(true);
	        p.closeInventory();
	      }
	      HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	      if (e.getCurrentItem().isSimilar(todososkits)) {
	        if (playerData.getPvp().getCoins() < 50000)
	        {
	          p.sendMessage( "You dont have enought money");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 50000);
	          p.sendMessage( "§7Voce Comprou §aTodos Os Kits§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kit.*");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(fly)) {
	        if (playerData.getPvp().getCoins() < 7000)
	        {
	          p.sendMessage( "You dont have enought money");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 7000);
	          p.sendMessage( "Comprou §aFly§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kitpvp.fly");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(tag)) {
	        if (playerData.getPvp().getCoins() < 4000)
	        {
	          p.sendMessage( "Você não tem dinheiro");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 4000);
	          p.sendMessage( "§7Voce Comprou §aTag MC§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add tag.mc");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit2)) {
	        if (playerData.getPvp().getCoins() < 1200)
	        {
	          p.sendMessage("§a§lCAIXA §fSem dinheiro o bastante");
	        }
	        else
	        {
	        	playerData.getPvp().removeCoins( 1200);
	          
	          randomprata(p);
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit3)) {
	        if (playerData.getPvp().getCoins() < 2500)
	        {
	          p.sendMessage("§a§lCAIXA §fSem dinheiro o bastante");
	        }
	        else
	        {
	        	 playerData.getPvp().removeCoins( 2500);
	          
	          randomouro(p);
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit)) {
	        if (playerData.getPvp().getCoins() < 600)
	        {
	          p.sendMessage("§a§lCAIXA §fSem dinheiro o bastante");
	        }
	        else
	        {
	          playerData.getPvp().removeCoins(600);
	          randombronze(p);
	        }
	      }
	    }
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (!(sender instanceof Player)) {
	      return true;
	    }
	    Player p = (Player)sender;
	    if (cmd.getName().equalsIgnoreCase("crates"))
	    {
	      Inventory lojadeoutros = Bukkit.createInventory(p, 27, "§8Crates");
	      
	      vidro = new ItemStack(Material.THIN_GLASS, 1, (short)14);
	      vidrometa = vidro.getItemMeta();
	      vidrometa.setDisplayName("§cCrates");
	      vidro.setItemMeta(vidrometa);
	      
	      vidro2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)13);
	      vidro2meta = vidro2.getItemMeta();
	      vidro2meta.setDisplayName("§6Crates");
	      vidro2.setItemMeta(vidro2meta);
	      
	      randomkit = new ItemStack(Material.getMaterial(336));
	      randomkitmeta = randomkit.getItemMeta();
	      randomkitmeta.setDisplayName("§6Caixa de Bronze §2Coins >> §a600");
	      randomkit.setItemMeta(randomkitmeta);
	      
	      randomkit2 = new ItemStack(Material.IRON_INGOT);
	      randomkit2meta = randomkit2.getItemMeta();
	      randomkit2meta.setDisplayName("§7Caixa de Prata §2Coins >> §a1200");
	      randomkit2.setItemMeta(randomkit2meta);
	      
	      randomkit3 = new ItemStack(Material.GOLD_INGOT);
	      randomkit3meta = randomkit3.getItemMeta();
	      randomkit3meta.setDisplayName("§eCaixa de Ouro §2Coins >> §a2500");
	      randomkit3.setItemMeta(randomkit3meta);
	      for (int i = 0; i != 27; i++)
	      {
	        lojadeoutros.setItem(i, vidro);
	        lojadeoutros.setItem(11, randomkit);
	        lojadeoutros.setItem(13, randomkit2);
	        lojadeoutros.setItem(15, randomkit3);
	        lojadeoutros.setItem(10, vidro2);
	        lojadeoutros.setItem(12, vidro2);
	        lojadeoutros.setItem(9, vidro2);
	        lojadeoutros.setItem(14, vidro2);
	        lojadeoutros.setItem(16, vidro2);
	        lojadeoutros.setItem(17, vidro2);
	      }
	      p.openInventory(lojadeoutros);
	      return true;
	    }
	    return true;
	  }
	  
	  public void randombronze(Player p)
	  {
	    Random r = new Random();
	    int o = r.nextInt(14);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " ABRIU UMA CAIXA DE BRONZE!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Ninja");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.ninja");
	    }
	    else if (o == 1)
	    {
	      p.sendMessage("§a§lCRATES §fVocê recebeu §a500§f Coins");
	      p3.addCoins(500);
	    }
	    else if (o == 2)
	    {
	      p.sendMessage("§a§lCAIXA  §fVocê recebeu §a600§f Coins");
	      p3.addCoins(600);
	    }
	    else if (o == 3)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Barbarian");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.barbarian");
	    }
	    else if (o == 4)
	    {
	      p.sendMessage("§a§lCRATE  §fVocê recebeu o kit Endermage");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.endermage");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o Kit Thresh e §a500§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.thresh");
	      p3.addCoins( 500);
	    }
	    else if (o == 6)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit sonic");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.sonic");
	      
	    }
	    else if (o == 7)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Anchor");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.anchor");
	    }
	    else if (o == 8)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu §a1000§f Coins");
	      p3.addCoins(1000);
	    }
	    else if (o == 9)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu §a900§f Coins");
	      p3.addCoins(900);
	    }
	    else if (o == 10)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu 300 XP");
	      p3.addXP(300);
	    }
	    else if (o == 11)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu kit secundário stomper");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.stomper");
	    }
	    else if (o == 12)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu kit snail");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.snail");
	    }
	      else if (o == 13)
		    {
		      p.sendMessage("§a§lCRATE §cVocê não ganhou nada");
		      p.sendMessage("§c§lMais sorte na próxima!");
		      
	      if ((o == 7) || 
	        (o == 8) || 
	        (o == 9) || 
	        (o == 10) || 
	        (o == 12)) {}
	    }
	  }
	  
	  public void randomprata(Player p)
	  {
	    Random r = new Random();
	    Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " ABRIU UMA CAIXA DE BRONZE!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    int o = r.nextInt(16);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Grappler");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.grappler");
	    }
	    else if (o == 1)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu §a7000§f Coins");
	      p3.addCoins(7000);
	    }
	    else if (o == 2)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu §a6000§f Coins");
	      p3.addCoins(6000);
	    }
	    else if (o == 3)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit gladiator");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.gladiator");
	    }
	    else if (o == 4)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu permissão para falar em cores e duplo xp");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.doublexp");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu kit deshfire e §a500§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.deshfire");
	      p3.addCoins(500);
	    }
	    else if (o == 6)
	    {
	      p.sendMessage("§a§lCRATE §f§lVocê recebeu kit Viper e §a1000§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.viper");
	      p3.addCoins(500);
	    }
	    else if (o == 7)
	    {
	        p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit Gladiator");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.gladiator");
		    
	    }
	    else if (o == 8)
	    {
	        p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit MilkMan");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.milkman");
	    }
	    else if (o == 9)
	    {
	        p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit secundário Gladiator");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.gladiator");
	    }
	    else if (o == 10)
	    {
	        p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit Meteor");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.meteor");
	    }
	    else if (o == 11)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit Sonic e Deshfire");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.sonic");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.deshfire");	    }
	    else if (o == 12)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit Anchor");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.anchor");
	      
	    }
	    else if (o == 13)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu o kit Barbarian");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.barbarian");
	    }
	    else if (o == 14)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu 550XP");
p3.addXP(550);
	    }
	    else if (o == 15)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu 750XP");
	    	 p3.addXP(750);
	    }
	    else if (o == 16)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lVocê recebeu 300XP");
	    	 p3.addXP(300);
	    }
	    else if (o == 17)
	    {
	      p.sendMessage("§a§lCRATE §f§lVocê recebeu o Kit Monk");
	    }
	    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.monk");
	    
	  
	    
	    if ((o != 7) && 
	      (o != 8) && 
	      (o != 9) && 
	      (o != 10) && 
	      (o != 11) && 
	      (o != 12) && 
	      (o != 13)) {}
	  }
	  
	  public void randomouro(Player p)
	  {
	    Random r = new Random();
	    int o = r.nextInt(17);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " ABRIU UMA CAIXA DE OURO!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit WaterBender");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.waterbender");
	    }
	    else if (o == 1)
	    {
		      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Anchor");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.anchor");
	    }
	    else if (o == 2)
	    {
		      p.sendMessage("§a§lCRATE §fVocê recebeu o kit Leech secundário");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.leech");
	    }
	    else if (o == 3)
	    {
		      p.sendMessage("§a§lCRATE §fVocê recebeu permissão para falar em cores e doublexp/doublecoins");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.doublexp");
	    }
	    else if (o == 4)
	    {
		      p.sendMessage("§a§lCRATE §fVocê recebeu todos os kits primários por 1 semana");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp kombo.kit.* true 7d");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu 900XP");
	      p3.addXP(900);
	    }
	    else if (o == 6)
	    {
	    	p.sendMessage("§a§lCRATE §fVocê recebeu todos os kits secundários por 1 semana");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp kombo.kit2.* true 7d");
	    }
	    else if (o == 7)
	    {
	      p.sendMessage("§a§lCRATE §fVocê recebeu o kit secundário NEO");

	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.neo");
	    }
	    else if (o == 8)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit secundário Avatar");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.avatar");
	    }
	    else if (o == 9)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit Boxer");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.boxer");
	    }
	    else if (o == 10)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit Ninja");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.ninja");
	    }
	    else if (o == 11)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit Ninja secundário");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.ninja");
	    }
	    else if (o == 12)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit secundário Timelord");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.timelord");
	    }
	    else if (o == 13)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit secundário Grappler");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.grappler");
	    }
	    else if (o == 14)
	    {
	    	 p.sendMessage("§a§lCRATE §fVocê recebeu o kit Fisherman");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.fisherman");
	    }
	    else if (o == 15)
	    {
	      p.sendMessage("§a§lVocê recebeu 6000 de XP");
	      p3.addXP(6000);
	      
	    }
	    else if (o == 16)
	    {
	    	p.sendMessage("§a§lVocê recebeu 4000 de XP");
		      p3.addXP(4000);
	      
	    }
	    else if (o == 17)
	    {
	      p.sendMessage("§a§lVocê recebeu o grupo Winner permanentemente!");
	      
	      
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add winner");
	      
	      
	    }
	    else if (o == 18)
	    {
		      p.sendMessage("§a§lVocê recebeu a tag 2024 (Apenas a tag)!");
		      
		      
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set helix.tag.2024");
	      if ((o == 7) || 
	        (o == 8) || 
	        (o == 9) || 
	        (o == 10) || 
	        (o == 11) || 
	        (o == 12) || 
	        (o == 13) ||
	        (o == 14) ||
	        (o == 15) ||
	        (o == 16) ||
	        (o == 17) ||
	        (o == 18)) {}
	    }
	  }
	}

