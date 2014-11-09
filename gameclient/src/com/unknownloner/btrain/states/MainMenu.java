package com.unknownloner.btrain.states;

import com.unknownloner.btrain.gl.Texture;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class MainMenu extends GameState{

    Texture texture;

    public MainMenu() throws IOException{
        super();
        texture = Texture.load("/textures/space.jpg");
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

        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());

        glUniform2f(shader.uniformLoc("u_screen_size"), Display.getWidth(), Display.getHeight());
        batch.drawString("Hello World!", 50.0, 50.0, 10);

        batch.end();
    }
}
