package com.smeshed.mb.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gui {
    public TimerParty t;

    public Gui() {
        t = new TimerParty(600, 470);
        t.run();

    }

    public void draw(SpriteBatch batch) {
        t.draw(batch);
    }

    public TimerParty getTimer() {
        return t;
    }
}
