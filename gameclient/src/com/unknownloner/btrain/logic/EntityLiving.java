package com.unknownloner.btrain.logic;

public abstract class EntityLiving extends Entity {

    public int health = 1000;

    public EntityLiving(Level level) {
        super(level);
    }

    @Override
    public void tick() {
        if (health < 0) {
            isDead = true;
        }
    }

}
