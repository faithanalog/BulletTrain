package com.unknownloner.btrain.states;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.ui.MenuText;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import sun.security.krb5.internal.KdcErrException;

import java.io.IOException;
import java.security.Key;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class MainMenu extends GameState{

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

    public MainMenu() throws IOException {
        texture = Texture.load("/textures/space.jpg");
    }


    public void init(){

    }

    public void tick(){
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_UP && select == 2) {
                    select = 1;
                    exit.string = exit.string.substring(1);
                } else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && select == 1) {
                    select = 2;
                    level.string = level.string.substring(1);
                }
            }
        }
        switch(select){
            case 1:
                if(level.string.charAt(0) != '\u0000')
                    level.string = "\u0000" + level.string;
                break;
            case 2:
                if(exit.string.charAt(0) != '\u0000')
                    exit.string = "\u0000" + exit.string;
                break;
        }
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

        title.draw(batch);

        level.draw(batch);

        exit.draw(batch);

//        batch.drawString(title, levelX, levelY, 5);
        batch.end();
    }
}
