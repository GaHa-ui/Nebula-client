package dev.nebula.client.ui;

import java.util.List;

public record NebulaMenuSpec(
        UiColor baseBackground,
        UiColor deepBackground,
        UiColor panelBackground,
        UiColor panelBorder,
        UiColor hoverGlow,
        int panelWidth,
        int panelHeight,
        int panelRadius,
        int logoButtonSize,
        int clockTopOffset,
        List<String> mainActions,
        List<String> playModeActions) {

    public static NebulaMenuSpec defaultSpec() {
        return new NebulaMenuSpec(
                UiColor.rgb(2, 0, 107),
                UiColor.rgb(0, 0, 63),
                UiColor.rgba(220, 220, 220, 38),
                UiColor.rgba(255, 255, 255, 38),
                UiColor.rgba(0, 102, 255, 204),
                580,
                160,
                20,
                84,
                80,
                List.of("Nebula Logo", "Play", "Menu", "Options"),
                List.of("Singleplayer", "Multiplayer"));
    }
}
