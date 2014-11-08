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
    private Mat3 model = new Mat3();
    FloatBuffer modelBuf = BufferUtils.createFloatBuffer(9);

    public LevelRenderer(Level level) throws IOException {
        this.level = level;
        shader = new Shader(Util.readText("/shaders/sprites.vert"), Util.readText("/shaders/sprites.frag"), "Level Shader");
        levelTex = Texture.load(level.background);

        bgVerts = glGenBuffers();


        float[] bgVertData = Util.toTris(new float[] {
                1f, 1f, 1f, 0f,
                0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f,
                1f, 0f, 1f, 1f

        }, 4);
        FloatBuffer buf = BufferUtils.createFloatBuffer(bgVertData.length);
        buf.put(bgVertData).flip();
        glEnable(GL_ARRAY_BUFFER);
        glBindBuffer(GL_ARRAY_BUFFER, bgVerts);
        glBufferData(GL_ARRAY_BUFFER, buf, GL_STATIC_DRAW);
    }

    public void render() {
        shader.use();
        glUniform2f(shader.uniformLoc("u_screen_size"), 1.0f, 1.0f);

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);
        glBindBuffer(GL_ARRAY_BUFFER, bgVerts);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 4 * 4, 2 * 4);

        glDrawArrays(GL_TRIANGLES, 0, 6);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }

}
