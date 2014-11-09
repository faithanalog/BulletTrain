package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.BulletTrain;
import com.unknownloner.btrain.core.GameStates;
import com.unknownloner.btrain.gfx.entity.BulletRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Level {

    public final String name;
    public final String background;

    public List<Entity> entities = new LinkedList<>();
    public EntityPlayer player;
    public EntityMusicShooter music;
    public EntityEnemy enemy;



    private List<Entity> spawnQueue = new ArrayList<>();

    public Level(String name, String background) {
        this.name = name;
        this.background = background;
        this.player = new EntityPlayer(this, new KeyInput());
        this.music = new EntityMusicShooter(this, "/maps/" + name);
        this.enemy = new EntityEnemy(this);
        spawnEntity(this.player);
    }

    public void spawnEntity(Entity e) {
        spawnQueue.add(e);
    }

    public void tick() {
        music.tick();

        entities.addAll(spawnQueue);
        spawnQueue.clear();

        //Tick entities
        for (Entity e : entities) {
            e.tick();
        }

        //Remove dead entities
        Iterator<Entity> iter = entities.iterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            if (e.isDead) {
                iter.remove();
                if(e instanceof EntityPlayer){
                    BulletTrain.currentGameState = GameStates.GAME_OVER;
                }
            }
        }
    }

}
