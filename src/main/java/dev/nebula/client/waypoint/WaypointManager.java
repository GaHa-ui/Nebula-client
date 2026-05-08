package dev.nebula.client.waypoint;

import dev.nebula.client.config.NebulaConfigManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WaypointManager {
    private final NebulaConfigManager configs;
    private final List<Waypoint> waypoints = new ArrayList<>();
    private final List<String> folders = new ArrayList<>();

    public WaypointManager(NebulaConfigManager configs) { this.configs = configs; }

    public List<Waypoint> all() { return Collections.unmodifiableList(waypoints); }
    public List<String> folders() { return Collections.unmodifiableList(folders); }

    public Waypoint add(double x, double y, double z, String name) {
        Waypoint waypoint = Waypoint.of(x, y, z, name);
        waypoints.add(waypoint);
        save();
        return waypoint;
    }

    public void load() {
        WaypointConfig config = configs.read("waypoints.json", WaypointConfig.class, new WaypointConfig());
        waypoints.clear();
        waypoints.addAll(config.waypoints);
        folders.clear();
        folders.addAll(config.folders.isEmpty() ? List.of("Default") : config.folders);
        save();
    }

    public void save() {
        WaypointConfig config = new WaypointConfig();
        config.folders = new ArrayList<>(folders.isEmpty() ? List.of("Default") : folders);
        config.waypoints = new ArrayList<>(waypoints);
        configs.write("waypoints.json", config);
    }

    public String exportJson() {
        save();
        return configs.configDirectory().resolve("waypoints.json").toString();
    }
}
