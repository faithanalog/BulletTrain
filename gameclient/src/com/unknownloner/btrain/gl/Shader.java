package com.unknownloner.btrain.gl;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public int glProg;

    private Map<String, Integer> uniformMap = new HashMap<>();

    public Shader(String vertSrc, String fragSrc, String name) {
        int vs = glCreateShader(GL_VERTEX_SHADER);
        int fs = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vs, vertSrc);
        glCompileShader(vs);
        String log = glGetShaderInfoLog(vs, 65536);
        if (!log.isEmpty()) {
            throw new RuntimeException("Vert Shader load error in " + name + ":" + log);
        }

        glShaderSource(fs, fragSrc);
        glCompileShader(fs);
        log = glGetShaderInfoLog(fs, 65536);
        if (!log.isEmpty()) {
            throw new RuntimeException("Frag Shader load error in " + name + ":" + log);
        }

        glProg = glCreateProgram();
        glAttachShader(glProg, vs);
        glAttachShader(glProg, fs);
        link();
    }

    public void link() {
        glLinkProgram(glProg);
        uniformMap.clear();
    }

    public void use() {
        glUseProgram(glProg);
    }

    public Shader(String vertSrc, String fragSrc) {
        this(vertSrc, fragSrc, "Anonymous Shader");
    }

    public void dispose() {
        glDeleteProgram(glProg);
    }

    public int uniformLoc(String uniform) {
        if (uniformMap.containsKey(uniform)) {
            return uniformMap.get(uniform);
        } else {
            int loc = glGetUniformLocation(glProg, uniform);
            uniformMap.put(uniform, loc);
            return loc;
        }
    }

}
