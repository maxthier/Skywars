package net.qubikstudios.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.qubikstudios.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;

public class KillListener implements Listener {

    @EventHandler
    public void handleKill(PlayerDeathEvent ev){
        Player p = ev.getEntity();
        if(p.getKiller() != null){
             ev.deathMessage(Skywars.prefix.append(Component.text(p.getName()).color(NamedTextColor.GOLD)).append(Component.text(" killed by ").color(NamedTextColor.GRAY)).append(Component.text(p.getKiller().getName()).color(NamedTextColor.GOLD)));
        }else{
            ev.deathMessage(Skywars.prefix.append(Component.text(p.getName()).color(NamedTextColor.GOLD).append(Component.text(" died")).color(NamedTextColor.GRAY)));
        }
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if(players.size() == 2){
            players.forEach(player -> player.teleport(Skywars.hub));
            players.remove(p);
            Player[] winner = players.toArray(new Player[players.size()]);
            players.forEach(player -> player.getName());
            Bukkit.broadcast(Skywars.prefix.append(Component.text(winner[0].getName()).color(NamedTextColor.GOLD)).append(Component.text(" won the game!").color(NamedTextColor.GREEN)));
            Bukkit.broadcast(Skywars.prefix.append(Component.text("the server stops in 10 seconds!").color(NamedTextColor.DARK_GREEN)));
            Bukkit.getScheduler().runTaskLater(Skywars.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Bukkit.shutdown();
                }
            }, 200);
        }else{
            p.kick(Component.text("You lost the game!"));
        }
    }

}
