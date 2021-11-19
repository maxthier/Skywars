package net.qubikstudios.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LootGenerator {

    private int blocks = 0;
    private int armor = 0;
    private int heal = 0;
    private int weapons = 0;
    private int utils = 0;
    private int ores = 0;
    private Random rndm = new Random();

    private loot level;
    private Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "Chest");

    public LootGenerator(loot level){
        this.level = level;
    }

    public Inventory generate(){
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(7, 19); i++) {
            
        }
        return inv;
    }

    enum loot{
        LOW,
        MIDDLE,
        HIGH
    }
}
