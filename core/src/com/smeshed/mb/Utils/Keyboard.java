package com.smeshed.mb.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Entity.Character;
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.GUI.Parameter;

public class Keyboard {
    private Parameter parameter;

    public Keyboard() {
        this.parameter = new Parameter();
    }

    public boolean ToLeftFacingPressed(Character mario) {
        if (mario.getEtat() != CharacterEtat.DEAD) {
            if (Gdx.input.isKeyPressed(parameter.getLeftKey()))
                return true;
        }
        return false;
    }

    public boolean ToRightFacingPressed(Character mario) {
        if (mario.getEtat() != CharacterEtat.DEAD) {
            if (Gdx.input.isKeyPressed(parameter.getRightKey()))
                return true;
        }
        return false;
    }

    public boolean WalkPressed(Character mario) {
        if (mario.getEtat() != CharacterEtat.DEAD
                && (Gdx.input.isKeyPressed(parameter.getLeftKey()) || Gdx.input.isKeyPressed(parameter.getRightKey()))
                && !Gdx.input.isKeyPressed(parameter.getRunKey()) && mario.getEtat() != CharacterEtat.JUMPWALK
                && mario.getEtat() != CharacterEtat.JUMPRUN && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLWALK && mario.getEtat() != CharacterEtat.FALLRUN)
            // ! Marche
            return true;
        return false;
    }

    public boolean RunPressed(Character mario) {
        if (mario.getEtat() != CharacterEtat.DEAD && Gdx.input.isKeyPressed(parameter.getRunKey())
                && (Gdx.input.isKeyPressed(parameter.getLeftKey()) || Gdx.input.isKeyPressed(parameter.getRightKey()))
                && mario.getEtat() != CharacterEtat.JUMPWALK && mario.getEtat() != CharacterEtat.JUMPRUN
                && mario.getEtat() != CharacterEtat.FALL && mario.getEtat() != CharacterEtat.FALLWALK
                && mario.getEtat() != CharacterEtat.FALLRUN)
            // ! Course
            return true;
        return false;
    }

    public boolean StaticPressed(Character mario) {
        if (mario.getEtat() != CharacterEtat.DEAD && !Gdx.input.isKeyPressed(parameter.getRightKey())
                && !Gdx.input.isKeyPressed(parameter.getLeftKey()) && !Gdx.input.isKeyPressed(parameter.getUpKey())
                && !Gdx.input.isKeyPressed(parameter.getDownKey()) && mario.getEtat() != CharacterEtat.JUMPWALK
                && mario.getEtat() != CharacterEtat.JUMPRUN && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLRUN && mario.getEtat() != CharacterEtat.FALLWALK)
            // ! Static
            return true;
        return false;
    }

    public boolean AnyJumpPressed(Character mario, CharacterEtat lastEtat) {
        if (mario.getEtat() != CharacterEtat.DEAD && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLWALK && mario.getEtat() != CharacterEtat.FALLRUN
                && Gdx.input.isKeyPressed(parameter.getUpKey()) && lastEtat != CharacterEtat.JUMP
                && lastEtat != CharacterEtat.JUMPWALK && lastEtat != CharacterEtat.JUMPRUN)
            return true;
        return false;
    }

    public boolean JumpRunPressed(Character mario, CharacterEtat lastEtat) {
        if (mario.getEtat() != CharacterEtat.DEAD && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLWALK && mario.getEtat() != CharacterEtat.FALLRUN
                && Gdx.input.isKeyPressed(parameter.getUpKey()) && Gdx.input.isKeyPressed(parameter.getRunKey()))
            return true;
        return false;
    }

    public boolean JumpWalkPressed(Character mario, CharacterEtat lastEtat) {
        if (mario.getEtat() != CharacterEtat.DEAD && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLWALK && mario.getEtat() != CharacterEtat.FALLRUN
                && Gdx.input.isKeyPressed(parameter.getUpKey()) && !Gdx.input.isKeyPressed(parameter.getRunKey())
                && (Gdx.input.isKeyPressed(parameter.getLeftKey()) || Gdx.input.isKeyPressed(parameter.getRightKey())))
            return true;
        return false;
    }

    public boolean JumpStaticPressed(Character mario, CharacterEtat lastEtat) {
        if (mario.getEtat() != CharacterEtat.DEAD && mario.getEtat() != CharacterEtat.FALL
                && mario.getEtat() != CharacterEtat.FALLWALK && mario.getEtat() != CharacterEtat.FALLRUN
                && Gdx.input.isKeyPressed(parameter.getUpKey()) && Gdx.input.isKeyPressed(parameter.getRunKey())
                && !Gdx.input.isKeyPressed(parameter.getLeftKey()) && !Gdx.input.isKeyPressed(parameter.getRightKey()))
            return true;
        return false;
    }

}
