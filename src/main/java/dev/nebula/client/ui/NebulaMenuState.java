package dev.nebula.client.ui;

public enum NebulaMenuState {
    MAIN,
    PLAY_MODE,
    EXITING;

    public NebulaMenuState transition(NebulaMenuAction action) {
        return switch (this) {
            case MAIN -> switch (action) {
                case PLAY -> PLAY_MODE;
                case EXIT -> EXITING;
                default -> MAIN;
            };
            case PLAY_MODE -> switch (action) {
                case BACK -> MAIN;
                case EXIT -> EXITING;
                default -> PLAY_MODE;
            };
            case EXITING -> EXITING;
        };
    }
}
