package net.qubikstudios.utils;

import net.qubikstudios.Skywars;
import net.qubikstudios.loottable.LootTable;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    private final String name;
    private World world;
    private Location loc;
    private List<Location> locations = new ArrayList<>();
    //private static LootTable low = new LootTable.LootTableBuilder()
    //        .add(new ItemStack(Material.BREAD), 3, 1, 1)
    //        .add(new ItemStack(Material.DIAMOND_SWORD), 1, 1, 1)
    //        .build();

    public Map(String name){
        this.name = name;
        Skywars.getPlugin().getServer().createWorld(new WorldCreator(name));
        this.world = Bukkit.getWorld(name);
        this.loc = new Location(world, 0, 90, 0);
        if(name.equalsIgnoreCase("star")) {
            world.setTime(16000l);
        }else
            world.setTime(6000l);
        getSpawns();
        getChests();
    }

    private void getSpawns(){
        for (ArmorStand as : world.getEntitiesByClass(ArmorStand.class)){
            locations.add(as.getLocation());
            as.remove();
        }
    }

    private void getChests(){
        for (Chunk c : world.getLoadedChunks()){
            for (BlockState b : c.getTileEntities()){
                if(b instanceof Chest){
                    Chest chest = (Chest) b;
                    Random rndm = new Random();
                    for (int i = 0; i < ThreadLocalRandom.current().nextInt(7, 19); i++) {
                        while (true){
                            int slot = rndm.nextInt(27);
                            if(chest.getBlockInventory().getItem(slot) == null){
                                //chest.getBlockInventory().setItem(slot, low.getRandom());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public Location getPlayerSpawn(){
        Random rnd = new Random();
        Location result = locations.get(rnd.nextInt(locations.size()));
        locations.remove(result);
        return result;
    }

    public String getName() {
        return name;
    }

    public Location getSpawn(){
        return this.loc;
    }
}