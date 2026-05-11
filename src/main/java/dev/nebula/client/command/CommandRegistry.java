package dev.nebula.client.command;

import dev.nebula.client.waypoint.WaypointManager;

/**
 * Lightweight command bridge. Fabric API command callbacks are intentionally attached by mixin/adapter code in later milestones,
 * keeping this core compatible with Krypton Wrapper and clients without optional GUI libraries.
 */
public final class CommandRegistry {
    private final WaypointManager waypointManager;

    private CommandRegistry(WaypointManager waypointManager) { this.waypointManager = waypointManager; }
    public static CommandRegistry createDefault(WaypointManager waypointManager) { return new CommandRegistry(waypointManager); }
    public void register() { /* reserved for .gps and /wp adapters */ }

    public String handleGps(double x, double y, double z, String name) {
        return "Created waypoint " + waypointManager.add(x, y, z, name).name;
    }
}
