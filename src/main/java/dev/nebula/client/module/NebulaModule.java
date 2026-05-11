package dev.nebula.client.module;

import java.util.LinkedHashMap;
import java.util.Map;

public final class NebulaModule {
    private final String id;
    private final String name;
    private final ModuleCategory category;
    private boolean enabled;
    private ModuleKeybind keybind;
    private final Map<String, Object> settings = new LinkedHashMap<>();

    public NebulaModule(String id, String name, ModuleCategory category, boolean enabled, ModuleKeybind keybind) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.enabled = enabled;
        this.keybind = keybind;
    }

    public String id() { return id; }
    public String name() { return name; }
    public ModuleCategory category() { return category; }
    public boolean enabled() { return enabled; }
    public ModuleKeybind keybind() { return keybind; }
    public Map<String, Object> settings() { return settings; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setKeybind(ModuleKeybind keybind) { this.keybind = keybind; }

    public NebulaModule setting(String key, Object value) {
        settings.put(key, value);
        return this;
    }
}
