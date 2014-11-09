package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.math.Vec2;

public class EntityPlayer extends EntityLiving {

    public Input input;
    public EntityPlayer(Level level, Input input) {
        super(level);
        bounds.size.set(28.0, 24.0);
        this.input = input;
    }

    @Override
    public void tick() {
        double vx = 0.0;
        double vy = 0.0;

        if (input.left()) {
            vx -= 1.0;
        }
        if (input.right()) {
            vx += 1.0;
        }
        if (input.up()) {
            vy += 1.0;
        }
        if (input.down()) {
            vy -= 1.0;
        }

        if (vx != 0 || vy != 0) {
            Vec2 vel = new Vec2(vx, vy);
            vel.normalize();
            vel.scale(3.0);

            move(vel.x, vel.y);
        }
        super.tick();
    }

    @Override
    public EntityType type() {
        return EntityType.PLAYER;
    }

}
