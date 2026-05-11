package dev.nebula.client.ui;

public record UiColor(int red, int green, int blue, int alpha) {
    public UiColor {
        red = clamp(red);
        green = clamp(green);
        blue = clamp(blue);
        alpha = clamp(alpha);
    }

    public static UiColor rgb(int red, int green, int blue) {
        return new UiColor(red, green, blue, 255);
    }

    public static UiColor rgba(int red, int green, int blue, int alpha) {
        return new UiColor(red, green, blue, alpha);
    }

    public String hex() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    public String cssRgba() {
        return "rgba(" + red + ", " + green + ", " + blue + ", " + (alpha / 255.0d) + ")";
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
