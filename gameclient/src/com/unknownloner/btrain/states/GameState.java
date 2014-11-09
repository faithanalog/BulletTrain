package com.unknownloner.btrain.states;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gfx.SpriteBatch;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.Level;
import com.unknownloner.btrain.math.Mat3;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class GameState {

    protected final Shader shader;
    protected SpriteBatch batch;
    protected Mat3 model = new Mat3();
    FloatBuffer modelBuf = BufferUtils.createFloatBuffer(9);

    public GameState() {
        batch = new SpriteBatch();
        shader = batch.getShader();
    }

    public abstract void init();

    public abstract void tick();

    public abstract void draw();
}
