package com.smeshed.mb.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation.Swing;

public class Parameter {
    private enum Action {
        RUN, LEFT, RIGHT, UP, DOWN, OPEN_CONTROL_PANNEL
    }

    private int volume = 100;
    private int left = Keys.LEFT;
    private int right = Keys.RIGHT;
    private int up = Keys.UP;
    private int down = Keys.DOWN;
    private int run = Keys.CONTROL_LEFT;

    public Parameter() {

    }

    public void assignKeys(Action a) {
        switch (a) {
        case RUN:
            if (Gdx.input.isKeyPressed(Keys.ANY_KEY))
                run = Keys.ANY_KEY;
            break;

        default:
            break;
        }

    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLeftKey() {
        return left;
    }

    public void setLeftKey(int left) {
        this.left = left;
    }

    public int getRightKey() {
        return right;
    }

    public void setRightKey(int right) {
        this.right = right;
    }

    public int getUpKey() {
        return up;
    }

    public void setUpKey(int up) {
        this.up = up;
    }

    public int getDownKey() {
        return down;
    }

    public void setDownKey(int down) {
        this.down = down;
    }

    public int getRunKey() {
        return run;
    }

    public void setRunKey(int run) {
        this.run = run;
    }

}
