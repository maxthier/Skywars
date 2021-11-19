package net.qubikstudios.commands;

import net.qubikstudios.Skywars;
import net.qubikstudios.utils.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ForcemapCommand implements CommandExecutor, TabCompleter{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("skywars.forcemap")) {
                if (args.length == 1) {
                    for (Map map : Skywars.getMaps()){
                        if(map.getName().equalsIgnoreCase(args[0])){
                            if(Skywars.getMap().getName().equalsIgnoreCase(args[0])){
                                p.sendMessage("§cDie Map ist schon ausgewählt!");
                                return false;
                            }
                            Skywars.setMap(map);
                            p.sendMessage("Du hast die Map gesetzt!");
                            return true;
                        }
                    }
                    p.sendMessage("§cKeine Map mit diesem namen gefunden!");
                } else {
                    sendHelp(p);
                }
            } else {

            }
        }
        return false;
    }

    private void sendHelp(Player p) {

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("skywars.forcemap")) {
                if (args.length == 1) {
                    Skywars.getMaps().forEach(map -> result.add(map.getName()));
                }
            } else
                return result;
        }
        return result;
    }
}
