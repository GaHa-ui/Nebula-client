package dev.nebula.client.config;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public final class NebulaConfigManager {
    private final Path configDirectory;

    public NebulaConfigManager() { this(Path.of("config", "Nebula")); }
    public NebulaConfigManager(Path configDirectory) { this.configDirectory = configDirectory; }
    public Path configDirectory() { return configDirectory; }

    public void ensureConfigDirectory() {
        try { Files.createDirectories(configDirectory); }
        catch (IOException e) { throw new IllegalStateException("Cannot create Nebula config directory", e); }
    }

    public <T> T read(String fileName, Class<T> type, T fallback) {
        ensureConfigDirectory();
        Path file = configDirectory.resolve(fileName);
        if (Files.notExists(file)) write(fileName, fallback);
        return fallback;
    }

    public void write(String fileName, Object value) {
        ensureConfigDirectory();
        Path file = configDirectory.resolve(fileName);
        try (Writer writer = Files.newBufferedWriter(file)) { writer.write(toJson(value, 0)); writer.write('\n'); }
        catch (IOException e) { throw new IllegalStateException("Cannot write Nebula config: " + file, e); }
    }

    private static String toJson(Object value, int indent) {
        if (value == null) return "null";
        if (value instanceof Number || value instanceof Boolean) return value.toString();
        if (value instanceof Enum<?> e) return quote(e.name());
        if (value instanceof String s) return quote(s);
        if (value instanceof Collection<?> c) {
            if (c.isEmpty()) return "[]";
            StringBuilder out = new StringBuilder("[\n");
            int i = 0;
            for (Object item : c) {
                out.append(spaces(indent + 2)).append(toJson(item, indent + 2));
                out.append(++i == c.size() ? "\n" : ",\n");
            }
            return out.append(spaces(indent)).append(']').toString();
        }
        if (value instanceof Map<?, ?> m) {
            if (m.isEmpty()) return "{}";
            StringBuilder out = new StringBuilder("{\n");
            int i = 0;
            for (Map.Entry<?, ?> entry : m.entrySet()) {
                out.append(spaces(indent + 2)).append(quote(String.valueOf(entry.getKey()))).append(": ").append(toJson(entry.getValue(), indent + 2));
                out.append(++i == m.size() ? "\n" : ",\n");
            }
            return out.append(spaces(indent)).append('}').toString();
        }
        Field[] fields = value.getClass().getFields();
        StringBuilder out = new StringBuilder("{\n");
        for (int i = 0; i < fields.length; i++) {
            try {
                out.append(spaces(indent + 2)).append(quote(fields[i].getName())).append(": ").append(toJson(fields[i].get(value), indent + 2));
                out.append(i + 1 == fields.length ? "\n" : ",\n");
            } catch (IllegalAccessException e) { throw new IllegalStateException(e); }
        }
        return out.append(spaces(indent)).append('}').toString();
    }

    private static String spaces(int count) { return " ".repeat(count); }
    private static String quote(String value) { return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"'; }
}
