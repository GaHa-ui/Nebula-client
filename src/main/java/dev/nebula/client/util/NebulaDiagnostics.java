package dev.nebula.client.util;

import dev.nebula.client.config.NebulaConfigManager;
import dev.nebula.client.module.ModuleManager;
import dev.nebula.client.ui.NebulaUiTheme;
import dev.nebula.client.waypoint.WaypointManager;

import java.nio.file.Files;
import java.nio.file.Path;

public final class NebulaDiagnostics {
    private NebulaDiagnostics() { }

    public static void main(String[] args) throws Exception {
        Path temp = Files.createTempDirectory("nebula-diagnostics");
        NebulaConfigManager configs = new NebulaConfigManager(temp);
        ModuleManager modules = ModuleManager.createDefault(configs);
        modules.load();
        if (modules.all().size() != 37) throw new IllegalStateException("Expected 37 modules");

        WaypointManager waypoints = new WaypointManager(configs);
        waypoints.load();
        waypoints.add(10, 64, -5, "Diagnostics");
        if (!Files.exists(temp.resolve("waypoints.json"))) throw new IllegalStateException("Waypoint config missing");

        NebulaUiTheme theme = NebulaUiTheme.createDefault();
        if (theme.panoramaFrames().size() != 12) throw new IllegalStateException("Expected 12 panorama frames");
        if (theme.mainMenu().panelWidth() != 580) throw new IllegalStateException("Expected 580px menu panel width");
        if (!"Nebula Client".equals(theme.splash().title())) throw new IllegalStateException("Expected Nebula splash title");
        System.out.println("Nebula diagnostics passed: 37 modules, waypoint persistence, 12 panorama frames, menu mockup tokens");
    }
}
