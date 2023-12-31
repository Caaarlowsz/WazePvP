package net.helix.pvp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.command.ActionBar;
import net.helix.pvp.command.AntiSpam;
import net.helix.pvp.command.Aplicar;
import net.helix.pvp.command.Arena;
import net.helix.pvp.command.AutoSoup;
import net.helix.pvp.command.AvisoT;
import net.helix.pvp.command.BukkitCommandFramework;
import net.helix.pvp.command.BukkitCommandLoader;
import net.helix.pvp.command.ChatCommand;
import net.helix.pvp.command.Crash;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.command.DesligarPlugin;
import net.helix.pvp.command.DesligarServidor;
import net.helix.pvp.command.Euforia;
import net.helix.pvp.command.EventManager;
import net.helix.pvp.command.Fake;
import net.helix.pvp.command.FakeList;
import net.helix.pvp.command.Fly;
import net.helix.pvp.command.GiveCoins;
import net.helix.pvp.command.GiveDeaths;
import net.helix.pvp.command.GiveKills;
import net.helix.pvp.command.GiveXP;
import net.helix.pvp.command.GladInfo;
import net.helix.pvp.command.Grant;
import net.helix.pvp.command.Head;
import net.helix.pvp.command.Info;
import net.helix.pvp.command.KITPVP;
import net.helix.pvp.command.LastLogin;
import net.helix.pvp.command.LavaIniciar;
import net.helix.pvp.command.LimparCache;
import net.helix.pvp.command.MDRIniciar;
import net.helix.pvp.command.MacroTest;
import net.helix.pvp.command.Medal;
import net.helix.pvp.command.Money;
import net.helix.pvp.command.NoBreakEvent;
import net.helix.pvp.command.OneVsOneIniciar;
import net.helix.pvp.command.PvP;
import net.helix.pvp.command.RDMAutomatic;
import net.helix.pvp.command.RankCMD;
import net.helix.pvp.command.Regras;
import net.helix.pvp.command.RemoveKit;
import net.helix.pvp.command.RemoverGlads;
import net.helix.pvp.command.ResetEventoClass;
import net.helix.pvp.command.ResetKDR;
import net.helix.pvp.command.SC;
import net.helix.pvp.command.ScoreboardCMD;
import net.helix.pvp.command.SendCredits;
import net.helix.pvp.command.ServerTimerEvent2;
import net.helix.pvp.command.SetArena;
import net.helix.pvp.command.SetFeast;
import net.helix.pvp.command.SetHologramCMD;
import net.helix.pvp.command.Site;
import net.helix.pvp.command.SkitCMD;
import net.helix.pvp.command.SocialSpy;
import net.helix.pvp.command.SortearPlayer;
import net.helix.pvp.command.Sorteio;
import net.helix.pvp.command.SpawnCMD;
import net.helix.pvp.command.StaffVsPlayers;
import net.helix.pvp.command.Sudo;
import net.helix.pvp.command.TPALL;
import net.helix.pvp.command.Vanish;
import net.helix.pvp.command.VerRank;
import net.helix.pvp.command.Warp;
import net.helix.pvp.command.Youtuber;
import net.helix.pvp.command.b;
import net.helix.pvp.cooldown2.UpdateEvent2;
import net.helix.pvp.cooldown2.UpdateScheduler;
import net.helix.pvp.cooldown2.UpdateScheduler2;
import net.helix.pvp.evento.EventoComando;
import net.helix.pvp.evento.EventoListeners;
import net.helix.pvp.evento.EventoTabComplete;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.inventory.ALL;
import net.helix.pvp.inventory.Extras;
import net.helix.pvp.inventory.ShopGUI;
import net.helix.pvp.inventory.ShopKDRGUI;
import net.helix.pvp.inventory.StatusGUI;
import net.helix.pvp.inventory.listener.BuyKitListener;
import net.helix.pvp.inventory.listener.Lapis;
import net.helix.pvp.inventory.listener.SelectKitListener;
import net.helix.pvp.inventory.listener.SelectWarpListener;
import net.helix.pvp.kit.provider.Barbarian;
import net.helix.pvp.kit.provider.Boxer;
import net.helix.pvp.kit.provider.Flash;
import net.helix.pvp.kit.provider.Grappler;
import net.helix.pvp.kit.provider.Jumper;
import net.helix.pvp.kit.provider.Sonic;
import net.helix.pvp.kit.provider.Tank;
import net.helix.pvp.kit.provider.TimeLord;
import net.helix.pvp.kit.provider.Tornado;
import net.helix.pvp.listener.Cocoa;
import net.helix.pvp.listener.EntityCalculateDamageListener;
import net.helix.pvp.listener.Jump;
import net.helix.pvp.listener.LAVA;
import net.helix.pvp.listener.OnevsOneKS;
import net.helix.pvp.listener.OpenSpawnItemsListener;
import net.helix.pvp.listener.PlayerCombatLogListener;
import net.helix.pvp.listener.PlayerCompassListener;
import net.helix.pvp.listener.PlayerDeathListener;
import net.helix.pvp.listener.PlayerDieArenaListener;
import net.helix.pvp.listener.PlayerJoinListener;
import net.helix.pvp.listener.PlayerKillstreakListener;
import net.helix.pvp.listener.PlayerQuitListener;
import net.helix.pvp.listener.PotePlaca;
import net.helix.pvp.listener.RecraftGeral;
import net.helix.pvp.listener.ServerEssentialsListener;
import net.helix.pvp.listener.SignListener;
import net.helix.pvp.listener.SoupHandlerListener;
import net.helix.pvp.scoreboard.ScoreboardBuilder;
import net.helix.pvp.warp.provider.SetX1;
import net.helixpvp.kit2.AntiProxyListener;
import net.helixpvp.kit2.GladiatorListener;
import net.md_5.bungee.api.chat.TextComponent;
import us.ajg0702.leaderboards.LeaderboardPlugin;

public class HelixPvP extends JavaPlugin implements Listener, PluginMessageListener {
	
	public static HelixPvP getInstance() {
		return getPlugin(HelixPvP.class);
	}
	public static HelixPvP getInstance2() {
		return instance;
	}
	public ArrayList<EnchantingInventory> inventories;
	private ScoreboardBuilder scoreboardBuilder;
	private Hologram topPlayersHd;

	  private EventManager eventManager;
	public static boolean euforia;
	private static HelixPvP instance;
	 public static File file_x1 = new File("plugins/SRKitPvP", "1v1.yml");
	 public static FileConfiguration cfg_x1 = YamlConfiguration.loadConfiguration(file_x1);
	 protected String getIpLocalHost() {
	        try {
	            return (new BufferedReader(new InputStreamReader((new URL("http://checkip.amazonaws.com")).openStream())))
	                    .readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 private void startUpdating() {

		    Bukkit.getServer().getScheduler().runTaskTimer(getInstance(), new UpdateScheduler(), 20, 20);
		  
		}
	 private void setupRecipes() {
	        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
	        ItemStack cocoa = new ItemStack(Material.INK_SACK, 1, (short) 3);
	        ShapelessRecipe soupRecipe = new ShapelessRecipe(soup);
	        soupRecipe.addIngredient(Material.BOWL);
	        soupRecipe.addIngredient(cocoa.getData());
	        getServer().addRecipe(soupRecipe);
	    }
	 private static String getConfig(String s) {
		    return HelixPvP.getInstance().getConfig().getString(s);
		  }
	public void onEnable() {
		ChatCommand.chat = true; // aumenta o texto pra nao lagar 
		
		instance = this;
		 startUpdating();
		    	 Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		    		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.scoreboardBuilder = new ScoreboardBuilder(this);
		new Feast(this);
		
		loadCommands();
		ScoreboardBuilder.init();
		loadListeners();
		this.inventories = new ArrayList<>();

		setupRecipes();
		Bukkit.getConsoleSender().sendMessage("RECEITAS CARREGADAS (CACAU)");



	
new BukkitRunnable() {
			
			@Override
			public void run() {
				if (euforia) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (net.helix.pvp.warp.HelixWarp.SPAWN.hasPlayer(player.getName()) && !EventoUtils.game.contains(player.getName())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 0));
						}
						else {
							 player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));	
					}
				
			
			}
			} 
			}}.runTaskTimer(this, 0, 3 * 20L);
		
new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (EventoUtils.game.contains(p.getName()) && RDMAutomatic.iniciou) {
						if (!getInstance().getEventManager().getRdmAutomatic().players.contains(p)) {
				getInstance().getEventManager().getRdmAutomatic().putInEvent2(p);
						}
				else if (!EventoUtils.game.contains(p.getName()) && RDMAutomatic.iniciou) {
					getInstance().getEventManager().getRdmAutomatic().removeFromEvent(p);
				}
			}
					}
			}}
		.runTaskTimer(this, 0, 1 * 20L);
		
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getWorlds().forEach(world -> {
						world.getEntities().stream().filter(entity -> entity instanceof Item)
						.forEach(en -> en.remove());
					});
					}}.runTaskTimer(this, 0, 30 * 20L);
		});
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getWorlds().forEach(world -> {
						world.getEntities().stream().filter(entity -> entity instanceof Item && entity.getLocation().getX() > 654100 && entity.getLocation().getX() < 654150)
						.forEach(en -> en.remove());
					
						
					    
					});
					}}.runTaskTimer(this, 0, 1 * 5L);
		});
	    Bukkit.getWorld(getConfig("MAPAMUNDO")).setTime(18000);
		HelixBukkit.getExecutorService().submit(() -> {
			
			new BukkitRunnable() {

				
				@Override
				public void run() {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (Bukkit.getOnlinePlayers().size() < 3) {
							return;
						}

						DarKit.sendTitle(player, "§c§lFÚRIA", "§fTodos ficaram fortes");
						
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 0));
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1F, 10F);
						euforia = true;
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit.* true 2m");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit2.* true 2m");
					}
					Bukkit.broadcastMessage("§cO evento §4§lFÚRIA §ccomeçou");
					Bukkit.broadcastMessage("§cPor dois minutos todos teram força 2");
					Bukkit.broadcastMessage("§cTodos os kits liberados durante o evento");
//					    Bukkit.getWorld("spawn").setTime(18000);
//						Bukkit.broadcastMessage("§cO evento §4§lEUFORIA §cacabou de começar");
//						Bukkit.broadcastMessage("§cPor dois minutos estará de noite e players teram força 1");
//						Bukkit.broadcastMessage("§cTodos os kits primários e secundários liberados durante o evento");
						  Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
								public void run() {
									if (!euforia) {
										  return;
									  }
									
									Bukkit.broadcastMessage("§aO evento fúria foi finalizado!");
									euforia = false;
									
									 Bukkit.getWorld(getConfig("MAPAMUNDO")).setTime(100);
									 for (Player p1 : Bukkit.getOnlinePlayers()) {
										 
										 DarKit.sendTitle(p1, "§c§lFÚRIA", "§aEvento finalizado!");
									      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
									        p1.getActivePotionEffects().forEach(potion -> p1.removePotionEffect(potion.getType()));
									      }
								}
							}, 2400L);
					}
						 
					
				}.runTaskTimer(this, 0, 40 * 60 * 20L);
		});
		
		Bukkit.getWorld("spawn").setDifficulty(Difficulty.HARD);
		if (!file_x1.exists()) {
			/* 132 */       saveResource("1v1.yml", false);
			/*     */     }
		 try
		 /*     */     {
		 /* 139 */       cfg_x1.load(file_x1);
		 /*     */     }
		 /*     */     catch (IOException|InvalidConfigurationException e1)
		 /*     */     {
		 /* 144 */       System.out.println(e1.getMessage());
		 /*     */     }
	
		new BukkitRunnable() {

			private long tick;
			private long tick2;

			@Override
			public void run() {
				getServer().getPluginManager().callEvent(new net.helix.pvp.command.ServerTimerEvent(++tick));
				getServer().getPluginManager().callEvent(new ServerTimerEvent2(++tick2));
			}
		}.runTaskTimer(this, 1, 1);
		new net.helix.pvp.papi.PlaceHolderAPIHook(this).register();
		loadTopPlayersHologram();
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fCriado por §a[v" + getDescription().getAuthors() + "]");
	}
	public String color(String s) {
		s = ChatColor.translateAlternateColorCodes('&', s);
		return s;
	}
	public static void handleTopPlayers(Location location) {
		Plugin lb = Bukkit.getPluginManager().getPlugin("ajLeaderboards");
		if (lb == null || !lb.isEnabled() || !(lb instanceof LeaderboardPlugin)) {
			Bukkit.getLogger().severe("AjLeaderBoards not found! TopKills will not work.");
			return;
		}
		Plugin dc = Bukkit.getPluginManager().getPlugin("DecentHolograms");
		if (dc == null) {
			Bukkit.getLogger().severe("DecentHolograms not found! TopKills will not work.");
			return;
		}
		LeaderboardPlugin ajlb = (LeaderboardPlugin) lb;
		if (!ajlb.getCache().createBoard("helixpvp2_player_kills")) {
			Bukkit.getLogger().severe("TopKills Creation Failed. Aborting");
			return;
		}
		
		
		
		

			String header = "§e§lTop 15 players §a(KILLS)";	
				List<String> lines = Arrays.asList(header,
					"§61"  + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_name%" +
					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_value%", "§62" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_name%" +
							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_value%", "§63" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_name%" +
									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_value%", "§64" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_name%" +
											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_value%", "§65" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_name%" +
													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_value%", "§66" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_name%" +
															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_value%", "§67" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_name%" +
																	" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_value%", "§68" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_name%" +
																			" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_value%", "§69" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_name%" +
																					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_value%", "§610" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_value%", "§611" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_name%" +
																									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_value%", "§612" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_12_alltime_name%" +
																											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_12_alltime_value%", "§613" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_13_alltime_name%" +
																													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_13_alltime_value%", "§614" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_14_alltime_name%" +
																															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_14_alltime_value%", "§615" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_15_alltime_name%" +
																																	" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_15_alltime_value%");
			
			
				

			Hologram hologram = DHAPI.getHologram("topkills");
			if (hologram == null) {
			 Hologram holo = DHAPI.createHologram("topkills", location, true, lines);
		 holo.updateAll();
				}
			
			 DHAPI.updateHologram("topkills");
			}
			
			
			
				
	
	
	private void loadTopPlayersHologram() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Optional<HelixWarp> warpOptional;
				if (!(warpOptional = HelixBukkit.getInstance().getWarpManager().findWarp("top-players")).isPresent()) {
					return;
				}
				
				Location location = warpOptional.get().getLocation();
				handleTopPlayers(location);
			}
		}.runTaskTimer(HelixPvP.getInstance(), 10 * 20L, 2 * (60 * 20L));
	}
	
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer("§cServidor está reiniciando.");
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (GladiatorListener.combateGlad.containsKey(p)) {
			for (final Location loc : GladiatorListener.blocks.get(p.getName())) {
				
			Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad de " + p.getName());
	            loc.getBlock().setType(Material.AIR);
	        }
			if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				for (final Location loc : net.helixpvp.kit2.GladiatorListener.blocks.get(p.getName())) {
					Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad Secundário de " + p.getName());				
		            loc.getBlock().setType(Material.AIR);
		        }
			
		
		for (EnchantingInventory ei : this.inventories)
		      ei.setItem(1, null); 
		    this.inventories = null;
			Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin desabilitado! §a[v" + getDescription().getVersion() + "]");
		  }
		}
	}
		
	}
	
	 public EventManager getEventManager() {
		    return this.eventManager;
		  }
	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("skit").setExecutor(new SkitCMD());
		getCommand("crash").setExecutor(new Crash());
		RDMAutomatic.iniciou = false;
		getCommand("sethologram").setExecutor(new SetHologramCMD());
		getCommand("scoreboard").setExecutor(new ScoreboardCMD(this));
		getCommand("rank").setExecutor(new RankCMD());
		getCommand("givecoins").setExecutor(new GiveCoins());
		getCommand("givekills").setExecutor(new GiveKills());
		getCommand("givedeaths").setExecutor(new GiveDeaths());
		getCommand("shutdownserver").setExecutor(new DesligarServidor());
		getCommand("resetkdr").setExecutor(new ResetKDR());
		getCommand("lastlogin").setExecutor(new LastLogin());
				getCommand("finalizarbatalhas").setExecutor(new RemoverGlads());
		getCommand("ll").setExecutor(new LastLogin());
		getCommand("lastlogin").setExecutor(new LastLogin());
		getCommand("socialspy").setExecutor(new SocialSpy(this));
		getCommand("head").setExecutor(new Head());
		getCommand("removekit").setExecutor(new RemoveKit());
		getCommand("givekit").setExecutor(new DarKit());
		getCommand("fly").setExecutor(new Fly());
		getCommand("set1v1").setExecutor(new SetX1());
		getCommand("svpiniciar").setExecutor(new StaffVsPlayers());
		getCommand("mdriniciar").setExecutor(new MDRIniciar());
		getCommand("group").setExecutor(new Group());
		new BukkitCommandLoader(new BukkitCommandFramework(getInstance())).loadCommandsFromPackage("net.helix.pvp.command");
		getCommand("macrotest").setExecutor(new MacroTest());
		getCommand("yt").setExecutor(new Youtuber());
		getCommand("youtuber").setExecutor(new Youtuber());
		getCommand("pvp").setExecutor(new PvP());
		getCommand("euforia").setExecutor(new Euforia());
		getCommand("limparcache").setExecutor(new LimparCache());
		getCommand("autosoup").setExecutor(new AutoSoup(this));
		getCommand("warpinfo").setExecutor(new GladInfo());
		getCommand("arenainiciar").setExecutor(new Arena());
		getCommand("lavainiciar").setExecutor(new LavaIniciar());
		getCommand("sealend123").setExecutor(new DesligarPlugin(this));
		getCommand("tpall").setExecutor(new TPALL());
		getCommand("verrank").setExecutor(new VerRank());
		getCommand("stats").setExecutor(new VerRank());
		getCommand("status").setExecutor(new VerRank());
		getCommand("medalha").setExecutor(new Medal());
		getCommand("crates").setExecutor(new Caixas());
		getCommand("fakelist").setExecutor(new FakeList());
		getCommand("fake").setExecutor(new Fake());
		getCommand("medalhas").setExecutor(new Medal());
		getCommand("pinfo").setExecutor(new Info());
		getCommand("requisitos").setExecutor(new Youtuber());
		getCommand("req").setExecutor(new Youtuber());
		getCommand("sortearplayer").setExecutor(new SortearPlayer());
		getCommand("sorteio").setExecutor(new Sorteio());
		getCommand("givexp").setExecutor(new GiveXP());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("v").setExecutor(new Vanish());
		getCommand("kitpvp").setExecutor(new KITPVP(this));
		getCommand("actionbar").setExecutor(new ActionBar());
		getCommand("regras").setExecutor(new Regras());
		getCommand("setfeast").setExecutor(new SetFeast());
		getCommand("raikiri21").setExecutor(new Commands());
		getCommand("setarena").setExecutor(new SetArena());
		getCommand("sudo").setExecutor(new Sudo());
		getCommand("grupo").setExecutor(new Group());
		getCommand("consolesudo").setExecutor(new Sudo());
		if (this.getConfig().getBoolean("StaffChatAtivado")) {
		getCommand("sc").setExecutor(new SC());
		}
		getCommand("site").setExecutor(new Site());
		getCommand("loja").setExecutor(new Site());
		getCommand("money").setExecutor(new Money());
		  (getInstance()).eventManager = new EventManager();
		getCommand("dinheiro").setExecutor(new Money());
		getCommand("warp").setExecutor(new Warp());
		getCommand("grant").setExecutor(new Grant());
		getCommand("sendcredits").setExecutor(new SendCredits());
		getCommand("aplicar").setExecutor(new Aplicar());
		getCommand("sendtitle").setExecutor(new AvisoT());
		getCommand("event").setExecutor(new EventoComando());
		getCommand("evento").setExecutor(new EventoComando());
		getCommand("reseteventoclass").setExecutor(new ResetEventoClass());
		getCommand("1v1iniciar").setExecutor(new OneVsOneIniciar());
		getCommand("event").setTabCompleter(new EventoTabComplete());
		getCommand("evento").setTabCompleter(new EventoTabComplete());
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new StatusGUI(), this);
		pm.registerEvents(new Boxer(), this);
		pm.registerEvents(new ALL(), this);
		pm.registerEvents(new Extras(), this);
		pm.registerEvents(new TimeLord(), this);
		pm.registerEvents(new Flash(), this);
		pm.registerEvents(new RecraftGeral(), this);
		pm.registerEvents(new Jumper(), this);
		pm.registerEvents(new RDMAutomatic(), this);
		pm.registerEvents(new ShopKDRGUI(), this);
		pm.registerEvents(new EventoListeners(), this);
		pm.registerEvents(new Grappler(), this);
		pm.registerEvents(new OnevsOneKS(), this);
		pm.registerEvents(new AntiProxyListener(), this);
		pm.registerEvents(new NoBreakEvent(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
		pm.registerEvents(new Youtuber(), this);
		pm.registerEvents(new Caixas(), this);
		pm.registerEvents(new Barbarian(), this);
		pm.registerEvents(new Tornado(), this);
		pm.registerEvents(new Sonic(), this);
		pm.registerEvents(new AntiSpam(), this);
		pm.registerEvents(new PotePlaca(), this);
		pm.registerEvents(new MacroTest(), this);
		pm.registerEvents(new Cocoa(), this);
		pm.registerEvents(new ShopGUI(), this);
		pm.registerEvents(new Tank(), this);
		pm.registerEvents(new SC(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new Arena(), this);
		pm.registerEvents(new net.helixpvp.kit2.Anchor(), this);
		pm.registerEvents(new net.helixpvp.kit2.Grappler(), this);
		pm.registerEvents(new net.helixpvp.kit2.Kangaroo(), this);
		pm.registerEvents(new net.helixpvp.kit2.Barbarian(), this);
		pm.registerEvents(new net.helixpvp.kit2.PvP(), this);
		pm.registerEvents(new net.helixpvp.kit2.Viper(), this);
		pm.registerEvents(new net.helixpvp.kit2.Boxer(), this);
		pm.registerEvents(new Lapis(this), this);
		pm.registerEvents(new SoupHandlerListener(), this);
		pm.registerEvents(new EntityCalculateDamageListener(), this);
		pm.registerEvents(new Jump(), this);
		pm.registerEvents(new SpawnCMD(), this);
		pm.registerEvents(new SignListener(), this);
		pm.registerEvents(new PlayerCombatLogListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new SoupTypeGUI(), this);
		pm.registerEvents(new PlayerCompassListener(), this);
		pm.registerEvents(new LAVA(), this);
		pm.registerEvents(new AntiOP(), this);
		pm.registerEvents(new PlayerKillstreakListener(), this);
		pm.registerEvents(new PlayerDieArenaListener(), this);
	}
	
	public ScoreboardBuilder getScoreboardBuilder() {
		return scoreboardBuilder;
	}
	
	public Hologram getTopPlayersHd() {
		return topPlayersHd;
	}
	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
		// TODO Auto-generated method stub
		
	}
}
