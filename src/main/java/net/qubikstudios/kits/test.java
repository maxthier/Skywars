package net.qubikstudios.kits;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class test extends Kit{

    @EventHandler
    public void test(AsyncPlayerChatEvent ev){
        Bukkit.broadcastMessage("Test");
    }

}
