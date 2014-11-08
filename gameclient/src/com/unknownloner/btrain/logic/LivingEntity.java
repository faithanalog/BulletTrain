package com.unknownloner.btrain.logic;

public class LivingEntity extends Entity {

    public int health = 1000;

    @Override
    public void tick() {
        if (health < 0) {
            isDead = true;
        }
    }

}
