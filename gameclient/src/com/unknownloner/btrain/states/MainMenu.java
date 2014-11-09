package com.unknownloner.btrain.states;

import com.unknownloner.btrain.BulletTrain;
import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.core.GameStates;
import com.unknownloner.btrain.core.GameTick;
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

    int select = 0;

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

    MenuText[] options = {level, exit};

    public MainMenu() throws IOException {
        texture = Texture.load("/textures/space.jpg");
    }


    public void init(){

    }

    public void tick(){
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_UP && select > 0) {
                    if(options[select].string.charAt(0) == '\u0000')
                        options[select].string = options[select].string.substring(1);
                    if(select!= 0)
                        select--;
                    options[select].string = "\u0000" + options[select].string;
                } else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && select < 4) {
                    if(options[select].string.charAt(0) == '\u0000')
                        options[select].string = options[select].string.substring(1);
                    if(select != options.length - 1)
                        select++;
                    options[select].string = "\u0000" + options[select].string;
                } else if (Keyboard.getEventKey() == Keyboard.KEY_RETURN){
                    if(select < options.length - 1){
                        BulletTrain.currentGameState = GameStates.LEVEL_SELCET;
                    } else if (select == options.length - 1){
                        Display.destroy();
                        System.exit(0);
                    }
                }
            }
        }
        if(options[select].string.charAt(0) != '\u0000')
            options[select].string = "\u0000" + options[select].string;
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
