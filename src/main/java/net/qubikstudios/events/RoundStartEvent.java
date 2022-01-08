package net.qubikstudios.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class RoundStartEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    public RoundStartEvent(){

    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}