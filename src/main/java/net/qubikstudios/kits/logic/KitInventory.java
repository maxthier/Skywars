package net.qubikstudios.kits.logic;

import net.kyori.adventure.text.Component;
import net.qubikstudios.kits.logic.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.Set;

public class KitInventory {

    public static Set<Player> players = new HashSet<>();
    private Inventory inv;
    private final Player p;

    public KitInventory(Player player){
        p = player;
        inv = Bukkit.createInventory(null, 5*9, Component.text("Select your Kit!"));
        KitManager.getKits().forEach(Kit -> inv.addItem(Kit.getIcon()));
    }

    public void open(){
        p.openInventory(inv);
        players.add(p);
    }

}
