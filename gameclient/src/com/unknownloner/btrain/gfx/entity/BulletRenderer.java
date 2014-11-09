package com.unknownloner.btrain.gfx.entity;

import com.unknownloner.btrain.gfx.SpriteBatch;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.EntityBullet;

import java.io.IOException;

public class BulletRenderer extends EntityRenderer<EntityBullet> {

    Texture bullet;

    public BulletRenderer() {
        try {
            bullet = Texture.load("/textures/entities/bullet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch batch, EntityBullet entityBullet, double x, double y) {
        batch.drawTexture(bullet, x - 6, y - 6, 12, 12);
    }

}
