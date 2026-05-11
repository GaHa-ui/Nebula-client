package dev.nebula.client.ui;

public final class HudAnimation {
    private float displayed;

    public HudAnimation(float initialValue) { this.displayed = initialValue; }
    public float displayed() { return displayed; }

    public float approach(float target, float tickDelta, float speed) {
        float clampedDelta = Math.max(0.0f, Math.min(1.0f, tickDelta));
        float alpha = 1.0f - (float) Math.pow(1.0f - Math.max(0.01f, Math.min(1.0f, speed)), clampedDelta * 20.0f);
        displayed += (target - displayed) * alpha;
        if (Math.abs(target - displayed) < 0.01f) displayed = target;
        return displayed;
    }
}
