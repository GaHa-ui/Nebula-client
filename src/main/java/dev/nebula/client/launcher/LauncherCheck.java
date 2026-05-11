package dev.nebula.client.launcher;

public record LauncherCheck(String name, boolean passed, String details) {
    public static LauncherCheck pass(String name, String details) {
        return new LauncherCheck(name, true, details);
    }

    public static LauncherCheck fail(String name, String details) {
        return new LauncherCheck(name, false, details);
    }

    public String format() {
        return (passed ? "[OK] " : "[FAIL] ") + name + " - " + details;
    }
}
