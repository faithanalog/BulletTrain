package com.unknownloner.btrain;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import java.io.File;

public class BulletTrain {

    public static void main(String[] args) throws Exception {
        initDisplay();
        Thread.sleep(1000);
        Display.destroy();
    }

    public static void initDisplay() throws LWJGLException {
        System.setProperty("org.lwjgl.librarypath", nativesPath());
        Display.setDisplayMode(new DisplayMode(854, 480));
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
