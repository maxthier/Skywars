package net.qubikstudios;

import net.qubikstudios.utils.State;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skywars extends JavaPlugin {

    private static Skywars plugin;
    public static State state;
    public static Location hub;
    public static int maxPlayers;
    private FileConfiguration config;


    @Override
    public void onEnable() {
        plugin = this;
        state = State.STARTING;
        config = getConfig();
        initConfig();
        maxPlayers = config.getInt("Players");
        hub = new Location(Bukkit.getWorld(config.getString("Hub.World")), config.getInt("Hub.X"), config.getInt("Hub.Y"), config.getInt("Hub.Z"), config.getInt("Hub.Yaw"), 0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initConfig(){
        config.addDefault("Database.Host", "localhost");
        config.addDefault("Database.Port", 3306);
        config.addDefault("Database.User", "mariadb");
        config.addDefault("Database.Password", "password");
        config.addDefault("Players", 8);
        config.addDefault("Hub.World", "hub");
        config.addDefault("Hub.X", 0);
        config.addDefault("Hub.Y", 0);
        config.addDefault("Hub.Z", 0);
        config.addDefault("Hub.Yaw", 0);
    }

    public ClassLoader ClassLoader() {
        return getClassLoader();
    }

    public static Skywars getPlugin() {
        return plugin;
    }
}