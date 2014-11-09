package com.unknownloner.btrain.core;

import com.unknownloner.btrain.states.InGame;
import com.unknownloner.btrain.states.MainMenu;

import java.io.IOException;

public class GameTick {

    static MainMenu mainMenu;
    static InGame inGame;

    public static void init(){
        try{
            mainMenu = new MainMenu();
            inGame = new InGame();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void tickMainMenu(){
        mainMenu.tick();
        mainMenu.draw();
    }

    public static void tickLevelSelect(){

    }

    public static void tickPaused(){

    }

    public static void tickGame(){
        inGame.tick();
        inGame.draw();
    }

}
