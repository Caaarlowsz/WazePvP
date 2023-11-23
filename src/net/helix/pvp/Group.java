package net.helix.pvp;

import net.helix.pvp.command.DarKit;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Group implements CommandExecutor {
  LuckPerms api = LuckPermsProvider.get();
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("cmd.group")) {
      sender.sendMessage(ChatColor.RED + "Você não tem autorização para gerenciar grupos.");
      return true;
    } 
    if (args.length < 2) {
      sender.sendMessage(ChatColor.DARK_AQUA + "/" + label + " <Player> <Group>.");
      return true;
    } 
    net.luckperms.api.model.group.Group group = api.getGroupManager().getGroup(args[1]);
    if (group == null) {
      sender.sendMessage(ChatColor.RED + "Group não encontrado.");
      sender.sendMessage(ChatColor.RED + "Use default para o grupo Membro.");
      return true;
    } 
    if (!sender.hasPermission("cmd.group." + args[1])) {
      sender.sendMessage(ChatColor.RED + "Você não pode dar esse grupo para outras pessoas.");
      return true;
    } 
    String target = args[0];
    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + target + " parent set " + args[1]);
    Player targetPlayer;
    if ((targetPlayer = Bukkit.getPlayer(target)) != null)
      targetPlayer.sendMessage(ChatColor.RED + "Seu grupo foi alterado para " + args[1]); 
    for (Player p4 : Bukkit.getOnlinePlayers()) {
    	DarKit.sendTitle(p4, "§c§lGRUPO ALTERADO", "§f " + target + " teve seu grupo alterado para " + args[1].toUpperCase());
    	p4.playSound(p4.getLocation(), Sound.GHAST_SCREAM, 10, 10);
    	p4.sendMessage("§c§lGROUP CHANGED" + "§f" + target + " teve o grupo alterado para " + args[1].toUpperCase());
    }
    sender.sendMessage(ChatColor.RED + "Você atualizou o grupo de " + target + " para " + args[1] + " !");
    return true;
  }
}