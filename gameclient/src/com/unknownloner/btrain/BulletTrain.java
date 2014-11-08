package com.unknownloner.btrain;

import com.unknownloner.btrain.gl.Shader;
import com.unknownloner.btrain.math.Mat3;
import com.unknownloner.btrain.math.Vec2;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import java.io.File;
import java.util.Arrays;

public class BulletTrain {

    static boolean isFullscreen;

    public static void main(String[] args) throws Exception {
        for (String str : args) {
            if (str.startsWith("--fullscreen")) {
                isFullscreen = true;
            }
        }
        initDisplay();
//        Shader shader = new Shader(Util.readText("/shaders/sprites.vert"), Util.readText("/shaders/sprites.frag"), "Test");
        Thread.sleep(1000);
        Display.destroy();

//        Vec2 test = new Vec2(2, 0);
//        Mat3 rot = Mat3.rotation(Math.PI );
//        rot.multVec(test);
//
//        System.out.println(test);
    }

    public static void initDisplay() throws LWJGLException {
        System.setProperty("org.lwjgl.librarypath", nativesPath());
        if (isFullscreen) {
            Display.setFullscreen(true);
        } else {
            Display.setDisplayMode(new DisplayMode(854, 480));
        }
        Display.setTitle("Bullet Train");
        Display.create(new PixelFormat().withDepthBits(24));
    }

    public static String nativesPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String suffix = "";
        if (os.contains("win")) {
            suffix = "windows";
        } else if (os.contains("mac")) {
            suffix = "macosx";
        } else {
            suffix = "linux";
        }
        return System.getProperty("user.dir") + File.separator + "native" + File.separator + suffix;
    }

}
