package dev.nebula.client.module;

public record ModuleKeybind(String displayName, int glfwKeyCode) {
    public static ModuleKeybind none() { return new ModuleKeybind("NONE", -1); }
}
