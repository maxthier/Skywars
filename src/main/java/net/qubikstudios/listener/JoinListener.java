package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import net.qubikstudios.kits.logic.KitManager;
import net.qubikstudios.scoreboards.WaitingScoreboard;
import net.qubikstudios.utils.State;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    @EventHandler
    public void HandlePlayerJoin(PlayerJoinEvent ev){
        Player p = ev.getPlayer();
        p.getInventory().clear();
        switch (Skywars.state){
            case STARTING:
                //Kick
                if(Skywars.getMaxPlayers() <= Bukkit.getOnlinePlayers().size()){
                    p.kickPlayer("§cTest");
                    return;
                }
                p.setGameMode(GameMode.ADVENTURE);
                p.teleport(Skywars.hub);
                new WaitingScoreboard(p);
                p.setLevel(Skywars.getCountdown());
                p.getInventory().setItem(0, new ItemStack(Material.CHEST));
                KitManager.selectKit(p, "Default");
                if(Bukkit.getOnlinePlayers().size() >= Skywars.getPlayerstostart() && !Skywars.runsCountdown()){
                    Skywars.startCountdown();
                }
                if(Bukkit.getOnlinePlayers().size() == Skywars.getMaxPlayers()){
                    Skywars.setCountdown(10);
                }
                WaitingScoreboard.updatePlayers();
                break;
            case INGAME:
                break;
            case ENDING:
                p.kickPlayer("§cThe game is finished!");
                break;
        }
    }

    @EventHandler
    public void HandlePlayerLeave(PlayerQuitEvent ev){
        ev.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        if(Skywars.state == State.STARTING){
            WaitingScoreboard.updatePlayers();
        }
    }
}