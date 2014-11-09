package com.unknownloner.btrain.logic;

public class EntityPlayerBullet extends EntityBullet {



    public EntityPlayerBullet(Level level, double x, double y, double speed, double rotation) {
        super(level, x, y, speed, rotation);
    }

    @Override
    public EntityType type(){
        return EntityType.PLAYER_BULLET;
    }
}
