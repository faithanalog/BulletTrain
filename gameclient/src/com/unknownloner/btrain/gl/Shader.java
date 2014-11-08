package com.unknownloner.btrain.gl;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public int glProg;

    public Shader(String vertSrc, String fragSrc, String name) {

        int vs = glCreateShader(GL_VERTEX_SHADER);
        int fs = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vs, name);
        glCompileShader(vs);
        String log = glGetShaderInfoLog(vs, 65536);
        if (!log.isEmpty()) {
            throw new RuntimeException("Shader load error in " + name + ":" + log);
        }

        glShaderSource(fs, name);
        glCompileShader(fs);
        log = glGetShaderInfoLog(fs, 65536);
        if (!log.isEmpty()) {
            throw new RuntimeException("Shader load error in " + name + ":" + log);
        }

        glProg = glCreateProgram();
        glAttachShader(glProg, vs);
        glAttachShader(glProg, fs);
        link();
    }

    public void link() {
        glLinkProgram(glProg);
    }

    public Shader(String vertSrc, String fragSrc) {
        this(vertSrc, fragSrc, "Anonymous Shader");
    }

    public void dispose() {
        glDeleteProgram(glProg);
    }

}
