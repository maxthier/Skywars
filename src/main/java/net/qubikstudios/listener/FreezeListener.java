package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import net.qubikstudios.utils.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void handlePlayerMovement(PlayerMoveEvent ev){
        if(Skywars.getState() == State.FINAL) {
            ev.setCancelled(true);
        }
    }
}
