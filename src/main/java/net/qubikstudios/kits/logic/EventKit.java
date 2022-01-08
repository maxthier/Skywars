package net.qubikstudios.kits.logic;

import org.bukkit.event.Listener;

public interface EventKit extends Kit, Listener {

    public int getCooldown();

    public void setCooldown();
}
