package net.helix.pvp.command;



import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

public class ResetEventoClass implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (!player.hasPermission("comando.resetareventoclass")) {
            	player.sendMessage(ChatColor.RED + "Você não tem autorização.");
            	return true;
            }
            EventoUtils.resetEventoClass();
            EventoUtils.getEventoPlayers().forEach(p -> {
            	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
                HelixWarp.SPAWN.send(p);
                p.chat("/spawn");
                p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
            });
            if (HelixPvP.getInstance().getEventManager().isRunningRDM()) {
            	HelixPvP.getInstance().getEventManager().setRdmAutomatic(null);
            }
        }
        else {
            sender.sendMessage("NO CONSOLE");
        }
        return true;
    }

}
