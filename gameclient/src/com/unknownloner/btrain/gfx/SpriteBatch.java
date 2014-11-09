package com.unknownloner.btrain.gfx;

import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
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
        tex.bind();
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
        shader.use();
    }

    public Texture getTexture() {
        return curTex;
    }

    public Shader getShader() {
        return curShader;
    }

    public void begin() {
        if (isBatching) {
            throw new RuntimeException("Tried to start sprite batching while sprite batching");
        }
        isBatching = true;
        curShader.use();
        curTex.bind();
        glBindBuffer(GL_ARRAY_BUFFER, glBuf);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
    }

    public void end() {
        if (!isBatching) {
            throw new RuntimeException("Ended sprite batching but was not sprite batching");
        }
        flush();

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }

    private void flush() {
        verts.flip();
        glBufferData(GL_ARRAY_BUFFER, verts, GL_STREAM_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 4 * 4, 2 * 4);

        glDrawArrays(GL_TRIANGLES, 0, verts.limit() / 4);
        verts.clear();
    }

    //This stuff is the interesting part
    public void addVert(double x, double y, double u, double v) {
        verts.put((float)x).put((float)y).put((float)u).put((float)v);
    }

    /**
     * Draws a texture at (x,y)
     * @param tex
     * @param x
     * @param y
     */
    public void drawTexture(Texture tex, double x, double y) {
        drawTexture(tex, x, y, tex.width, tex.height);
    }

    /**
     * Draws a texture at (x,y) scaled to the given size
     * @param tex
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void drawTexture(Texture tex, double x, double y, double w, double h) {
        setTexture(tex);
        addVert(x + w, y + h, 1.0, 0.0);
        addVert(x + 0, y + h, 0.0, 0.0);
        addVert(x + 0, y + 0, 0.0, 1.0);

        addVert(x + 0, y + 0, 0.0, 1.0);
        addVert(x + w, y + 0, 1.0, 1.0);
        addVert(x + w, y + h, 1.0, 0.0);
    }

    /**
     * Draws a texture at (x,y) with size of width,height.
     * @param tex
     * @param x
     * @param y
     * @param w
     * @param h
     * @param u0 U coordinate of top left corner
     * @param v0 V coordinate of top left corner
     * @param u1 U coordinate of bottom right corner
     * @param v1 V coordinate of bottom right corner
     */
    public void drawTextureUV(Texture tex, double x, double y, double w, double h, double u0, double v0, double u1, double v1) {
        setTexture(tex);
        addVert(x + w, y + h, u1, v0);
        addVert(x + 0, y + h, u0, v0);
        addVert(x + 0, y + 0, u0, v1);

        addVert(x + 0, y + 0, u0, v1);
        addVert(x + w, y + 0, u1, v1);
        addVert(x + w, y + h, u1, v0);
    }

    public void drawTextureRegion(Texture tex, double x, double y, int tx, int ty, int tw, int th) {
        drawTextureRegion(tex, x, y, tw, th, tx, ty, tw, th);
    }

    public void drawTextureRegion(Texture tex, double x, double y, double w, double h, int tx, int ty, int tw, int th) {
        double u0 = tx / (double)tex.width;
        double v0 = ty / (double)tex.height;
        double u1 = u0 + tw / (double)tex.width;
        double v1 = v0 + th / (double)tex.height;
        drawTextureUV(tex, x, y, w, h, u0, v0, u1, v1);
    }


}
