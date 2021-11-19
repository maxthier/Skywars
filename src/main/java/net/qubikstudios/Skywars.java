package net.qubikstudios;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import net.qubikstudios.commands.ForcemapCommand;
import net.qubikstudios.commands.StartCommand;
import net.qubikstudios.kits.Kit;
import net.qubikstudios.listener.JoinListener;
import net.qubikstudios.utils.Map;
import net.qubikstudios.utils.State;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public final class Skywars extends JavaPlugin {

    private static Skywars plugin;
    public static State state;
    public static Location hub;
    private static int maxPlayers;
    private static int Playerstostart;
    private static List<Map> maps = new ArrayList<>();
    private static Map map;
    private FileConfiguration config;
    private static int Countdown = 0;
    private static int CountdownID;


    @Override
    public void onEnable() {
        plugin = this;
        state = State.STARTING;
        config = getConfig();
        saveConfig();
        initConfig();
        maxPlayers = config.getInt("Player.Players");
        Playerstostart = config.getInt("Player.PlayersToStart");
        hub = new Location(Bukkit.getWorld(config.getString("Hub.World")), config.getDouble("Hub.X"), config.getDouble("Hub.Y"), config.getDouble("Hub.Z"), config.getLong("Hub.Yaw"), 0);
        config.getConfigurationSection("Worlds").getKeys(false).forEach(s -> maps.add(new Map(s)));
        Random rnd = new Random();
        map = maps.get(rnd.nextInt(maps.size()));

        registerCommands();
        registerListener();
        Debug();
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                ServerObject server = TimoCloudAPI.getBukkitAPI().getThisServer();
                server.setExtra(map.getName());
                server.setState("LOBBY");
            }
        }, 40);
        getLogger().log(Level.WARNING, "Loading Finished!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands(){
        getCommand("start").setExecutor(new StartCommand());
        getCommand("forcemap").setExecutor(new ForcemapCommand());
        getCommand("forcemap").setTabCompleter(new ForcemapCommand());
    }

    private void registerListener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new Kit(), this);
    }

    public void initConfig(){
        config.addDefault("Database.Host", "localhost");
        config.addDefault("Database.Port", 3306);
        config.addDefault("Database.User", "mariadb");
        config.addDefault("Database.Password", "password");
        config.addDefault("Player.Players", 8);
        config.addDefault("Player.PlayersToStart", 4);
        config.addDefault("Hub.World", "hub");
        config.addDefault("Hub.X", 0);
        config.addDefault("Hub.Y", 0);
        config.addDefault("Hub.Z", 0);
        config.addDefault("Hub.Yaw", 0);
        config.addDefault("Worlds.Valley", "Valley");
        config.options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
    }

    public void Debug(){
        getLogger().log(Level.INFO, config.getString("Hub.World"));
        getLogger().log(Level.INFO, String.valueOf(config.getDouble("Hub.X")));
        getLogger().log(Level.INFO, String.valueOf(config.getDouble("Hub.Y")));
        getLogger().log(Level.INFO, String.valueOf(config.getDouble("Hub.Z")));
        getLogger().log(Level.INFO, String.valueOf(config.getDouble("Hub.Yaw")));
        maps.forEach(map1 -> getLogger().log(Level.INFO, map1.getName()));
    }

    public static void startCountdown(){
        Countdown = 10;
        CountdownID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skywars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(Countdown));
                if(Countdown <= 10){
                    Bukkit.getOnlinePlayers().forEach(player -> player.setExp(Countdown/10f));
                }
                if(Countdown <= 0) {
                    startRound();
                    Bukkit.getScheduler().cancelTask(CountdownID);
                }
                Countdown--;
            }
        }, 0, 20);
    }

    public static void stopCoundtown(){
        if (runsCountdown())
            Bukkit.getScheduler().cancelTask(CountdownID);
    }

    public static void startRound(){
        Bukkit.broadcastMessage("Runde startet!");
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(map.getPlayerSpawn()));
    }

    public static boolean runsCountdown(){
        return Countdown != 0;
    }

    public static void setCountdown(int countdown) {
        Countdown = countdown;
    }

    public static int getCountdown() {
        return Countdown;
    }

    public static int getMaxPlayers() {
        return maxPlayers;
    }

    public static int getPlayerstostart() {
        return Playerstostart;
    }

    public ClassLoader ClassLoader() {
        return getClassLoader();
    }

    public static void setMap(Map map) {
        Skywars.map = map;
        TimoCloudAPI.getBukkitAPI().getThisServer().setExtra(map.getName());
    }

    public static Map getMap() {
        return map;
    }

    public static List<Map> getMaps() {
        return maps;
    }

    public static State getState() {
        return state;
    }

    public static Skywars getPlugin() {
        return plugin;
    }
}