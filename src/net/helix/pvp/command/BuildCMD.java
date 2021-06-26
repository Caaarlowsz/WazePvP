package net.helix.pvp.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildCMD implements Listener, CommandExecutor {
	
	private static List<String> build = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("helix.cmd.build")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (build.contains(sender.getName())) {
			build.remove(sender.getName());
		}else {
			build.add(sender.getName());
		}
		
		sender.sendMessage(build.contains(sender.getName()) ? "�aAgora voc� pode construir." : "�eAgora voc� n�o pode construir.");
		return true;
	}
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		if (!build.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!build.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}

}
