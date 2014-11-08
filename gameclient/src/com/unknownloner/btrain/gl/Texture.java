package com.unknownloner.btrain.gl;

import com.unknownloner.btrain.Util;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {

    public int glTex;

    public Texture() {
        glTex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, glTex);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    public Texture(BufferedImage img) {
        this();
        upload(img);
    }

    public void upload(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] d = new int[w * h];
        img.getRGB(0, 0, w, h, d, 0, w);

        IntBuffer buf = BufferUtils.createIntBuffer(w * h);
        buf.put(d);
        buf.flip();

        glBindTexture(GL_TEXTURE_2D, glTex);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, buf);

        if (Util.isPowerOf2(w) && Util.isPowerOf2(h)) {
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
            glGenerateMipmap(GL_TEXTURE_2D);
        } else {
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        }

    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, glTex);
    }

    public static Texture load(String path) throws IOException {
        return new Texture(ImageIO.read(Texture.class.getResourceAsStream(path)));
    }

}
