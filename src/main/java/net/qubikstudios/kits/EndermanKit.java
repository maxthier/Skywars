package net.qubikstudios.kits;

import net.qubikstudios.kits.logic.EventKit;
import net.qubikstudios.kits.logic.KitManager;
import net.qubikstudios.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EndermanKit implements EventKit {

    private final ItemStack icon;
    private final Inventory inv;
    private final ItemStack[] armor = new ItemStack[4];
    private final String name = "Enderman";

    public EndermanKit(){
        icon = new ItemBuilder(Material.ENDER_PEARL).setName("§5Enderman").build();
        inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.addItem(new ItemStack(Material.ENDER_PEARL));
        armor[1] = new ItemStack(Material.LEATHER_CHESTPLATE);
    }

    @EventHandler
    public void onEvent(PlayerDeathEvent ev) {
        if(ev.getPlayer().getKiller() == null){
            return;
        }
        Player killer = ev.getPlayer().getKiller();
        if (KitManager.getSelectedKit(killer).equals(this)){
            killer.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public void setCooldown() {

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
}
