package net.helix.pvp.command;



import java.security.AllPermission;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.defaults.TellCommand;
import org.bukkit.entity.Player;


import net.helix.pvp.command.BukkitCommandFramework.Command;
import net.helix.pvp.command.BukkitCommandFramework.CommandArgs;
public class Tell extends CommandClass {

	ArrayList<UUID> tell = new ArrayList<>();

	@Command(name = "tell")
	public void onTell(CommandArgs args) {
		if (!args.isPlayer())
			return;

		Player player = args.getPlayer();
		String[] a = args.getArgs();

		if (player == null) {
			return;
		}

		if (a.length == 0) {
			player.sendMessage("§3§lTELL §fUse /tell <player> <mensagem>!");
			return;
		}

		if (a.length == 1) {
			if (a[0].equalsIgnoreCase("off")) {
				if (!tell.contains(player.getUniqueId())) {
					tell.add(player.getUniqueId());
					player.sendMessage("§a§lTELL §fVocê agora está com o tell §c§lOFF!");
				} else {
					player.sendMessage("§a§lTELL §fVocê já está com o tell §c§lOFF!");
				}
			} else if (a[0].equalsIgnoreCase("on")) {
				if (tell.contains(player.getUniqueId())) {
					tell.remove(player.getUniqueId());
					player.sendMessage("§a§lTELL §fVocê agora está com o tell §a§lON!");
				} else {
					player.sendMessage("§a§lTELL §fVocê já está com o tell §a§lON!");
				}
			}
		}

		if (a.length > 1) {
			Player target = Bukkit.getPlayer(a[0]);

			if (target == null) {
				player.sendMessage("§a§lTELL §fPlayer offline!");
				return;
			}
			
			else if (player == target) {
					player.sendMessage("§cVocê não pode falar consigo mesmo!");
					return;
				}
				else if (tell.contains(target.getUniqueId())) {
					player.sendMessage("§a§lTELL §fO tell desse jogador está §c§lOFF!");
					return;
				}
					String message = "";

					for (int i = 1; i < a.length; i++) {
						message += a[i] + " ";
					}
						final String tiCopy = message;
					player.sendMessage("§a§lTELL §5[§fVocê -> " + target.getName() + "§7] §f" + message);
					target.sendMessage(
							"§a§lTELL §f[" + player.getName() + " §7-> §fVocê" + "§f] " + message);
				        
					
					Bukkit.getOnlinePlayers().stream()
			        .filter(p -> !SocialSpy.toggle.containsKey(p.getName()) && p.hasPermission("kombo.cmd.report"))
			        .forEach(p ->
			                   p.sendMessage("§7§o(TELL SPY) §8§o[§7§o" + player.getName() + " §f» §7§o" + target.getName() +
			                            "§8§o] §e§o" + tiCopy));
				
					     
			              
					        
			

					}
				}

			
			}
		
	



