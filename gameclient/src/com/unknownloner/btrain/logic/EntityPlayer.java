package com.unknownloner.btrain.logic;

public class EntityPlayer extends EntityLiving {

    public Input input;
    public EntityPlayer(Level level, Input input) {
        super(level);
        bounds.size.set(64.0, 64.0);
        this.input = input;
    }

    @Override
    public void tick() {
        double vx = 0.0;
        double vy = 0.0;

        if (input.left()) {
            vx -= 3.0;
        }
        if (input.right()) {
            vx += 3.0;
        }
        if (input.up()) {
            vy += 3.0;
        }
        if (input.down()) {
            vy -= 3.0;
        }
        move(vx, vy);
        super.tick();
    }

    @Override
    public EntityType type() {
        return EntityType.PLAYER;
    }

}
