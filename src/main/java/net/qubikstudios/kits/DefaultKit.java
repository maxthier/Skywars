package net.qubikstudios.kits;

import net.qubikstudios.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DefaultKit implements Kit {

    private final ItemStack icon;
    private final Inventory inv;
    private final ItemStack[] armor = new ItemStack[4];
    private final String name = "Default";

    public DefaultKit(){
        icon = new ItemBuilder(Material.STONE_SWORD).setName("§6" + name).build();
        inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.addItem(new ItemStack(Material.IRON_SWORD));
        armor[1] = new ItemStack(Material.IRON_CHESTPLATE);
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
