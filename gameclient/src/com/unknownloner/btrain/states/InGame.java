package com.unknownloner.btrain.states;

import com.unknownloner.btrain.BulletTrain;
import com.unknownloner.btrain.core.GameStates;
import com.unknownloner.btrain.gfx.LevelRenderer;
import com.unknownloner.btrain.logic.Level;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class InGame extends GameState {

    public Level level;
    public LevelRenderer renderer;
    public static String levelName;

    public InGame() throws IOException {
        super();
        level = new Level(levelName, "/textures/space.png");
        renderer = new LevelRenderer(level);
    }

    public void init() {

    }

    public void tick() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            level.music.clip.stop();
            BulletTrain.currentGameState = GameStates.PAUSED;
            return;
        }
        level.tick();
        if(!level.music.clip.isActive()){
            BulletTrain.currentGameState = GameStates.MAIN_MENU;
        }
    }

    public void draw() {
        renderer.render();
    }
}
