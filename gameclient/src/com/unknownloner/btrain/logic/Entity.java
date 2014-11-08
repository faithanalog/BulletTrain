package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.math.AABB;
import com.unknownloner.btrain.math.Vec2;

public abstract class Entity {

    public final Vec2 pos = new Vec2();
    public final AABB bounds;
    public final Level level;

    public boolean isDead = false;

    Entity(Level level) {
        this.level = level;
        bounds = new AABB(pos, new Vec2(0, 0));
    }

    public void move(double dx, double dy) {
        pos.x += dx;
        pos.y += dy;
    }

    public void moveTo(double x, double y) {
        pos.x = x;
        pos.y = y;
    }

    public abstract void tick();

    public abstract EntityType type();

}
