package com.unknownloner.btrain.core;

import com.unknownloner.btrain.states.*;

import java.io.IOException;

public class GameTick {

    static MainMenu mainMenu;
    static LevelSelect levelSelect;
    static Paused paused;
    static InGame inGame;
    static GameOver gameOver;

    public static void init(){
        try{
            mainMenu = new MainMenu();
            levelSelect = new LevelSelect();
            paused = new Paused();
            inGame = new InGame();
            gameOver = new GameOver();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void tickMainMenu(){
        mainMenu.tick();
        mainMenu.draw();
    }

    public static void tickLevelSelect(){
        levelSelect.tick();
        levelSelect.draw();
    }

    public static void tickPaused(){
        paused.tick();
        paused.draw();
    }

    public static void tickGame(){
        inGame.tick();
        inGame.draw();
    }

    public static void tickGameOver(){
        gameOver.tick();
        gameOver.draw();
    }

}
