package com.unknownloner.btrain.states;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.ui.MenuText;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class LevelSelect extends GameState {

    Texture texture;

    int select = 1;

    String titleText = "Bullet Train";
    int titleX = (Display.getWidth() - Util.stringWidth(titleText, 10)) / 2;
    int titleY = Display.getHeight() * 3 / 4;
    MenuText title = new MenuText(titleText, 10, titleX, titleY, 1.0f, 1.0f, 0.0f, 1.0f);

    String levelText = "Level Select";
    int levelX = (Display.getWidth() - Util.stringWidth(levelText, 5)) / 2;
    int levelY = (Display.getHeight() * 11 / 20);
    MenuText level = new MenuText(levelText, 5, levelX, levelY, 1.0f, 1.0f, 0.0f, 1.0f);

    String exitText = "Exit Game";
    int exitX = (Display.getWidth() - Util.stringWidth(exitText, 5)) / 2;
    int exitY = (Display.getHeight() * 7 / 20);
    MenuText exit = new MenuText(exitText, 5, exitX, exitY, 1.0f, 1.0f, 0.0f, 1.0f);

    public LevelSelect() throws IOException {
        texture = Texture.load("/textures/space.jpg");
    }

    public void init(){

    }

    public void tick(){

    }

    public void draw(){
        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());

        batch.end();
    }
}