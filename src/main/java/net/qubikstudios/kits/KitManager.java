package net.qubikstudios.kits;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class KitManager {
    private static Set<Kit> kits = new HashSet<>();
    private static HashMap<String, Kit> names = new HashMap<>();
    private static HashMap<Player, Kit> selected = new HashMap<>();

    public static void registerKit(Kit kit){
        names.put(kit.getName(), kit);
    }

    public static void selectKit(Player p, String kit){
        if(selected.containsKey(p)){
            selected.remove(p);
        }
        selected.put(p, names.get(kit));
    }

    public static Collection<Kit> getKits() {
        return names.values();
    }
}
