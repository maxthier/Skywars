package net.qubikstudios.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;
    private List<Component> lore;

    public ItemBuilder(Material material, int amount){
        item = new ItemStack(material, amount);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material){
        this(material, 1);
    }

    public ItemBuilder setAmount(int amount){
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name){
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int Level, boolean ignore){
        meta.addEnchant(ench, Level, ignore);
        return this;
    }

    public ItemBuilder hideEnchantments(){
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder addLoreLine(Component line){
        if(meta.hasLore()) {
            lore = meta.lore();
        }else{
            lore = new ArrayList<>();
        }
        lore.add(line);
        meta.lore(lore);
        return this;
    }

    public ItemBuilder setLore(List<Component> lore){
        meta.lore(lore);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }
}
