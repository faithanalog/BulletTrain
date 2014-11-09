package com.unknownloner.btrain.states;

import com.unknownloner.btrain.gfx.LevelRenderer;
import com.unknownloner.btrain.logic.Level;

import java.io.IOException;

public class InGame extends GameState {

    public Level level;
    public LevelRenderer renderer;

    public InGame() throws IOException {
        super();
        level = new Level("Test Level", "/textures/space.png");
        renderer = new LevelRenderer(level);
    }

    public void init() {

    }

    public void tick() {
        level.tick();
    }

    public void draw() {
        renderer.render();
    }
}
