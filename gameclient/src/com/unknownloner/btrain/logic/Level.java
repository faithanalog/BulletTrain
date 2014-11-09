package com.unknownloner.btrain.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Level {

    public final String name;
    public final String background;

    public List<Entity> entities = new LinkedList<>();
    public EntityPlayer player;

    public Level(String name, String background) {
        this.name = name;
        this.background = background;
        this.player = new EntityPlayer(this, new KeyInput());
        spawnEntity(this.player);
    }

    public void spawnEntity(Entity e) {
        entities.add(e);
    }

    public void tick() {
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
            }
        }
    }

}
