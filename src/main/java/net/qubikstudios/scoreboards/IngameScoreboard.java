package net.qubikstudios.scoreboards;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.qubikstudios.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.nio.Buffer;

public class IngameScoreboard {

    private Scoreboard board;
    private Objective obj;

    public IngameScoreboard(Player player){
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("WaitingBoard", "dummy",
                Component.text("» ").color(NamedTextColor.BLUE)
                        .append(Component.text("Sky").color(NamedTextColor.DARK_AQUA))
                        .append(Component.text("Wars ").color(NamedTextColor.RED))
                        .append(Component.text("«").color(NamedTextColor.BLUE)));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team spacer = board.registerNewTeam("spacer");
        spacer.addEntry("§f"); spacer.addEntry("§c"); spacer.addEntry("§0"); spacer.addEntry("§e");
        spacer.prefix(Component.text("                                           ").color(NamedTextColor.GRAY).decorate(TextDecoration.STRIKETHROUGH));

        obj.getScore("§f").setScore(15);
        obj.getScore("§3Team: §7" + "Comming Soon").setScore(14);
        Team kit = board.registerNewTeam("kit");
        kit.addEntry("§1");
        kit.prefix(Component.text("Kit: ").color(NamedTextColor.DARK_AQUA).append(Component.text("Comming Soon").color(NamedTextColor.GRAY)));
        obj.getScore("§1").setScore(13);
        obj.getScore("§3Server: §7" + TimoCloudAPI.getBukkitAPI().getThisServer().getName()).setScore(12);
        Team kills = board.registerNewTeam("kills");
        kills.addEntry("§1");
        kills.prefix(Component.text("Kills: ").color(NamedTextColor.DARK_AQUA).append(Component.text(0).color(NamedTextColor.GRAY)));
        obj.getScore("§2").setScore(12);
        Team players = board.registerNewTeam("players");
        players.addEntry("§3");
        players.prefix(Component.text("Players: ").color(NamedTextColor.DARK_AQUA).append(Component.text(Bukkit.getOnlinePlayers().size()).color(NamedTextColor.GRAY)));
        obj.getScore("§3").setScore(11);
        Team map = board.registerNewTeam("map");
        map.addEntry("§2");
        map.prefix(Component.text("Map: ").color(NamedTextColor.DARK_AQUA).append(Component.text(Skywars.getMap().getName()).color(NamedTextColor.GRAY)));
        obj.getScore("§2").setScore(11);

        obj.getScore("§3").setScore(10);
        obj.getScore("§c").setScore(9);
        obj.getScore("§3Website: §9Qubik-Studios.net").setScore(8);
        obj.getScore("§3Discord: §9discord.Qubik-Studios.net").setScore(7);
        obj.getScore("§3Sponsored by §bDreamHosting.at").setScore(6);
        obj.getScore("§0").setScore(5);
        player.setScoreboard(board);
    }

    /*
    Skywars
15
14    Map
13    » Valley
12
11    Kills
10    » 2
9
8     Players
7     » 2
6
5     Kit
4      » Tank
     */

}
