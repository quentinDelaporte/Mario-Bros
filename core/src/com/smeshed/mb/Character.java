package com.smeshed.mb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Character {
    private TextureRegion texture;
    private float y;
    private float x;
    private int width;
    private int height;
    private Rectangle hitbox;
    private CharacterEtat etat;
    private CharacterFacing facing;
    private FileHandle marioStandStatic;
    private Anim marioStaticLeft;
    private static final long LONG_JUMP_PRESS = 150l;
    private static final float ACCELERATION = 20f;
    private static final float GRAVITY = -20f;
    private static final float MAX_JUMP_SPEED = 7f;
    private static final float DAMP = 0.90f;
    private static final float MAX_VEL = 4f;

    public enum CharacterEtat {
        STATIC, JUMP, WALK, RUN, FALL, DEAD;
    }

    public enum CharacterFacing {
        LEFT, RIGHT
    }

    public Character(int width, int height, float x, float y) {

        etat = CharacterEtat.STATIC;
        facing = CharacterFacing.LEFT;

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(SpriteBatch batch, float stateTime) {

        marioStandStatic = Gdx.files.internal("./images/mario/mario-static-left.png");
        marioStaticLeft = new Anim(marioStandStatic, 5, 1, 0.1f);

        // if (etat == CharacterEtat.STATICLEFT) {
        batch.draw(marioStaticLeft.getAnimation(stateTime), 180, 250, this.width, this.height);
        // }
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

    public CharacterFacing getFacing() {
        return facing;
    }

    public void setEtat(CharacterEtat etat) {
        this.etat = etat;
    }

    public void setFacing(CharacterFacing facing) {
        this.facing = facing;
    }

    public float getY() {
        return y;
    }

    public void setY(float f) {
        y = f;
    }

    public float getX() {
        return x;
    }

    public void setX(float f) {
        x = f;
    }

    public boolean isDead() {
        if (y <= 0) {
            System.out.println("Lose");

            etat = CharacterEtat.DEAD;
            return true;
        }
        return false;
    }

    public static long getLongJumpPress() {
        return LONG_JUMP_PRESS;
    }

    public static float getAcceleration() {
        return ACCELERATION;
    }

    public static float getGravity() {
        return GRAVITY;
    }

    public static float getMaxJumpSpeed() {
        return MAX_JUMP_SPEED;
    }

    public static float getDamp() {
        return DAMP;
    }

    public static float getMaxVel() {
        return MAX_VEL;
    }

}
