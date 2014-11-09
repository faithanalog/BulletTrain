package com.unknownloner.btrain.states;

import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class MainMenu extends GameState{

    public MainMenu() throws IOException{
        super();
    }


    public void init(){

    }

    public void tick(){

    }

    public void draw(){
        batch.begin();
        glUniform2f(shader.uniformLoc("u_screen_size"), 1.0f, 1.0f);

        modelBuf.clear();
        model.store(modelBuf);
        modelBuf.flip();
        glUniformMatrix3(shader.uniformLoc("u_model"), false, modelBuf);

        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());
        batch.drawString("Hello World!", 50.0, 50.0, 3);

        batch.end();
    }
}
