package com.unknownloner.btrain.states;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.gl.Texture;
import org.lwjgl.opengl.Display;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3;

public class MainMenu extends GameState{

    Texture texture;

    public MainMenu() throws IOException{
        super(new Shader(Util.readText("/shaders/sprites.vert"), Util.readText("/shaders/font.frag"), "Main Menu Shader"));
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
        glUniform4f(shader.uniformLoc("u_color"), 1.0F, 1.0F, 1.0F, 1.0F);

        batch.drawTexture(texture, 0, 0, Display.getWidth(), Display.getHeight());
        batch.flush();

        int titleX = (Display.getWidth() - Util.stringWidth("Bullet Train", 10)) / 2;
        int titleY = Display.getHeight() * 3 / 4;
        glUniform4f(shader.uniformLoc("u_color"), 1.0F, 1.0F, 0.0F, 1.0F);
        batch.drawString("Bullet Train", titleX, titleY, 10);



        batch.end();
    }
}
