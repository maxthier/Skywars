package net.qubikstudios.listener;

import net.qubikstudios.Skywars;
import net.qubikstudios.kits.KitInventory;
import net.qubikstudios.utils.State;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void handleInteraction(PlayerInteractEvent ev){
        if(Skywars.state == State.STARTING){
            if(ev.getAction() == Action.RIGHT_CLICK_AIR || ev.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(ev.getItem() != null){
                    if(ev.getItem().getType() == Material.CHEST){
                        new KitInventory(ev.getPlayer()).open();
                    }
                }
            }
        }
    }

}
