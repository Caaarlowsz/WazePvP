package net.helix.pvp.command;


import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.query.QueryOptions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.tablist.SortingManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagCommand implements CommandExecutor {

	Map<String, Long> cooldowns = new HashMap<String, Long>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
       SortingManager S =  TabAPI.getInstance().getSortingManager();
        LuckPerms api = LuckPermsProvider.get();
        TabAPI apitab = TabAPI.getInstance();
        Player player = (Player) sender;
        Set<Group> groups = api.getGroupManager().getLoadedGroups();
    	List<String> groupsList = new ArrayList<>();
        if (args.length == 0) {
        	
        	       
        	for(Group group : groups) {
        	    groupsList.add(group.getName());
        	}
Player p = (Player)sender;
if (p.hasPermission("helix.tag.diretor")) {
    ChatInterativo.Comando(p.getName(), "§3§lDIRETOR", "/tag diretor", "§eExemplo: §3[Diretor] §3" + p.getName());
    }
if (p.hasPermission("helix.tag.subdiretor")) {
    ChatInterativo.Comando(p.getName(), "§0§lSUBDIRETOR", "/tag subdiretor", "§eExemplo: §0[SubDiretor] §0" + p.getName());
    }
                    if (p.hasPermission("helix.tag.gerente")) {
                    ChatInterativo.Comando(p.getName(), "§4§lGERENTE", "/tag gerente", "§eExemplo: §4[Gerente] §4" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.admin")) {
                        ChatInterativo.Comando(p.getName(), "§c§lADMIN", "/tag admin", "§eExemplo: §c[Admin] §c" + p.getName());
                        }	
                    if (p.hasPermission("helix.tag.coord")) {
                    ChatInterativo.Comando(p.getName(), "§5§lCOORD", "/tag coord", "§eExemplo: §5[Coord] §5" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.invest")) {
                    ChatInterativo.Comando(p.getName(), "§a§lINVEST", "/tag invest", "§eExemplo: §a[Investidor] §a" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.mod")) {
                    ChatInterativo.Comando(p.getName(), "§2§lMODERADOR", "/tag mod", "§eExemplo: §2[Moderador] §2" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.ajudante")) {
                    ChatInterativo.Comando(p.getName(), "§e§lAJUDANTE", "/tag ajudante", "§eExemplo: §d[Ajudante] §d" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.estagiario")) {
                        ChatInterativo.Comando(p.getName(), "§d§lESTAGIÁRIO", "/tag estagiario", "§eExemplo: §e[Estagiário] §e" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.construtor")) {
                        ChatInterativo.Comando(p.getName(), "§a§lCONSTRUTOR", "/tag construtor", "§eExemplo: §a§lCONSTRUTOR §a" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.famoso")) {
                    ChatInterativo.Comando(p.getName(), "§8§lFAMOSO", "/tag famoso", "§eExemplo: §5§lFAMOSO §5" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.tiktoker")) {
                    ChatInterativo.Comando(p.getName(), "§b§lTIKTOKER§6§l+", "/tag tiktoker", "§eExemplo: §9§lTIKTOKER §9" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.yt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lYT", "/tag yt", "§eExemplo: §c§lYT §c" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.streamer")) {
                    ChatInterativo.Comando(p.getName(), "§5§lSTREAMER", "/tag streamer", "§eExemplo: §5§lSTREAMER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.miniyt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lMINIYT", "/tag miniyt", "§eExemple: §c§lMINIYT §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.beta")) {
                    ChatInterativo.Comando(p.getName(), "§b§lIMPERADOR", "/tag imperador", "§eExemple: §b§lIMPERADOR §b" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.diamond")) {
                    ChatInterativo.Comando(p.getName(), "§6§lLENDARIO", "/tag lendario", "§eExample: §6§lLENDÁRIO §6" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.emerald")) {
                    ChatInterativo.Comando(p.getName(), "§6§lHERÓI", "/tag heroi", "§eExemplo: §6§lHERÓI §6" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.campeao")) {
                    ChatInterativo.Comando(p.getName(), "§3§lCAMPEÃO", "/tag campeao", "§eExemplo: §6§lCAMPEÃO §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.nitro")) {
                    ChatInterativo.Comando(p.getName(), "§d§lNITRO", "/tag nitro", "§eExemplo: §d§lNITRO §d" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.friend")) {
                        ChatInterativo.Comando(p.getName(), "§c§lFRIEND", "/tag friend", "§eExemplo: §c§lFRIEND §c" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2025")) {
                        ChatInterativo.Comando(p.getName(), "§a§l2025", "/tag 2025", "§eExemplo: §a§l2025 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2024")) {
                        ChatInterativo.Comando(p.getName(), "§1§l2024", "/tag 2024", "§eExemplo: §1§l2024 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2023")) {
                        ChatInterativo.Comando(p.getName(), "§b§l2023", "/tag 2023", "§eExemplo: §b§l2023 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.natal")) {
                        ChatInterativo.Comando(p.getName(), "§c§lNATAL", "/tag natal", "§eExemplo: §c§lNATAL §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.winner")) {
                        ChatInterativo.Comando(p.getName(), "§2§lWINNER", "/tag winner", "§eExemplo: §2§lWINNER §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.top1")) {
                    ChatInterativo.Comando(p.getName(), "§9§lTOP1", "/tag top1", "§eExemplo: §9§lTOP1 §7" + p.getName());
                    }              
                    ChatInterativo.Comando(p.getName(), "§7§lMEMBRO", p.hasPermission("helix.tag.membro") ? "/tag membro" : "/tag default", "§eExemplo: §7" + p.getName());
                    return true;
              }

            
        


        if (!player.hasPermission("helix.tag." + args[0])) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }
        if (cooldowns.containsKey(player.getName() + "EPT")) {
            if (cooldowns.get(player.getName() + "EPT") > System.currentTimeMillis()) {
                long timeleft = (cooldowns.get(player.getName() + "EPT") - System.currentTimeMillis()) / 1000;
                player.sendMessage(ChatColor.RED + "Espere " + timeleft +" para mudar a tag novamente!");
                return true;
            }
        }

try {
	
        String prefix = api.getGroupManager().getGroup(args[0]).getCachedData().getMetaData().getPrefix();

        // Get an OfflinePlayer object for the player

       


        // Load, modify & save the user in LuckPerms.
        api.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {

            // Remove all other prefixes the user had before.
            user.data().clear(NodeType.PREFIX::matches);

            // Find the highest priority of their other prefixes
            // We need to do this because they might inherit a prefix from a parent group,
            // and we want the prefix we set to override that!
            Map<Integer, String> inheritedPrefixes = user.getCachedData().getMetaData(QueryOptions.nonContextual()).getPrefixes();
            int priority = inheritedPrefixes.keySet().stream().mapToInt(i -> i + 10).max().orElse(10);

            // Create a node to add to the player.
            Node node = PrefixNode.builder(prefix, priority).build();

            // Add the node to the user.
            user.data().add(node);

            // Tell the sender.
            cooldowns.put(player.getName() + "EPT", System.currentTimeMillis() + 7 * 1000);
            sender.sendMessage("§a§lTAG: §fSua tag foi alterada para " + (args[0].equalsIgnoreCase("Membro") || (args[0].equalsIgnoreCase("default")) ? "§7§lMEMBER" : ChatColor.RESET + prefix.replace("&", "§")));
            apitab.getPlayer(player.getName()).setTemporaryGroup(args[0]);
            Bukkit.getConsoleSender().sendMessage(player.getName() + " alterou a tag para " + args[0]);
        });
} catch (NullPointerException e) {
	sender.sendMessage(ChatColor.RED + "§4§lTAG: Tag inexistente!");
}

        return true;
    }
}