package dev.nebula.client;

import dev.nebula.client.command.CommandRegistry;
import dev.nebula.client.config.NebulaConfigManager;
import dev.nebula.client.module.ModuleManager;
import dev.nebula.client.ui.NebulaUiTheme;
import dev.nebula.client.waypoint.WaypointManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric entrypoint for Nebula Client.
 */
public final class NebulaClient implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "nebula";
    public static final String VERSION = "1.0.0";

    private static final NebulaConfigManager CONFIGS = new NebulaConfigManager();
    private static final ModuleManager MODULES = ModuleManager.createDefault(CONFIGS);
    private static final WaypointManager WAYPOINTS = new WaypointManager(CONFIGS);
    private static final NebulaUiTheme THEME = NebulaUiTheme.createDefault();

    @Override
    public void onInitialize() {
        CONFIGS.ensureConfigDirectory();
        MODULES.load();
        WAYPOINTS.load();
    }

    @Override
    public void onInitializeClient() {
        CommandRegistry.createDefault(WAYPOINTS).register();
        THEME.prepareAssets();
    }

    public static ModuleManager modules() { return MODULES; }
    public static WaypointManager waypoints() { return WAYPOINTS; }
    public static NebulaUiTheme theme() { return THEME; }
}
