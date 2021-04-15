package com.smeshed.mb.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Anim;
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.Entity.Character.CharacterFacing;
import com.smeshed.mb.Utils.Coordonnees;

public class BigCoin extends Mob {
    private Anim anim;
    private int height, width;
    double x;
    private double y;
    private Rectangle hitbox;
    private boolean catched;
    private Coordonnees entiteCoordonnees;

    public BigCoin(float x, float y, int width, int height, EntityType entity, boolean respawn, boolean catched) {
        super(x, y, width, height, entity, respawn);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, this.width, this.height);
        this.catched = false;
    }

    public void draw(SpriteBatch batch, float stateTime) {
        anim = new Anim(Gdx.files.internal("./images/object/giant-coin-rotating.png"), 6, 1, 0.1f);

        if (!catched)
            batch.draw(anim.getAnimation(stateTime), (float) x, (float) y, (float) this.width, (float) this.height);

    }

    public void ramasser() {
        catched = true;
    }

    public boolean isRamasse() {
        return catched;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void move(CharacterEtat e, CharacterFacing f, double jumpHeight) {
        entiteCoordonnees = antiMarioMove(e, f, jumpHeight);
        this.x = entiteCoordonnees.getX();
        this.y = entiteCoordonnees.getY();
    }

}
