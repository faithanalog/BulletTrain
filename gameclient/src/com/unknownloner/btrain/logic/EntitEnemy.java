package com.unknownloner.btrain.logic;

public class EntitEnemy extends EntityLiving {
    public EntitEnemy(Level level) {
        super(level);
    }

    @Override
    public EntityType type() {
        return EntityType.ENEMY;
    }
}
