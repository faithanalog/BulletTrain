package com.unknownloner.btrain.states;

import com.unknownloner.btrain.BulletTrain;
import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.core.GameStates;
import com.unknownloner.btrain.core.GameTick;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.ui.MenuText;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class Paused extends GameState{

    Texture texture;

    int select = 0;

    String pausedText = "Game Paused";
    int pausedX = (Display.getWidth() - Util.stringWidth(pausedText, 10)) / 2;
    int pausedY = Display.getHeight() * 3 / 4;
    MenuText paused = new MenuText(pausedText, 10, pausedX, pausedY, 1.0f, 1.0f, 0.0f, 1.0f);

    String resumeText = "\u0000Resume";
    int resumeX = (Display.getWidth() - Util.stringWidth(resumeText, 5)) / 2;
    int resumeY = (Display.getHeight() / 2);
    MenuText resume = new MenuText(resumeText, 5, resumeX, resumeY, 1.0f, 1.0f, 0.0f, 1.0f);

    MenuText[] options = {resume};

    public Paused() throws IOException{
        texture = Texture.load("/textures/space.jpg");
    }

    public void init(){

    }

    public void tick() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_RETURN){
                        GameTick.inGame.level.music.clip.start();
                        BulletTrain.currentGameState = GameStates.IN_LEVEL;
                }
            }
        }
    }

    public void draw(){
        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);
        batch.setColor(0.0f, 0.0f, .75f, 1.0f);
        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());

        paused.draw(batch);

        for(MenuText text: options){
            text.draw(batch);
        }

        batch.end();
    }
}
