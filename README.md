# Nebula Client

Nebula Client is a Fabric client mod scaffold for Minecraft 1.21.11. It focuses on a modular PvP/HUD architecture, Android-safe rendering decisions for Krypton Wrapper, and JSON-backed user configuration.

## Implemented foundation

- Fabric entrypoints for common and client initialization.
- Gradle build that targets Java 21 and packages `fabric.mod.json` resources.
- JSON configs under `config/Nebula/`:
  - `modules.json` for all toggleable modules and settings.
  - `waypoints.json` for grouped screen/world waypoints.
- All 37 requested modules registered as toggleable module definitions.
- Waypoint manager with `.gps`/`/wp` command bridge hooks prepared for Fabric command adapters.
- UI theme manifest for splash/main menu/HUD work, including reserved logo/panorama texture paths, Rajdhani/Orbitron font descriptors, Minecraft-standard sound policy, and Telegram/GitHub/site links.
- Krypton Wrapper detection helper that keeps rendering code free from desktop-only dependencies.

## Build

```bash
gradle clean build
```

The mod jar is produced at `build/libs/NebulaClient-1.0.0+1.21.11.jar` locally and uploaded by GitHub Actions as a downloadable artifact.

## Notes

The cape/cosmetic system is intentionally deferred for future versions. PNG placeholders are not committed; add the provided star-N logo at `assets/nebula/textures/gui/logo_placeholder.png` and add 12 panorama frames at `assets/nebula/textures/gui/panorama/nebula_0.png` through `nebula_11.png` when available.



## Interface mockup

The provided blue Nebula main-menu/loading mockup is captured in code as `NebulaMenuSpec` and `NebulaSplashSpec`. A browser preview for designers is available at `docs/ui/main-menu-preview.html`; it mirrors the animated blue background, center glass panel, clock, play-mode transition, branding, and exit notification from the HTML concept.

## Launcher diagnostics

The mod JAR is still a Fabric mod, not a standalone launcher. For quick cloud testing, the JAR also has a small command-line diagnostics entrypoint:

```bash
java -jar build/libs/NebulaClient-1.0.0+1.21.11.jar
```

It checks Java 21+, creates Nebula config files, verifies the 37 module definitions, confirms 12 panorama paths, and prints whether the artifact is ready to copy into the Minecraft `mods` folder.

## Testing the GitHub Actions JAR

The repository does not commit generated JAR binaries. GitHub builds the mod in **Actions → Build Nebula Client** and uploads a downloadable artifact named `NebulaClient-1.0.0+1.21.11`.

To test it, open the latest successful workflow run, download the artifact, unzip it, and copy `NebulaClient-1.0.0+1.21.11.jar` into your Minecraft `mods` folder. A SHA-256 checksum is included in the artifact as `NebulaClient-1.0.0+1.21.11.jar.sha256`.
