package com.unknownloner.btrain.gfx;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.Level;
import com.unknownloner.btrain.math.Mat3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.IOException;
import java.nio.FloatBuffer;

public class LevelRenderer {

    public final Level level;
    public final Shader shader;
    public final Texture levelTex;

    private SpriteBatch batch;

    private Mat3 model = new Mat3();
    FloatBuffer modelBuf = BufferUtils.createFloatBuffer(9);

    public LevelRenderer(Level level) throws IOException {
        this.level = level;
        shader = new Shader(Util.readText("/shaders/sprites.vert"), Util.readText("/shaders/sprites.frag"), "Level Shader");
        levelTex = Texture.load(level.background);

        batch = new SpriteBatch();
        batch.setShader(shader);
    }

    public void render() {

        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), 1.0f, 1.0f);

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);

        batch.drawTexture(levelTex, 0.0, 0.0, 1.0, 1.0);
        batch.flush();

        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());
        batch.drawString("Hello World!", 50.0, 50.0, 3);

        batch.end();
    }

}
