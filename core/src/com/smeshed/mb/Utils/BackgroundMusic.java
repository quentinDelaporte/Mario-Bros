package com.smeshed.mb.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private Music menuMusic;

    public BackgroundMusic() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("./song/music/1-1.mp3"));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
}
