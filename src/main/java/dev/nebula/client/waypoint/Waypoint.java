package dev.nebula.client.waypoint;

import java.util.UUID;

public final class Waypoint {
    public String id = UUID.randomUUID().toString();
    public String name = "Waypoint";
    public String folder = "Default";
    public double x;
    public double y;
    public double z;
    public int color = 0xAA66FF;
    public String icon = "star";
    public WaypointType type = WaypointType.WORLD;
    public boolean visible = true;

    public static Waypoint of(double x, double y, double z, String name) {
        Waypoint waypoint = new Waypoint();
        waypoint.x = x;
        waypoint.y = y;
        waypoint.z = z;
        if (name != null && !name.isBlank()) waypoint.name = name;
        return waypoint;
    }
}
