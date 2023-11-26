package net.helix.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.kit.provider.Wither;
import net.helix.pvp.listener.PlayerJoinListener;
import net.helixpvp.kit2.Anchor;
import net.helixpvp.kit2.AntiStomperReal;
import net.helixpvp.kit2.Archer;
import net.helixpvp.kit2.Avatar;
import net.helixpvp.kit2.Boxer;
import net.helixpvp.kit2.Camel;
import net.helixpvp.kit2.Critical;
import net.helixpvp.kit2.Deshfire;
import net.helixpvp.kit2.EnderMage;
import net.helixpvp.kit2.Firebender;
import net.helixpvp.kit2.Fireman;
import net.helixpvp.kit2.Barbarian;
import net.helixpvp.kit2.Fisherman;
import net.helixpvp.kit2.GladiatorListener;
import net.helixpvp.kit2.Grappler;
import net.helixpvp.kit2.Kangaroo;
import net.helixpvp.kit2.Leech;
import net.helixpvp.kit2.Meteor;
import net.helixpvp.kit2.Sight;
import net.helixpvp.kit2.Snail;
import net.helixpvp.kit2.MilkMan;
import net.helixpvp.kit2.Monk;
import net.helixpvp.kit2.Nenhum;
import net.helixpvp.kit2.Ninja;
import net.helixpvp.kit2.Poseidon;
import net.helixpvp.kit2.PvP;
import net.helixpvp.kit2.QuickDropper;
import net.helixpvp.kit2.Scout;
import net.helixpvp.kit2.Stomper;
import net.helixpvp.kit2.Switcher;
import net.helixpvp.kit2.Thor;
import net.helixpvp.kit2.Viper;
import net.helixpvp.kit2.WaterBender;


public enum HelixKit2  {
	NENHUM("None2", 0, 0 , new Nenhum() , new ItemStack(Material.BARRIER), "Sem habilidades" , 1),
	PVP("PvP-2", 0, 0 , new PvP() , new ItemStack(Material.STONE_SWORD), "Tenha sharp 1 na espada" , 1),
	ANCHOR("Anchor", 10000, 0, new Anchor() , new ItemStack(Material.ANVIL), "Não receba ou de knockback" , 1),
	ANTISTOMPER("AntiStomper", 8000, 0, new AntiStomperReal() , new ItemStack(Material.DIAMOND_HELMET), "Imune a stompers" , 1),
	NINJA("Ninja", 10000, 0, new Ninja() , new ItemStack(Material.EMERALD), "Teleporte ao ultimo inimigo hitado", 1),
	POSEIDON("Poseidon", 4000, 0, new Poseidon() , new ItemStack(Material.WATER_BUCKET), "Forte na água", 1),
	AVATAR("Avatar", 12000, 0, new Avatar() , new ItemStack(Material.BEACON), "Controle os 4 elementos", 1),
	LEECH("Leech", 10000, 0, new Leech() , new ItemStack(Material.REDSTONE), "Roube vida dos inimigos", 1),
	ARCHER("Archer", 0, 0, new Archer() , new ItemStack(Material.BOW), "Receba arco e flecha!", 1),
	BOXER("Boxer", 15000, 0, new Boxer() , new ItemStack(Material.QUARTZ), "De mais dano e leve menos.", 1),
    VIPER("Viper", 10000, 0, new Viper() , new ItemStack(Material.SPIDER_EYE), "De veneno ao hitar", 1),
    SIGHT("Sight", 10000, 0, new Sight(), new ItemStack(Material.INK_SACK, 1 , (short)1), "De cegueira ao hitar!", 1),
    NEO("Neo", 8000, 0, new net.helixpvp.kit2.NEO() , new ItemStack(Material.BARRIER), "Imune a varios kits", 1),
	QUICKDROPPER("QuickDropper", 2500, 2500, new QuickDropper() , new ItemStack(Material.BOWL), "Drope potes automaticamente", 1),
	DESHFIRE("Deshfire", 2500, 2500, new Deshfire() , new ItemStack(Material.REDSTONE_BLOCK), "De um desh e bote fogo", 1),
	KANGAROO("Kangaroo", 0, 0, new Kangaroo() , new ItemStack(Material.FIREWORK), "De doublejumps", 1),
	VACUUM("Vacuum", 10000, 10000, new EnderMage() , new ItemStack(Material.ENDER_PORTAL_FRAME), "Empurre seus inimigos", 1),
	CRITICAL("Critical", 9000, 0, new Critical() , new ItemStack(Material.REDSTONE_BLOCK), "De hits críticos", 1),
	STOMPER("Stomper", 15000, 0, new Stomper() , new ItemStack(Material.IRON_BOOTS), "Esmague seus inimigos.", 1),
	THOR("Thor", 2500, 2500, new Thor() , new ItemStack(Material.GOLD_AXE), "Invoque raios.", 1),
	WATERBENDER("Waterbender", 12000, 0, new WaterBender(), new ItemStack(Material.LAPIS_ORE), "Crie uma prisão de água", 1),
	FIREBENDER("Firebender", 12000, 0, new Firebender(), new ItemStack(Material.REDSTONE_ORE), "Crie uma prisão de fogo", 1),
	GLADIATOR("Gladiator", 15000, 0, new GladiatorListener() , new ItemStack(Material.IRON_FENCE), "Desafie seu inimigo para 1v1.", 1),
	CAMEL("Camel", 9000, 9000, new Camel() , new ItemStack(Material.SAND), "Forte em desertos.", 1),
	BARBARIAN("Barbarian", 18000, 0, new Barbarian(), new ItemStack(Material.WOOD_SWORD), "Sua espada evolue a cada kill!", 1),
	SNAIL("Snail", 10000, 0, new Snail() , new ItemStack(Material.FERMENTED_SPIDER_EYE), "De lentidão a cada hit", 1),
	FIREMAN("Fireman", 10000, 0, new Fireman() , new ItemStack(Material.LAVA_BUCKET), "De fogo a cada hit.", 1),
	MONK("Monk", 9000, 0, new Monk() , new ItemStack(Material.BLAZE_ROD), "Bagunçe o inventario do inimigo.", 1),
	FISHERMAN("Fisherman", 9000, 0, new Fisherman() , new ItemStack(Material.FISHING_ROD), "Puxe jogadores com sua vara de pesca.", 1),
	SWITCHER("Switcher", 8000, 0, new Switcher() , new ItemStack(Material.SNOW_BALL), "Troque de lugar com o inimigo.",  1),
	METEOR("Meteor", 8000, 0, new Meteor() , new ItemStack(Material.FIREBALL), "Seja um meteoro.",  1),
    WITHER("Wither", 10000, 500, new net.helixpvp.kit2.Wither(), new ItemStack(Material.SKULL_ITEM, 1 , (short)1), "Seja um wither", 2),
	GRAPPLER("Grappler", 10000, 0, new Grappler() , new ItemStack(Material.LEASH), "Voe por ai com sua cordinha", 1);
	
	private final String name;
	private final String description;
	private final int price;
	private final int pricecash;
	private final KitHandler2 handler2;
	private final ItemStack icon;
	private final int page;
	private static String nl = System.getProperty("line.separator");
	static {
		getKits().forEach(kit -> 
			Bukkit.getPluginManager().registerEvents(kit.getHandler(), HelixPvP.getInstance())
		);
	}
	
	public static List<HelixKit2> getKits() {
		return Arrays.asList(values());
	}
	
	public static Optional<HelixKit2> findKit(String name) {
		return getKits().stream().filter(
				kit -> kit.toString().equalsIgnoreCase(name) 
					|| kit.getName().equalsIgnoreCase(name)
		).findFirst();
	}
	
	HelixKit2(String name, int price, int pricecash, KitHandler2 handler2, ItemStack icon, String description , int page) {
		this.name = name;
		this.price = price;
		this.pricecash = pricecash;
		this.handler2 = handler2;
		this.icon = icon;
		this.description = description;
		this.page = page;
	}
	
	public void send(Player player) {
		if (!(player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && PlayerJoinListener.fall.contains(player)  && EnderMageReal.isSpawn(player.getLocation()))) {
	      	player.closeInventory();
	  		return;
	  	 }
		if (KitManager2.getPlayer(player.getName()).getkit2().getName() == KitManager.getPlayer(player.getName()).getKit().getName()) {
			player.sendMessage("§cVocê já tem esse kit escolhido como secundário!");
			player.closeInventory();
			return;
		}
		if (KitManager2.getPlayer(player.getName()).getkit2().toString() == KitManager.getPlayer(player.getName()).getKit().toString()) {
			if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.NENHUM && KitManager.getPlayer(player.getName()).getKit() == HelixKit.NENHUM) {
				KitManager2.getPlayer(player.getName()).setkit2(this);
				player.sendMessage("§b" + name + " selecionado!");
				return;
			}
			if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.PVP && KitManager.getPlayer(player.getName()).getKit() == HelixKit.PVP) {
				KitManager2.getPlayer(player.getName()).setkit2(this);
				player.sendMessage("§b" + name + " selecionado!");
				return;
			}
			player.sendMessage("§cVocê já tem esse kit escolhido como secundário!");
			player.playSound(player.getLocation(), Sound.GHAST_MOAN, 1f, 1f);
			player.closeInventory();
			return;
		}
		KitManager2.getPlayer(player.getName()).setkit2(this);
		if (KitManager2.getPlayer(player.getName()).haskit2(PVP)) {
		
		player.sendMessage("§bPvP selecionado!");
		} else {
			player.sendMessage("§b" + name + " selecionado!");
		
		}
		
	}
	
	public KitHandler2 getHandler() {
		return handler2;
	}
	 public static HelixKit2 getKitTypeByName(String kitname) {
		 HelixKit2[] values;
	        for (int length = (values = values()).length, i = 0; i < length; ++i) {
	            final HelixKit2 kitType = values[i];
	            if (kitType.name().equalsIgnoreCase(kitname)) {
	                return kitType;
	            }
	        }
	        return null;
	    }

	public boolean isFree() {
		return price == 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	public int getPage() {
		return page;
	}
	public int getPriceCash() {
		return pricecash;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	public String getDescription() {
		return description.replace("&", "§");
	}
}
