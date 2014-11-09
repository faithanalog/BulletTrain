package com.unknownloner.btrain.gfx;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gfx.entity.EntityRenderer;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.Entity;
import com.unknownloner.btrain.logic.Level;
import com.unknownloner.btrain.math.Mat3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Collections;
import java.util.Comparator;

public class LevelRenderer {

    public static final double PARALLAX_FACTOR = 0.5;

    public final Level level;
    public final Texture levelTex;

    private SpriteBatch batch;

    private Mat3 model = new Mat3();
    FloatBuffer modelBuf = BufferUtils.createFloatBuffer(9);

    public LevelRenderer(Level level) throws IOException {
        this.level = level;
        levelTex = Texture.load(level.background);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        batch = new SpriteBatch();
    }

    public void render() {
        Shader shader = batch.getShader();
        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());

        shader.uniformMat3("u_model", model);
        batch.setColor(1f, 1f, 1f, 1f);

        //Parallax background
        double bx = level.player.bounds.centerX() * PARALLAX_FACTOR % (Display.getWidth() * 3);
        double by = level.player.bounds.centerY() * PARALLAX_FACTOR % (Display.getHeight() * 3);
        batch.drawTextureRegion(levelTex,
                -bx - Display.getWidth(),
                -by - Display.getHeight(),
                Display.getWidth() * 3,
                Display.getHeight() * 3,
                0, 0,
                Display.getWidth() * 3, Display.getHeight() * 3);
        batch.flush();


        //Translate everything based on player center
        Mat3 translation = Mat3.translation(Display.getWidth() / 2 - level.player.bounds.centerX(),
                Display.getHeight() / 6 - level.player.bounds.centerY());
        shader.uniformMat3("u_model", translation);

        //Sort level entities for effecient rendering
        Collections.sort(level.entities, entitySorter);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        for (Entity e : level.entities) {
            EntityRenderer.renderEntity(batch, e, e.pos.x, e.pos.y);
        }
        batch.flush();
        glDisable(GL_BLEND);
        batch.end();
    }

    private static Comparator<Entity> entitySorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            return Integer.compare(o1.type().ordinal(), o2.type().ordinal());
        }
    };

}
