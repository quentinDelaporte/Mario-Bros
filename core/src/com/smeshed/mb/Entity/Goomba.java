package com.smeshed.mb.Entity;

import java.text.BreakIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Anim;
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.Entity.Character.CharacterFacing;

// TODO: Creers anim et les définir;
public class Goomba extends Mob {

    private int initialX, initialY, x, y;
    int width, height;
    private Rectangle hitbox;

    public Goomba(float y, float x, int width, int height, MobType mob, boolean respawn) {

        super(y, x, width, height, mob, respawn);
        this.width = width;
        this.height = height;
        this.x = (int) x;
        this.y = (int) y;
        initialX = (int) x;
        initialY = (int) y;
    }

    public void draw(SpriteBatch batch, float stateTime) {
        Anim runLeft = new Anim(Gdx.files.internal("./images/mob/goomba/goomba-walk-left.png"), 16, 1, 0.1f);
        batch.draw(runLeft.getAnimation(stateTime), x, y, width, height);
        hitbox = new Rectangle(initialX, initialY, this.width, this.height);

    }

    // TODO: gerer saut de mario / chute
    // TODO: gerer collisions & mort
    public void move(CharacterEtat e, CharacterFacing f, int jumpHeight) {
        switch (e) {
        case WALK:
            switch (f) {
            case LEFT:
                this.x += 1;
                break;
            case RIGHT:
                this.x -= 3;
                break;
            }
            break;
        case RUN:
            switch (f) {
            case LEFT:
                this.x += 3;
                break;
            case RIGHT:
                this.x -= 5;
                break;
            }
            break;
        case JUMP:
            this.y -= jumpHeight;
            break;
        case FALL:
            this.y += 4;
            break;
        case STATIC:
            this.x -= 1;
            break;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}