package dev.nebula.client.module;

import dev.nebula.client.config.NebulaConfigManager;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class ModuleManager {
    private final NebulaConfigManager configs;
    private final Map<String, NebulaModule> modules = new LinkedHashMap<>();

    private ModuleManager(NebulaConfigManager configs) { this.configs = configs; }

    public static ModuleManager createDefault(NebulaConfigManager configs) {
        ModuleManager manager = new ModuleManager(configs);
        manager.registerDefaults();
        return manager;
    }

    public Collection<NebulaModule> all() { return modules.values(); }
    public Optional<NebulaModule> find(String id) { return Optional.ofNullable(modules.get(id)); }
    public void toggle(String id) { find(id).ifPresent(module -> { module.setEnabled(!module.enabled()); save(); }); }

    public void load() {
        ModuleConfig fallback = new ModuleConfig();
        fallback.modules.addAll(modules.values());
        ModuleConfig config = configs.read("modules.json", ModuleConfig.class, fallback);
        for (NebulaModule saved : config.modules) {
            NebulaModule module = modules.get(saved.id());
            if (module != null) {
                module.setEnabled(saved.enabled());
                module.setKeybind(saved.keybind());
                module.settings().clear();
                module.settings().putAll(saved.settings());
            }
        }
        save();
    }

    public void save() {
        ModuleConfig config = new ModuleConfig();
        config.modules.addAll(modules.values());
        configs.write("modules.json", config);
    }

    private void add(NebulaModule module) { modules.put(module.id(), module); }

    private void registerDefaults() {
        add(pvp("combo_display", "Combo Display"));
        add(pvp("reach_display", "Reach Display").setting("precision", 2));
        add(pvp("hit_indicator", "Hit Indicator"));
        add(pvp("armor_status", "Armor Status"));
        add(pvp("potion_effects", "Potion Effects"));
        add(pvp("cps_counter", "CPS Counter"));
        add(new NebulaModule("toggle_sprint", "Toggle Sprint", ModuleCategory.PVP, true, ModuleKeybind.none()));
        add(pvp("totem_indicator", "Totem Indicator"));
        add(pvp("saturation_display", "Saturation Display"));
        add(hud("keystrokes", "Keystrokes"));
        add(hud("coordinates", "Coordinates"));
        add(hud("fps_counter", "FPS Counter"));
        add(hud("ping_display", "Ping Display"));
        add(hud("direction", "Direction (Compass)"));
        add(hud("boss_bar", "Boss Bar"));
        add(hud("inventory_hud", "Inventory HUD"));
        add(hud("item_counter", "Item Counter"));
        add(hud("shulker_tooltips", "Shulker Tooltips"));
        add(new NebulaModule("zoom", "Zoom", ModuleCategory.CONTROL_CAMERA, true, new ModuleKeybind("C", 67)).setting("factor", 4.0));
        add(new NebulaModule("freelook", "Freelook", ModuleCategory.CONTROL_CAMERA, true, new ModuleKeybind("LEFT_ALT", 342)));
        add(new NebulaModule("dynamic_fov", "Dynamic FOV", ModuleCategory.CONTROL_CAMERA, true, ModuleKeybind.none()).setting("intensity", 0.65));
        add(new NebulaModule("snaplook", "Snaplook", ModuleCategory.CONTROL_CAMERA, true, new ModuleKeybind("B", 66)));
        add(visual("custom_crosshair", "Custom Crosshair"));
        add(visual("fullbright", "Fullbright / Gamma").setting("gamma", 15.0));
        add(visual("motion_blur", "Motion Blur").setting("androidSafe", true));
        add(visual("custom_fog", "Custom Fog"));
        add(visual("particles_control", "Particles Control"));
        add(visual("item_physics", "Item Physics"));
        add(visual("block_overlay", "Block Overlay"));
        add(visual("hitbox", "Hitbox"));
        add(qol("auto_text", "Auto Text"));
        add(qol("reconnect", "Reconnect"));
        add(qol("loot_beams", "Loot Beams"));
        add(qol("item_customizer", "Item Customizer"));
        add(qol("color_saturation", "Color Saturation"));
        add(qol("paperdoll", "Paperdoll"));
        add(qol("nick_hider", "Nick Hider"));
    }

    private static NebulaModule pvp(String id, String name) { return new NebulaModule(id, name, ModuleCategory.PVP, true, ModuleKeybind.none()); }
    private static NebulaModule hud(String id, String name) { return new NebulaModule(id, name, ModuleCategory.HUD, true, ModuleKeybind.none()); }
    private static NebulaModule visual(String id, String name) { return new NebulaModule(id, name, ModuleCategory.VISUAL, true, ModuleKeybind.none()); }
    private static NebulaModule qol(String id, String name) { return new NebulaModule(id, name, ModuleCategory.QUALITY_OF_LIFE, true, ModuleKeybind.none()); }
}
