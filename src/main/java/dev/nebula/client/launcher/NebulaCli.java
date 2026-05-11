package dev.nebula.client.launcher;

import java.nio.file.Path;

public final class NebulaCli {
    private NebulaCli() { }

    public static void main(String[] args) {
        Path configDirectory = Path.of("config", "Nebula");
        if (args.length >= 2 && "--config".equals(args[0])) {
            configDirectory = Path.of(args[1]);
        }

        LauncherReport report = LauncherReport.collect(configDirectory);
        System.out.print(report.render());
        if (!report.passed()) {
            System.exit(1);
        }
    }
}
