package com.smeshed.mb.GUI;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TimerParty extends TimerTask {
    private CharSequence str;
    private BitmapFont font;
    private float timer;
    private int x, y;
    private Runnable TimerRunnable;

    public TimerParty(int x, int y) {
        font = new BitmapFont();
        str = "";
        timer = 700;
        this.x = x;
        this.y = y;

        TimerRunnable = new Runnable() {
            public void run() {
                timer--;
            }
        };
    }

    public void draw(SpriteBatch spriteBatch) {
        str = "" + (int) timer;
        font.draw(spriteBatch, str, x, y);

    }

    @Override
    public void run() {
        Thread t = new Thread() {
            public void run() {
                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.scheduleAtFixedRate(TimerRunnable, 0, 1, TimeUnit.SECONDS);
            }
        };
        t.start();

    }

    public boolean isTimerFinished() {
        return timer - 700 == 0 ? true : false;
    }
}
