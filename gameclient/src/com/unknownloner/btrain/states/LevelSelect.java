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

public class LevelSelect extends GameState {

    public float red = 1.0f;
    public float green = 1.0f;
    public float blue = 1.0f;

    Texture texture;

    int select = 1;

    int textScale = 7;

    String difficultyText = "Difficulty";
    int difficultyX = (Display.getWidth() - Util.stringWidth(difficultyText, 10)) / 2;
    int difficultyY = Display.getHeight() * 5 / 6;
    MenuText difficulty = new MenuText(difficultyText, 10, difficultyX, difficultyY, 1.0f, 1.0f, 0.0f, 1.0f);


    String easyText = "Easy";
    int easyX = (Display.getWidth() - Util.stringWidth(easyText, textScale)) / 2;
    int easyY = Display.getHeight() * 4 / 6;
    MenuText easy = new MenuText(easyText, textScale, easyX, easyY, 1.0f, 1.0f, 0.0f, 1.0f);

    String mediumText = "Medium";
    int mediumX = (Display.getWidth() - Util.stringWidth(mediumText, textScale)) / 2;
    int mediumY = (Display.getHeight() * 11 / 20);
    MenuText medium = new MenuText(mediumText, textScale, mediumX, mediumY, 1.0f, 1.0f, 0.0f, 1.0f);

    String hardText = "Hard";
    int hardX = (Display.getWidth() - Util.stringWidth(hardText, textScale)) / 2;
    int hardY = (Display.getHeight() * 13 / 30);
    MenuText hard = new MenuText(hardText, textScale, hardX, hardY, 1.0f, 1.0f, 0.0f, 1.0f);

    String backText = "Back";
    int backX = (Display.getWidth() - Util.stringWidth(hardText, textScale)) / 2;
    int backY = (Display.getHeight() * 1 / 6);
    MenuText back = new MenuText(backText, textScale, backX, backY, 1.0f, 1.0f, 0.0f, 1.0f);

    MenuText[] options = {easy, medium, hard, back};

    public LevelSelect() throws IOException {
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
                        BulletTrain.currentGameState = GameStates.IN_LEVEL;
                    } else if (select == options.length - 1){
                        BulletTrain.currentGameState = GameStates.MAIN_MENU;
                    }
                }
            }
        }
        if(options[select].string.charAt(0) != '\u0000')
            options[select].string = "\u0000" + options[select].string;
        switch (select){
            case 0:
                red = 0.0f;
                green = 1.0f;
                blue = 0.0f;
                break;
            case 1:
                red = 1.0f;
                green = 1.0f;
                blue = 0.0f;
                break;
            case 2:
                red = 1.0f;
                green = 0.5f;
                blue = 0.0f;
                break;
            case 3:
                red = 0.0f;
                green = 0.0f;
                blue = 1.0f;
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
        batch.setColor(red, green, blue, 1.0f);
        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());

        difficulty.draw(batch);

        for(MenuText text: options){
            text.draw(batch);
        }

        batch.end();
    }
}