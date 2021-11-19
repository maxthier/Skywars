package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void HandlePlayerLeave(PlayerQuitEvent ev){
        Player p = ev.getPlayer();
        switch (Skywars.state){
            case STARTING:
                if(Bukkit.getOnlinePlayers().size() < Skywars.getPlayerstostart()){
                    Skywars.stopCoundtown();
                }
                break;
            case INGAME:
                break;
            case ENDING:
                break;
        }
    }

}
