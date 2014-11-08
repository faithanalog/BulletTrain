package com.unknownloner.btrain.gfx;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.Level;
import com.unknownloner.btrain.math.Mat3;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.IOException;
import java.nio.FloatBuffer;

public class LevelRenderer {

    public final Level level;
    public final Shader shader;
    public final Texture levelTex;

    private int bgVerts;

    public LevelRenderer(Level level) throws IOException {
        this.level = level;
        shader = new Shader(Util.readText("/shaders/sprites.vert"), Util.readText("/shaders/sprites.frag"), "Level Shader");
        levelTex = Texture.load(level.background);

        bgVerts = glGenBuffers();
        glBindBuffer(GL_VERTEX_ARRAY, bgVerts);

        float[] bgVertData = Util.toTris(new float[] {
                1f, 1f, 1f, 0f,
                0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f,
                1f, 0f, 1f, 1f

        }, 4);
        FloatBuffer buf = BufferUtils.createFloatBuffer(bgVertData.length);
        buf.put(bgVertData).flip();
        glBufferData(GL_VERTEX_ARRAY, buf, GL_STATIC_DRAW);
    }

    public void render() {
        glBindBuffer(GL_VERTEX_ARRAY, bgVerts);
        shader.use();


    }

}
