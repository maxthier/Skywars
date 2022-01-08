package net.qubikstudios.kits;

import net.qubikstudios.kits.logic.EventKit;
import net.qubikstudios.kits.logic.Kit;
import net.qubikstudios.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashSet;

public class AngelKit implements EventKit {

    private final ItemStack icon;
    private final Inventory inv;
    private final ItemStack[] armor = new ItemStack[4];
    private final String name = "Angel";
    private Collection<Player> used;

    public AngelKit(){
        icon = new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§d" + name).build();
        inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
        used = new HashSet<>();
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent ev){
        if(ev.getPlayer().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID){

        }
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public Inventory getInv() {
        return inv;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return armor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public void setCooldown() {

    }
}
