package net.helix.pvp.command;


	import org.bukkit.Sound;
	import org.bukkit.command.Command;
	import org.bukkit.command.CommandExecutor;
	import org.bukkit.command.CommandSender;
	import org.bukkit.entity.Player;

	public class Regras 
	implements CommandExecutor
	{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	  Player p = (Player)sender;
	  if (label.equalsIgnoreCase("regras"))
	  {
		p.sendMessage("�cLeia as regras");  
		p.sendMessage("");
		p.sendMessage("�e1� Uso de Trapa�as: Usar Hack ou Cheats � extremamente proibido.");
	    p.sendMessage("�e2� Ass�dio: N�o fazer coment�rios ofensivos com finalidades sexuais.");
	    p.sendMessage("�e3� Amea�as: N�o utilizar discursos de odio relacionado a� agress�o ou qualquer tipo de ato maldoso direcionado ao membro amea�ado.");
	    p.sendMessage("�e4� Brigas/Discuss�es (ofensas): N�o discuta com a finalidade de ofender, seja por meio verbal ou por escrito, caso aconte�a procure um respons�vel.");
	    p.sendMessage("�e5� Com�rcio: � proibida, fazer revenda/com�rcio em nossos chats ou chamadas, sem autoriza��o.");
	    p.sendMessage("�e6� Discrimina��o: � uma a��o preconceituosa em rela��o a uma pessoa ou grupo de pessoas. Seja por quest�es raciais, de g�nero, orienta��o sexual, nacionalidade, religi�o entre outras, sem consentimento da mesma.");
	    p.sendMessage("�e7� Divulga��o de servidores: Divulgar outro servidor sem ser a Sloper no tell de algum membro, ir� ter puni��o de banimento permanente.");
	    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
	  }
	  return false;
	}
	}
