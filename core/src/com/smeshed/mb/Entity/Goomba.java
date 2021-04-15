package com.smeshed.mb.Entity;

import java.text.BreakIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.Jump;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Anim;
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.Entity.Character.CharacterFacing;
import com.smeshed.mb.Utils.Coordonnees;

// TODO: Creers anim et les définir;
public class Goomba extends Mob {

    private double initialX, initialY, x, y;
    private double width;
    private double height;
    private Rectangle hitbox;
    private double moveSpeed;
    private Coordonnees mob;
    private double relativeX;
    private double relativeY;

    public Goomba(double y, double x, double width, double height, EntityType mob, boolean respawn) {

        super(y, x, width, height, mob, respawn);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        initialX = x;
        initialY = y;
        relativeX = 0;
        relativeY = 0;
    }

    @Override
    public void draw(SpriteBatch batch, float stateTime) {
        Anim runLeft = new Anim(Gdx.files.internal("./images/mob/goomba/goomba-walk-left.png"), 16, 1, 0.1f);
        batch.draw(runLeft.getAnimation(stateTime), (float) x, (float) y, (float) width, (float) height);
        hitbox = new Rectangle((int) initialX, (int) initialY, (int) this.width, (int) this.height);

    }

    @Override
    public void move(CharacterEtat e, CharacterFacing f, double jumpHeight) {
        mob = antiMarioMove(e, f, jumpHeight);
        this.relativeX -= 0.8;
        this.relativeY += 0;
        this.x = mob.getX() + relativeX;
        this.y = mob.getY() + relativeY;
    }

}