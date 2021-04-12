package com.smeshed.mb.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private Music menuMusic;
    private float volume;

    public BackgroundMusic(float volume, String path) {
        this.volume = volume;
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        menuMusic.setLooping(true);
        menuMusic.setVolume(this.volume);
        menuMusic.play();

    }

    public void setVolume(float volume) {
        this.volume = volume;
        menuMusic.setVolume(this.volume);

    }

    public void stop() {
        menuMusic.setVolume(0f);
        menuMusic.stop();

    }
}
