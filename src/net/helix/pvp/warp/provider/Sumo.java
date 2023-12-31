package net.helix.pvp.warp.provider;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle;
import net.helix.pvp.warp.WarpDuoBattleHandle2;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sumo extends WarpDuoBattleHandle2 {

    public Sumo() {
        super("sumo_pos1", "sumo_pos2");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (fastChallenge.size() < 2) {
                    return;
                }

                Player p1 = fastChallenge.get(0);
                Player p2 = fastChallenge.get(1);

                if (p1 == null || p2 == null) {
                    fastChallenge.remove(p1);
                    fastChallenge.remove(p2);
                    return;
                }
                if (p1 == p2) {
                	cancel();
                	fastChallenge.remove(p1);
                	p1.sendMessage(ChatColor.RED + "A procura falhou! Tente novamente.");
                	return;
                }

                startBattle(p1, p2);
            }
        }.runTaskTimer(HelixPvP.getInstance(), 0, 2 * 20L);
    }

    @Override
    public void execute(Player player) {
        super.execute(player);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
        setItems(player);
    }

    @Override
    public void setItems(Player player) {
        super.setItems(player);
        player.getInventory().setItem(3, new ItemBuilder("§cDesafiar §7(Clique)", Material.APPLE)
                .lore("§fClique em um jogador para desafiar")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .nbt("sumo", "challenge")
                .toStack()
        );

        boolean searchPlayers = fastChallenge.contains(player);
        player.getInventory().setItem(5, new ItemBuilder("§bProcurar jogador: " + (searchPlayers ? "§aON" : "§cOFF"), Material.INK_SACK, searchPlayers ? 10 : 8)
                .lore("§fClique para procurar")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .nbt("sumo", "fast-challenge")
                .toStack()
        );
        player.updateInventory();
    }
    @EventHandler
	public void onInteract22(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player =  (Player)event.getEntity();
		if (HelixWarp.SUMO.hasPlayer(player.getName()) && !findOpponent(player).isPresent()) {
			if (event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}
    }
		
    @EventHandler
	public void onInteract22(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player =  (Player)event.getEntity();
		if (HelixWarp.SUMO.hasPlayer(player.getName()) && !findOpponent(player).isPresent()) {
			event.setCancelled(true);
		}
		else if (HelixWarp.SUMO.hasPlayer(event.getDamager().getName()) && !findOpponent((Player)event.getDamager()).isPresent()) {
			event.setCancelled(true);
		}
	}

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!HelixWarp.SUMO.hasPlayer(player.getName()) || !event.hasItem()
                || !ItemBuilder.has(event.getItem(), "sumo", "fast-challenge")) {
            return;
        }
        event.setCancelled(true);

        if (HelixCooldown.inCooldown(player.getName(), "sumo-fast-challenge")) {
            player.sendMessage("§cAguarde...");
            return;
        }
        if (fastChallenge.contains(player)) {
            fastChallenge.remove(player);
            HelixCooldown.create(player.getName(), "sumo-fast-challenge", TimeUnit.SECONDS, 3);
        }else {
            fastChallenge.add(player);
        }
        setItems(player);
        player.sendMessage(fastChallenge.contains(player) ? "§6Você entrou na fila do sumô" : "§eVocê saiu da fila do sumô");
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (!(event.getRightClicked() instanceof Player)
                || !HelixWarp.SUMO.hasPlayer(player.getName())
                || !HelixWarp.SUMO.hasPlayer(event.getRightClicked().getName())
                || player.getItemInHand() == null
                || !ItemBuilder.has(player.getItemInHand(), "sumo", "challenge")) {
            return;
        }
        event.setCancelled(true);

        Player target = (Player) event.getRightClicked();

        if (battlingPlayers.containsKey(target)) {
            player.sendMessage("§cEsse jogador já está lutando");
            return;
        }

        if (HelixCooldown.inCooldown(target.getName(), "sumo-challenge-" + player.getName())) {
            startBattle(player, target);
            return;
        }

        if (HelixCooldown.inCooldown(player.getName(), "sumo-challenge-" + target.getName())) {
            player.sendMessage("§eVocê já convidou este jogador recentemente");
            return;
        }

        HelixCooldown.create(player.getName(), "sumo-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
        target.sendMessage("§e§lSUMO §eVocê foi convidado por " + player.getName() + " para uma batalha!");
        player.sendMessage("§6§lSUMO §6Você convidou " + target.getName() + " para uma batalha!");
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)
                || !(event.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();

        Optional<Player> targetOptional;
        if (!(targetOptional = findOpponent(damager)).isPresent()) {
            return;
        }

        Player target = targetOptional.get();

        if (target.equals(victim)) {
            event.setDamage(0);
        }
    }
    
    @EventHandler
    public void onInteracct(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block block = event.getClickedBlock();
            if(block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST))  {
            	if (!block.hasMetadata("PlacedBlock")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("Você só pode abrir o báu do Feast!");
            }
            }
        }
            }
        
       
    
    
    @EventHandler
    public void onMoveF(PlayerMoveEvent event) {
        Player player = event.getPlayer();
      

        if (!HelixWarp.SUMO.hasPlayer(player.getName())) {
            return;
        }

        if (!findOpponent(player).isPresent()) {
            return;
        }
        if (!(player.getLocation().getY() <= 62)) {
        	return;
        }
        Player target = findOpponent(player).get();
        Random random = new Random();
        finalizeBattle(player);

        HelixPlayer loserUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        HelixWarp.SUMO.send(player);

        int loserWithdrawnCoins = random.nextInt(20 + 1 - 8) + 8;
        if ((loserUser.getPvp().getCoins() - loserWithdrawnCoins) >= 0) {
            loserUser.getPvp().removeCoins(loserWithdrawnCoins);
            player.sendMessage("§c§l[-] §c" + loserWithdrawnCoins + " coins");
        }else {
            loserUser.getPvp().setCoins(0);
        }
        if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
			victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
			player.sendMessage("§c§l[-] §c10 XP");
		}else {
			victimHelixPlayer.getPvp().setXp(0);
			player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
		}
        HelixPlayer killerUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
        int winnerCoins = random.nextInt(80 + 1 - 25) + 25;
        HelixWarp.SUMO.send(target);
        target.playSound(target.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
        target.sendMessage("§6§lSUMO §eVocê ganhou a luta contra " + player.getName());
        killerUser.getPvp().addCoins(winnerCoins);
        killerUser.getPvp().addWinsSumo(1);;
        killerUser.getPvp().addWinstreakSumo(1);
        killerUser.getPvp().addXP(15);
        killerUser.getPvp().addKills(1);
        target.sendMessage("§6§l[+] §6" + winnerCoins + " coins");
        target.sendMessage("§6§l[+] §a15XP");
        
        loserUser.getPvp().addDeathsSumo(1);
        player.sendMessage("§4§lSUMO §4Você perdeu a luta contra " + target.getName());
HelixPlayer killerAccount = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreaksumo();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0") || (String.valueOf(killstreak).contains("3")) && killstreak != 0)) {
			Bukkit.broadcastMessage("§6§lWINS §e" + target.getName() + " tem um killstreak de §b" + killstreak + "§e no Sumo!");
		}
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int killstreak2 = victimA.getPvp().getWinstreaksumo();
		if (killstreak2 >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreaksumo() + " §e no Sumo para §6" +
	                killerAccount.getName() + "§e!"));
			victimA.getPvp().setWinstreaksumo(0);
		}
        HelixBukkit.getInstance().getPlayerManager().getController().save(loserUser);
        HelixBukkit.getInstance().getPlayerManager().getController().save(killerUser);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if (!HelixWarp.SUMO.hasPlayer(player.getName())) {
            return;
        }

        if (!block.getType().toString().contains("WATER") || !findOpponent(player).isPresent()) {
            return;
        }

        Player target = findOpponent(player).get();
        Random random = new Random();
        finalizeBattle(player);

        HelixPlayer loserUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        HelixWarp.SUMO.send(player);

        int loserWithdrawnCoins = random.nextInt(20 + 1 - 8) + 8;
        if ((loserUser.getPvp().getCoins() - loserWithdrawnCoins) >= 0) {
            loserUser.getPvp().removeCoins(loserWithdrawnCoins);
            player.sendMessage("§c§l[-] §c" + loserWithdrawnCoins + " coins");
        }else {
            loserUser.getPvp().setCoins(0);
        }
        if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
			victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
			player.sendMessage("§c§l[-] §c10 XP");
		}else {
			victimHelixPlayer.getPvp().setXp(0);
			player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
		}
        HelixPlayer killerUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
        int winnerCoins = random.nextInt(80 + 1 - 25) + 25;
        HelixWarp.SUMO.send(target);
        target.playSound(target.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);

        target.sendMessage("§a§lSUMO §aVocê ganhou a luta contra " + player.getName());
        killerUser.getPvp().addCoins(winnerCoins);
        killerUser.getPvp().addWinsSumo(1);;
        killerUser.getPvp().addWinstreakSumo(1);
        killerUser.getPvp().addXP(15);
        killerUser.getPvp().addKills(1);
        target.sendMessage("§6§l[+] §6" + winnerCoins + " coins");
        target.sendMessage("§6§l[+] §a15XP");
        
        loserUser.getPvp().addDeathsSumo(1);
        player.sendMessage("§c§lSUMO §cVocê perdeu a luta contra " + target.getName());
HelixPlayer killerAccount = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreaksumo();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0") || (String.valueOf(killstreak).contains("3")) && killstreak != 0)) {
			Bukkit.broadcastMessage("§6§lWINS §e" + target.getName() + " tem um winstreak de §b" + killstreak + "§e no Sumo!");
		}
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int killstreak2 = victimA.getPvp().getWinstreaksumo();
		if (killstreak2 >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreaksumo() + " §e no Sumo para §6" +
	                killerAccount.getName() + "§e!"));
			victimA.getPvp().setWinstreaksumo(0);
        HelixBukkit.getInstance().getPlayerManager().getController().save(loserUser);
        HelixBukkit.getInstance().getPlayerManager().getController().save(killerUser);
    }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Optional<Player> targetOptional;
        if (!(targetOptional = findOpponent(player)).isPresent()) {
            return;
        }
        Player target = targetOptional.get();
        finalizeBattle(player);

        HelixWarp.SUMO.send(target);
        target.sendMessage("§2Seu oponente deslogou");
    }
}