package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import net.qubikstudios.utils.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void handlePlayerDamage(EntityDamageEvent ev) {
        if (Skywars.state == State.STARTING) {
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void HandleAttack(EntityDamageByEntityEvent ev) {
        if (Skywars.state == State.STARTING) {
            ev.setCancelled(true);
        }
    }
}
