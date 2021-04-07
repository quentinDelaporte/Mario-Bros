package com.smeshed.mb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Character {
    private TextureRegion texture;
    private int y;
    private int x;
    private int width;
    private int height;
    private Rectangle hitbox;
    private CharacterEtat etat;
    private FileHandle marioStandStatic;
    private Anim marioStaticLeft;

    public enum CharacterEtat {
        STATICLEFT, STATICRIGHT, JUMPLEFT, JUMPRIGHT, JUMPSTATICLEFT, JUMPSTATICRIGHT, WALKLEFT, WALKRIGHT, RUNLEFT,
        RUNRIGHT, FALL, DEAD;
    }

    public Character(int width, int height, int x, int y) {

        etat = CharacterEtat.STATICLEFT;

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(SpriteBatch batch, float stateTime) {

        marioStandStatic = Gdx.files.internal("./images/mario/mario-static-left.png");
        marioStaticLeft = new Anim(marioStandStatic, 5, 1, 0.1f);

        if (etat == CharacterEtat.STATICLEFT) {
            batch.draw(marioStaticLeft.getAnimation(stateTime), 180, 250, this.width, this.height);
        }
        hitbox = new Rectangle(x, y, this.width, this.height);

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CharacterEtat getEtat() {
        return etat;
    }

    public void setEtat(CharacterEtat etat) {
        this.etat = etat;
    }

    public int getY() {
        return y;
    }

    public void setY(int i) {
        y = i;
    }

    public int getX() {
        return x;
    }

    public void setX(int i) {
        x = i;
    }

}
