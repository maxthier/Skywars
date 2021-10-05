package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void HandlePlayerJoin(PlayerJoinEvent ev){
        Player p = ev.getPlayer();
        switch (Skywars.state){
            case STARTING:
                if(Skywars.maxPlayers <= Bukkit.getOnlinePlayers().size()){
                    p.kickPlayer("§cTest");
                }
                p.teleport(Skywars.hub);
                break;
            case INGAME:
                break;
            case ENDING:
                break;
        }
    }
}