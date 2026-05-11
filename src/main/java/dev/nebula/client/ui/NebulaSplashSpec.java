package dev.nebula.client.ui;

public record NebulaSplashSpec(
        UiColor gradientStart,
        UiColor gradientMiddle,
        UiColor gradientEnd,
        UiColor particleColor,
        UiColor progressTrack,
        UiColor progressFill,
        int progressWidth,
        int progressHeight,
        int logoGlowRadius,
        String title) {

    public static NebulaSplashSpec defaultSpec() {
        return new NebulaSplashSpec(
                UiColor.rgb(0, 0, 79),
                UiColor.rgb(2, 0, 107),
                UiColor.rgb(0, 0, 61),
                UiColor.rgba(0, 68, 255, 31),
                UiColor.rgba(255, 255, 255, 25),
                UiColor.rgba(0, 102, 255, 220),
                420,
                12,
                22,
                "Nebula Client");
    }
}
