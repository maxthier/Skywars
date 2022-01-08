package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import net.qubikstudios.kits.logic.KitInventory;
import net.qubikstudios.kits.logic.KitManager;
import net.qubikstudios.utils.State;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent ev){
        if(Skywars.state == State.STARTING){
            ev.setCancelled(true);
            if(ev.getWhoClicked() instanceof Player){
                Player p = (Player) ev.getWhoClicked();
                if(KitInventory.players.contains(p)){
                    Bukkit.broadcastMessage(ev.getCurrentItem().getDisplayName().substring(2));
                    KitManager.selectKit(p, ev.getCurrentItem().getDisplayName().substring(2));
                }
            }
        }
    }

    @EventHandler
    public void handleItemDrop(PlayerDropItemEvent ev){
        if(Skywars.state == State.STARTING){
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void handleInventoryClose(InventoryCloseEvent ev){
        if(KitInventory.players.contains(ev.getPlayer())){
            KitInventory.players.remove(ev.getPlayer());
        }
    }
}
