package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.math.Vec2;
import javafx.scene.control.RadioMenuItem;

import java.util.Random;

public class EntityEnemy extends EntityLiving {

    public int shootCooldown = 7;

    private int shootDelay;

    private int xOffset = 10;
    private int xVel = -1;
    private int xLimit = -10;
    private Random rand = new Random();

    public EntityEnemy(Level level) {
        super(level);
    }

    @Override
    public EntityType type() {
        return EntityType.ENEMY;
    }

    @Override
    public void tick(){
        double vx = 0.0;
        double vy = 0.0;

        if(xVel == -1){
            vx = -1.0;
            xOffset--;
        }else if (xVel == 1){
            vx = 1.0;
            xOffset++;
        }
        if(xOffset == xLimit){
            xOffset = xLimit;
            xVel = -xVel;
            xLimit = rand.nextInt(50) * xVel;
        }
        if (vx != 0 || vy != 0) {
            Vec2 vel = new Vec2(vx, vy);
            vel.normalize();
            vel.scale(3.0);

            move(vel.x, vel.y);
        }
        move(0, 5.0);

        if (shootDelay > 0)
            shootDelay--;
        if (shootDelay == 0) {
            shootDelay = shootCooldown;
            fireBullet();
        }

        super.tick();
    }

    public void fireBullet() {
        EntityBullet left   = new EntityBullet(level, pos.x, pos.y + 8, 10.0, Math.toRadians(255));
        EntityBullet center = new EntityBullet(level, pos.x, pos.y + 8, 10.0,  Math.toRadians(270));
        EntityBullet right  = new EntityBullet(level, pos.x, pos.y + 8, 10.0,  Math.toRadians(285));
        level.spawnEntity(left);
        level.spawnEntity(center);
        level.spawnEntity(right);
    }
}
