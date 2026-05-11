package dev.nebula.client.launcher;

import dev.nebula.client.config.NebulaConfigManager;
import dev.nebula.client.module.ModuleManager;
import dev.nebula.client.ui.NebulaUiTheme;
import dev.nebula.client.util.RenderBackend;
import dev.nebula.client.waypoint.WaypointManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class LauncherReport {
    private final List<LauncherCheck> checks = new ArrayList<>();

    public static LauncherReport collect(Path configDirectory) {
        LauncherReport report = new LauncherReport();
        NebulaConfigManager configs = new NebulaConfigManager(configDirectory);
        ModuleManager modules = ModuleManager.createDefault(configs);
        WaypointManager waypoints = new WaypointManager(configs);
        NebulaUiTheme theme = NebulaUiTheme.createDefault();

        configs.ensureConfigDirectory();
        modules.load();
        waypoints.load();

        report.checks.add(javaVersionCheck());
        report.checks.add(LauncherCheck.pass("Render backend", RenderBackend.detect().name()));
        report.checks.add(modules.all().size() == 37
                ? LauncherCheck.pass("Modules", "37 default modules registered")
                : LauncherCheck.fail("Modules", "Expected 37 modules, got " + modules.all().size()));
        report.checks.add(theme.panoramaFrames().size() == 12
                ? LauncherCheck.pass("Panorama", "12 frame paths configured")
                : LauncherCheck.fail("Panorama", "Expected 12 frame paths, got " + theme.panoramaFrames().size()));
        report.checks.add(theme.mainMenu().panelWidth() == 580
                ? LauncherCheck.pass("Main menu mockup", "580px glass panel and blue nebula palette configured")
                : LauncherCheck.fail("Main menu mockup", "Unexpected panel width " + theme.mainMenu().panelWidth()));
        report.checks.add(Files.exists(configDirectory.resolve("modules.json"))
                ? LauncherCheck.pass("Module config", configDirectory.resolve("modules.json").toString())
                : LauncherCheck.fail("Module config", "modules.json was not created"));
        report.checks.add(Files.exists(configDirectory.resolve("waypoints.json"))
                ? LauncherCheck.pass("Waypoint config", configDirectory.resolve("waypoints.json").toString())
                : LauncherCheck.fail("Waypoint config", "waypoints.json was not created"));
        return report;
    }

    public boolean passed() {
        return checks.stream().allMatch(LauncherCheck::passed);
    }

    public List<LauncherCheck> checks() {
        return List.copyOf(checks);
    }

    public String render() {
        StringBuilder output = new StringBuilder();
        output.append("Nebula Client launcher diagnostics\n");
        output.append("Minecraft target: 1.21.11\n");
        output.append("Fabric mod id: nebula\n\n");
        for (LauncherCheck check : checks) {
            output.append(check.format()).append('\n');
        }
        output.append('\n');
        output.append(passed()
                ? "Result: ready to copy into the Minecraft mods folder.\n"
                : "Result: fix failed checks before testing in Minecraft.\n");
        return output.toString();
    }

    private static LauncherCheck javaVersionCheck() {
        int feature = Runtime.version().feature();
        return feature >= 21
                ? LauncherCheck.pass("Java", "Detected Java " + feature)
                : LauncherCheck.fail("Java", "Java 21+ required, detected " + feature);
    }
}
