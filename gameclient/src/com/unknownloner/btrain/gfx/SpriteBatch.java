package com.unknownloner.btrain.gfx;

import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class SpriteBatch {

    private Texture curTex;
    private Shader curShader;

    private int glBuf;
    private FloatBuffer verts = BufferUtils.createFloatBuffer(65536);

    private boolean isBatching = false;

    public SpriteBatch() {
        glBuf = glGenBuffers();
    }

    public void setTexture(Texture tex) {
        if (!isBatching) {
            curTex = tex;
            return;
        }

        if (tex != curTex) {
            flush();
        }
        curTex = tex;
    }

    public void setShader(Shader shader) {
        if (!isBatching) {
            curShader = shader;
            return;
        }

        if (curShader != shader) {
            flush();
        }
        curShader = shader;
    }

    public Texture getTexture() {
        return curTex;
    }

    public void begin() {
        if (isBatching) {
            throw new RuntimeException("Tried to start sprite batching while sprite batching");
        }
        isBatching = true;
        curShader.use();
        curTex.bind();
    }

    public void end() {
        if (!isBatching) {
            throw new RuntimeException("Ended sprite batching but was not sprite batching");
        }
        flush();
    }

    private void flush() {
        curTex.bind();
    }


}
