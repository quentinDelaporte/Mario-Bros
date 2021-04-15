package com.smeshed.mb.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.Entity.Character.CharacterFacing;
import com.smeshed.mb.Utils.Coordonnees;

public abstract class Mob {
    private double y;
    private double x;
    private double initialX;
    private double initialY;
    private double width;
    private double height;
    private Rectangle hitbox;
    private EntityType mob;
    private EntityEtat etat;
    private EntityFacing facing;
    private boolean respawn;

    public enum EntityType {
        GOOMBA, KOOPA_TROOPA, SHY_GUY, PIRANHA_PLANT, BILL_BALLE, BILL_BOURRIN, BLOOPS, BOB_OMB, BOO, BOOXER,
        CHEEP_CHEEP, CHEEP_CHOMP, CRYO_PIRANHA_PLANT, FRERE_MARTEAU, GRAND_GOOMBA, HERISS, WIGGLER, SKELEREX,
        SUPER_THWOMP, SWOOPER, THWOMP, BIG_COIN, COIN;
    }

    public enum EntityFacing {
        LEFT, RIGHT;
    }

    public enum EntityEtat {
        STATIC, JUMP, WALK, RUN, FALL, DEAD;
    }

    public Mob(double y2, double x2, double width2, double height2, EntityType mob, boolean respawn) {
        this.y = y2;
        this.x = x2;
        this.width = width2;
        this.height = height2;
        this.mob = mob;
        this.respawn = respawn;
        initialX = x2;
        initialY = y2;

    }

    protected abstract void draw(SpriteBatch batch, float stateTime);

    protected abstract void move(CharacterEtat e, CharacterFacing f, double jumpHeight);

    public Coordonnees antiMarioMove(CharacterEtat e, CharacterFacing f, double jumpHeight) {
        switch (e) {
        case WALK:
            x = (f == CharacterFacing.LEFT) ? x + 2 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 2 : x;
            break;
        case RUN:
            x = (f == CharacterFacing.LEFT) ? x + 4 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 4 : x;
            break;
        case JUMPWALK:
            x = (f == CharacterFacing.LEFT) ? x + 2 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 2 : x;
            y = y - jumpHeight;
            break;
        case JUMPRUN:
            x = (f == CharacterFacing.LEFT) ? x + 4 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 4 : x;
            y = y - jumpHeight;
            break;
        case JUMP:
            y = y - jumpHeight;
            break;
        case FALLWALK:
            x = (f == CharacterFacing.LEFT) ? x + 2 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 2 : x;
            y = y + jumpHeight;
            break;
        case FALLRUN:
            x = (f == CharacterFacing.LEFT) ? x + 4 : x;
            x = (f == CharacterFacing.RIGHT) ? x - 4 : x;
            y = y + jumpHeight;
            break;
        case FALL:
            y = y + jumpHeight;
            break;
        }
        return new Coordonnees(x, y);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getInitialX() {
        return initialX;
    }

    public void setInitialX(double initialX) {
        this.initialX = initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
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

    public EntityType getEntity() {
        return mob;
    }

    public void setEntity(EntityType mob) {
        this.mob = mob;
    }

    public EntityEtat getEtat() {
        return etat;
    }

    public void setEtat(EntityEtat etat) {
        this.etat = etat;
    }

    public EntityFacing getFacing() {
        return facing;
    }

    public void setFacing(EntityFacing facing) {
        this.facing = facing;
    }

}
