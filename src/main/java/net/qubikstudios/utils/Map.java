package net.qubikstudios.utils;

import net.qubikstudios.Skywars;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    private final String name;
    private World world;
    private Location loc;
    private List<Location> locations = new ArrayList<>();

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
        //getChests();
    }

    private void getSpawns(){
        FileConfiguration config = Skywars.getPlugin().getConfig();
        for (int i = 1; i < 9; i++) {
            final String path = "Worlds." + name + "." + i + ".";
            locations.add(new Location(world, config.getDouble(path + "X"), config.getDouble(path + "Y"), config.getDouble(path + "Z"), config.getInt(path + "Yaw"), 0f));
        }
    }

    private void getChests(){
        for (Chunk c : world.getLoadedChunks()){
            for (BlockState b : c.getTileEntities()){
                if(b instanceof Chest){
                    Chest chest = (Chest) b;
                    b.setType(Material.EMERALD_BLOCK);
                }
            }
        }
    }

    public Location getPlayerSpawn(){
        Random rnd = new Random();
        if (locations.isEmpty()){
            return loc;
        }
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