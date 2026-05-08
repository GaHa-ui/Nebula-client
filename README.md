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

The mod jar is produced at `build/libs/NebulaClient-1.0.0+1.21.11.jar`.

## Notes

The cape/cosmetic system is intentionally deferred for future versions. PNG placeholders are not committed; add the provided star-N logo at `assets/nebula/textures/gui/logo_placeholder.png` and add 12 panorama frames at `assets/nebula/textures/gui/panorama/nebula_0.png` through `nebula_11.png` when available.
