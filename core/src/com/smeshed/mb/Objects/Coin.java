package com.smeshed.mb.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Anim;

public class Coin {
    public enum CoinType {
        GIANT_COIN, LITTLE_COIN;
    }

    private CoinType CoinType;
    private Anim anim;
    private int height, width;
    private float x, y;
    private Rectangle hitbox;
    private boolean catched;

    public Coin(CoinType CoinType, float x, float y, int width, int height) {
        this.CoinType = CoinType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, this.width, this.height);
        this.catched = false;
    }

    public void draw(SpriteBatch batch, float stateTime) {
        if (CoinType == CoinType.GIANT_COIN)
            anim = new Anim(Gdx.files.internal("./images/object/giant-coin-rotating.png"), 6, 1, 0.1f);

        if (!catched)
            batch.draw(anim.getAnimation(stateTime), x, y, this.width, this.height);

    }

    public void ramasser() {
        catched = true;
    }

    public boolean isRamasse() {
        return catched;
    }

    public CoinType getCoinType() {
        return CoinType;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
