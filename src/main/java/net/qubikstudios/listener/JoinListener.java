package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class JoinListener implements Listener {

    @EventHandler
    public void HandlePlayerJoin(PlayerJoinEvent ev){
        Player p = ev.getPlayer();
        switch (Skywars.state){
            case STARTING:
                if(Skywars.getMaxPlayers() <= Bukkit.getOnlinePlayers().size()){
                    p.kickPlayer("§cTest");
                    return;
                }
                p.setGameMode(GameMode.ADVENTURE);
                Skywars.getPlugin().getLogger().log(Level.INFO, "teleport");
                p.setLevel(Skywars.getCountdown());
                p.teleport(Skywars.hub);
                if(Bukkit.getOnlinePlayers().size() >= Skywars.getPlayerstostart()){
                    Skywars.startCountdown();
                }
                break;
            case INGAME:
                break;
            case ENDING:
                p.kickPlayer("§cThe game is finished!");
                break;
        }
    }
}