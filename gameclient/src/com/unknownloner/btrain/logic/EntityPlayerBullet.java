package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.math.Vec2;
import org.lwjgl.opengl.Display;

public class EntityPlayerBullet extends EntityBullet {



    public EntityPlayerBullet(Level level, double x, double y, double speed, double rotation) {
        super(level, x, y, speed, rotation);
    }

    @Override
    public EntityType type(){
        return EntityType.PLAYER_BULLET;
    }

    @Override
    public void tick(){
        Vec2 vel = new Vec2(speed, 0);
        vel.rotate(rotation);
        move(vel.x, vel.y);
        if (pos.distTo(level.player.pos) > Math.max(Display.getWidth(), Display.getHeight()) * 2) {
            isDead = true;
        }
    }
}
