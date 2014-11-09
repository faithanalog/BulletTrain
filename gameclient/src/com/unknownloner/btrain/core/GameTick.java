package com.unknownloner.btrain.core;

import com.unknownloner.btrain.states.MainMenu;

import java.io.IOException;

public class GameTick {

    static MainMenu mainMenu;

    public static void init(){
        try{
            mainMenu = new MainMenu();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void tickMainMenu(){
        mainMenu.draw();

    }

    public static void tickLevelSelect(){

    }

    public static void tickPaused(){

    }

    public static void tickGame(){

    }

}
