package com.smeshed.mb.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Anim;

public class Character {
    private float y;
    private float x;
    private float initialX;
    private float initialY;
    private int width;
    private int height;
    private Rectangle hitbox;
    private CharacterEtat etat;
    private CharacterFacing facing;
    private Anim marioStaticLeft, marioStaticRight, marioWalkRight, marioWalkLeft, marioJumpLeft, marioJumpRight,
            marioRunLeft, marioRunRight;

    public enum CharacterEtat {
        STATIC, JUMPRUN, JUMP, JUMPWALK, WALK, RUN, FALL, DEAD;
    }

    public enum CharacterFacing {
        LEFT, RIGHT
    }

    public Character(int width, int height, float x, float y) {

        etat = CharacterEtat.STATIC;
        facing = CharacterFacing.LEFT;
        this.initialX = x;
        this.initialY = y;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(SpriteBatch batch, float stateTime) {
        marioStaticLeft = new Anim(Gdx.files.internal("./images/mario/mario-static-left.png"), 5, 1, 0.1f);
        marioStaticRight = new Anim(Gdx.files.internal("./images/mario/mario-static-right.png"), 5, 1, 0.1f);
        marioRunLeft = new Anim(Gdx.files.internal("./images/mario/mario-run-left.png"), 8, 1, 0.04f);
        marioRunRight = new Anim(Gdx.files.internal("./images/mario/mario-run-right.png"), 8, 1, 0.04f);
        marioWalkLeft = new Anim(Gdx.files.internal("./images/mario/mario-walk-left.png"), 6, 2, 0.2f);
        marioWalkRight = new Anim(Gdx.files.internal("./images/mario/mario-walk-right.png"), 6, 2, 0.2f);
        marioJumpLeft = new Anim(Gdx.files.internal("./images/mario/mario-jump-left.png"), 7, 1, 0.08f);
        marioJumpRight = new Anim(Gdx.files.internal("./images/mario/mario-jump-right.png"), 7, 1, 0.08f);

        animationSelector(batch, stateTime);
        hitbox = new Rectangle(x, y, this.width, this.height);

    }

    public void animationSelector(SpriteBatch batch, float stateTime) {
        switch (etat) {
        case STATIC:
            switch (facing) {
            case LEFT:
                batch.draw(marioStaticLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioStaticRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            default:
                break;
            }
            break;
        case JUMP:
            switch (facing) {
            case LEFT:
                batch.draw(marioJumpLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioJumpRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
            }
            break;
        case JUMPWALK:
            switch (facing) {
            case LEFT:
                batch.draw(marioJumpLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioJumpRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
            }
            break;
        case JUMPRUN:
            switch (facing) {
            case LEFT:
                batch.draw(marioJumpLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioJumpRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
            }
            break;
        case WALK:
            switch (facing) {
            case LEFT:
                batch.draw(marioWalkLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioWalkRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            }
            break;
        case RUN:
            switch (facing) {
            case LEFT:
                batch.draw(marioRunLeft.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            case RIGHT:
                batch.draw(marioRunRight.getAnimation(stateTime), initialX, initialY, this.width, this.height);
                break;
            }
            break;
        case DEAD:
            break;
        case FALL:
            break;
        }

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

            etat = CharacterEtat.DEAD;
            return true;
        }
        return false;
    }
}
