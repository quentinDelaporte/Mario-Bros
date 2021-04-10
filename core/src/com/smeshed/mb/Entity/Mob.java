package com.smeshed.mb.Entity;

import com.badlogic.gdx.math.Rectangle;

public class Mob {
    private float y;
    private float x;
    private float initialX;
    private float initialY;
    private int width;
    private int height;
    private Rectangle hitbox;
    private MobType mob;
    private MobEtat etat;
    private MobFacing facing;
    private boolean respawn;

    public enum MobType {
        GOOMBA, KOOPA_TROOPA, SHY_GUY, PIRANHA_PLANT, BILL_BALLE, BILL_BOURRIN, BLOOPS, BOB_OMB, BOO, BOOXER,
        CHEEP_CHEEP, CHEEP_CHOMP, CRYO_PIRANHA_PLANT, FRERE_MARTEAU, GRAND_GOOMBA, HERISS, WIGGLER, SKELEREX,
        SUPER_THWOMP, SWOOPER, THWOMP;
    }

    public enum MobFacing {
        LEFT, RIGHT;
    }

    public enum MobEtat {
        STATIC, JUMP, WALK, RUN, FALL, DEAD;
    }

    public Mob(float y, float x, int width, int height, MobType mob, boolean respawn) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.mob = mob;
        this.respawn = respawn;
        initialX = x;
        initialY = y;
    
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getInitialX() {
        return initialX;
    }

    public void setInitialX(float initialX) {
        this.initialX = initialX;
    }

    public float getInitialY() {
        return initialY;
    }

    public void setInitialY(float initialY) {
        this.initialY = initialY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public MobType getMob() {
        return mob;
    }

    public void setMob(MobType mob) {
        this.mob = mob;
    }

    public MobEtat getEtat() {
        return etat;
    }

    public void setEtat(MobEtat etat) {
        this.etat = etat;
    }

    public MobFacing getFacing() {
        return facing;
    }

    public void setFacing(MobFacing facing) {
        this.facing = facing;
    }

}
