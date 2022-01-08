package net.qubikstudios.kits;

import net.qubikstudios.kits.logic.Kit;
import net.qubikstudios.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StarterKit implements Kit {

    private final ItemStack icon;
    private final Inventory inv;
    private final ItemStack[] armor = new ItemStack[4];
    private final String name = "Starter";

    public StarterKit(){
        icon = new ItemBuilder(Material.WOODEN_SWORD).setName("§6" + name).build();
        inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.addItem(new ItemStack(Material.WOODEN_SWORD));
        inv.addItem(new ItemStack(Material.SPRUCE_PLANKS, 16));
        inv.addItem(new ItemStack(Material.COOKED_BEEF, 8));
        armor[0] = new ItemStack(Material.GOLDEN_HELMET);
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
