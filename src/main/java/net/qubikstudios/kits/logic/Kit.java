package net.qubikstudios.kits.logic;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface Kit {
    public ItemStack getIcon();

    public Inventory getInv();

    public ItemStack[] getArmorContent();

    public String getName();
}
