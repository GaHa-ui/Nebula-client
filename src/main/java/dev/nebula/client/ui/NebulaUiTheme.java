package dev.nebula.client.ui;

import java.util.List;

public final class NebulaUiTheme {
    private final String logoTexture;
    private final List<String> panoramaFrames;
    private final List<NebulaButtonLink> links;

    private NebulaUiTheme(String logoTexture, List<String> panoramaFrames, List<NebulaButtonLink> links) {
        this.logoTexture = logoTexture;
        this.panoramaFrames = List.copyOf(panoramaFrames);
        this.links = List.copyOf(links);
    }

    public static NebulaUiTheme createDefault() {
        return new NebulaUiTheme(
                "assets/nebula/textures/gui/logo_placeholder.png",
                List.of(
                        "assets/nebula/textures/gui/panorama/nebula_0.png",
                        "assets/nebula/textures/gui/panorama/nebula_1.png",
                        "assets/nebula/textures/gui/panorama/nebula_2.png",
                        "assets/nebula/textures/gui/panorama/nebula_3.png",
                        "assets/nebula/textures/gui/panorama/nebula_4.png",
                        "assets/nebula/textures/gui/panorama/nebula_5.png",
                        "assets/nebula/textures/gui/panorama/nebula_6.png",
                        "assets/nebula/textures/gui/panorama/nebula_7.png",
                        "assets/nebula/textures/gui/panorama/nebula_8.png",
                        "assets/nebula/textures/gui/panorama/nebula_9.png",
                        "assets/nebula/textures/gui/panorama/nebula_10.png",
                        "assets/nebula/textures/gui/panorama/nebula_11.png"),
                List.of(
                        new NebulaButtonLink("Telegram", "https://t.me/nebula_client"),
                        new NebulaButtonLink("GitHub", "https://github.com/NebulaClient/Nebula-client"),
                        new NebulaButtonLink("Сайт", "https://nebula-client.example.com")));
    }

    public String logoTexture() { return logoTexture; }
    public List<String> panoramaFrames() { return panoramaFrames; }
    public List<NebulaButtonLink> links() { return links; }
    public void prepareAssets() { /* Resource placeholders are packaged under assets/nebula/textures/gui. */ }
}
