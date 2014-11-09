package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.math.Vec2;
import org.lwjgl.opengl.Display;

public class EntityBullet extends Entity {

    public double speed;
    public double rotation;

    public EntityBullet(Level level, double x, double y, double speed, double rotation) {
        super(level);
        pos.x = x;
        pos.y = y;
        this.speed = speed;
        this.rotation = rotation;
        this.bounds.size.set(12, 12);
    }

    @Override
    public void tick() {
        Vec2 vel = new Vec2(speed, 0);
        vel.rotate(rotation);
        move(vel.x, vel.y);
        if (pos.distTo(level.player.pos) > Math.max(Display.getWidth(), Display.getHeight()) * 2) {
            isDead = true;
        }
    }

    @Override
    public EntityType type() {
        return EntityType.BULLET;
    }
}
