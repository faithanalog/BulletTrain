package com.unknownloner.btrain.logic;

import org.lwjgl.input.Keyboard;

public class KeyInput implements Input {
    @Override
    public boolean left() {
        return Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
    }

    @Override
    public boolean right() {
        return Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
    }

    @Override
    public boolean up() {
        return Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
    }

    @Override
    public boolean down() {
        return Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
    }

    @Override
    public boolean shooting() {
        return Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_SPACE);
    }
}
