package dev.nebula.client.util;

public enum RenderBackend {
    STANDARD_OPENGL,
    KRYPTON_WRAPPER_ANDROID;

    public static RenderBackend detect() {
        String runtime = (System.getProperty("java.runtime.name", "") + " " + System.getProperty("os.name", "")).toLowerCase();
        if (runtime.contains("android") || runtime.contains("krypton")) return KRYPTON_WRAPPER_ANDROID;
        return STANDARD_OPENGL;
    }
}
