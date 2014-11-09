package com.unknownloner.btrain.states;

import com.unknownloner.btrain.BulletTrain;
import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.core.GameStates;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.ui.MenuText;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;
import static org.lwjgl.opengl.GL11.*;

public class GameOver extends GameState{

    Texture texture;

    int select = 0;

    String gameOverText = "GAME OVER!";
    int gameOverX = (Display.getWidth() - Util.stringWidth(gameOverText, 10)) / 2;
    int gameOverY = Display.getHeight() * 5 / 6;
    MenuText gameOver = new MenuText(gameOverText, 10, gameOverX, gameOverY, 1.0f, 1.0f, 0.0f, 1.0f);

    String menuText = "Main Menu";
    int menuX = (Display.getWidth() - Util.stringWidth(menuText, 5)) / 2;
    int menuY = (Display.getHeight() * 11 / 20);
    MenuText menu = new MenuText(menuText, 5, menuX, menuY, 1.0f, 1.0f, 0.0f, 1.0f);

    String exitText = "Exit Game";
    int exitX = (Display.getWidth() - Util.stringWidth(exitText, 5)) / 2;
    int exitY = (Display.getHeight() * 13 / 30);
    MenuText exit = new MenuText(exitText, 5, exitX, exitY, 1.0f, 1.0f, 0.0f, 1.0f);

    MenuText[] options = {menu, exit};

    public GameOver() throws IOException {
        texture = Texture.load("/textures/space.jpg");
    }

    public void init() {


    }

    public void tick() {
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
                    if(select == 0) {
                        BulletTrain.currentGameState = GameStates.MAIN_MENU;
                    } else if (select == 1){
                        Display.destroy();
                        System.exit(0);
                    }
                }
            }
        }
        if(options[select].string.charAt(0) != '\u0000')
            options[select].string = "\u0000" + options[select].string;
    }

    public void draw() {
        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);
        glEnable(GL_BLEND);
        batch.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());

        gameOver.draw(batch);

        for(MenuText text: options){
            text.draw(batch);
        }

        batch.end();
        glDisable(GL_BLEND);
    }
}
