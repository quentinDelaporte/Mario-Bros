package com.smeshed.mb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Character {
    private Texture texture;
    private int width;
    private int height;
    private Rectangle hitbox;
    private boolean walkingToLeft, walkingToRight, jumpingToLeft, jumpingToRight, statiq, dead;

    public Character(Texture texture, int width, int height) {
        this.texture = texture;
        this.height = height;
        this.width = width;
        this.walkingToLeft = false;
        this.walkingToRight = false;
        this.jumpingToLeft = false;
        this.jumpingToRight = false;
        this.statiq = true;
        this.dead = false;
    }

    public void dessine(SpriteBatch batch, int posx, int posy) {
        batch.draw(texture, posx, posy, this.width, this.height);
        hitbox = new Rectangle(posx, posy, this.width, this.height);
    }

    public void dessine(SpriteBatch batch, TextureRegion currentFrame, int posx, int posy) {
        batch.draw(currentFrame, posx, posy, this.width, this.height);
        hitbox = new Rectangle(posx, posy, this.width, this.height);
    }

    public void dessine(SpriteBatch batch, Texture texture, int posx, int posy) {
        batch.draw(texture, posx, posy, this.width, this.height);
        hitbox = new Rectangle(posx, posy, this.width, this.height);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture t) {
        this.texture = t;
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

    public boolean isWalkingToLeft() {
        return this.walkingToLeft;
    }

    public void setWalkingToLeft(boolean walkingToLeft) {
        this.walkingToLeft = walkingToLeft;
    }

    public boolean isWalkingToRight() {
        return this.walkingToRight;
    }

    public void setWalkingToRight(boolean walkingToRight) {
        this.walkingToRight = walkingToRight;
    }

    public boolean isJumpingToLeft() {
        return this.jumpingToLeft;
    }

    public void setJumpingToLeft(boolean jumpingToLeft) {
        this.jumpingToLeft = jumpingToLeft;
    }

    public boolean isJumpingToRight() {
        return this.jumpingToRight;
    }

    public void setJumpingToRight(boolean jumpingToRight) {
        this.jumpingToRight = jumpingToRight;
    }

    public boolean isStatiq() {
        return this.statiq;
    }

    public void setStatiq(boolean statiq) {
        this.statiq = statiq;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

}
