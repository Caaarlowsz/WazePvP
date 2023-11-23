package net.helix.pvp.evento;



import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;
import net.helix.pvp.HelixPvP;

@Getter
public enum EventoType {

//StaffVsPlayers
    PVP("ArenaPvP", Arrays.asList("Olá jogadores! Bem vindo ao evento &c&lArena PvP&l&f!", "No evento vocês jogadores, receberam kit com itens", "Depois de certo tempo todos começaram a ser teleportado para novas arenas.", "Todos terão 20 segundos para se preparar.", "Agora vamos passar as &c&lREGRAS DO EVENTO&l&f:", "&fNão tente escapar da arena.", "&fNão faça times a mais que o evento proporcionar." , "Iniciando evento! Boa sorte!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("ARENAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoArenaX"), HelixPvP.getInstance().getConfig().getInt("EventoArenaY"), HelixPvP.getInstance().getConfig().getInt("EventoArenaZ"))),
    LAVA("Lava", Arrays.asList("Olá jogadores! Bem vindo ao evento LAVA", "Ao começo todos receberam um kit com sopas e recraft.", "Todos teram que tankar a lava que foram colocados!", "Suas sopas não serão restocadas durante o evento! Então use elas com sabedoria", "Todos terão 10 segundos para se preparar!", "Iniciando! Boa sorte!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("LAVAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoLavaX"), HelixPvP.getInstance().getConfig().getInt("EventoLavaY"), HelixPvP.getInstance().getConfig().getInt("EventoLavaZ"))),
    MDR("Mdr", Arrays.asList("Bem vindo ao evento mãe da rua!", "No evento vocês receberam um pack de maça dourada!", "O objetivo é passar a rua sem ser morto!", "Se você morrer será desqualificado.", "Se ficarem brincando ou desobedecer ordems do promotor também serão desqualificados.", "Todos terão 10 segundos para se preparar!", "Começando evento! Boa sorte!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("MDRMUNDO")), 326877.559, 70.0000000000, 345987.734)),
    _1v1("1v1", Arrays.asList("Bem vindo ao evento 1v1!", "Ao inicio vocês serão puxados de 2 em 2 para lutar", "Quem ganhar voltara para o evento!", "E quem perder voltara para o spawn!", "Começando evento! Boa sorte."), new Location(Bukkit.getWorlds().get(0), -56.778D, 144.47614D, -396.517D)),
    STAFFVSPLAYERS("StaffVsPlayers", Arrays.asList("Bem vindo ao evento Staff vs Players!", "Ao inicio do evento os staffs e players receberam itens diferentes", "Staff receberá full dima e players full couro!", "E staff batalhara contra players!", "Ja que não tem como definir um ganhador nesse evento ele será sempre 4FUN", "Começando evento! Boa sorte."), new Location(Bukkit.getWorlds().get(0), 156161.441, 81.0000, 158151.654)),
    ;//156161.441 81.0000 158151.654
    private final String name;
    private final List<String> explicacao;
    private final Location location;
    EventoType(String name, List<String> explicacao, Location location) {
        this.name = name;
        this.explicacao = explicacao;
        this.location = location;
    }

    public static EventoType getEventoByName(String name) {
        for (EventoType evento : EventoType.values()) {
        	 if (evento.getName().equalsIgnoreCase(name))
                 return evento;
         }
        return null;
    }
    public String getName() {
		return name;
	}
    public List<String> getExplicacao() {
		return explicacao;
	}
    public Location getLocation() {
		return location;
	}

    public static void explicarEvento(EventoType evento) {
        List<String> explic = evento.getExplicacao();
        int actualsec = 1;
        for (String message : explic) {
            Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), () -> {
            	EventoUtils.getEventoPlayers().forEach(p -> {
               p.sendMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + " §f" + ChatColor.translateAlternateColorCodes('&', message));
               p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        });
            	
    }, actualsec * 20);
            actualsec += 5;
        }
}
}


// mdr 801.5 100 519.5
// lava 641.5 118 518.5
// pvp 732.5 80 521.5
// 1v1 868.5 95 457.5
