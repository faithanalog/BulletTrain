package com.unknownloner.btrain.gfx.entity;

import com.unknownloner.btrain.gfx.SpriteBatch;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.EntityEnemy;

import java.io.IOException;

public class EnemyRenderer extends EntityRenderer<EntityEnemy> {

    private Texture playerTex;

    public EnemyRenderer() {
        try {
            playerTex = Texture.load("/textures/entities/enemy.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch batch, EntityEnemy entityEnemy, double x, double y) {
        batch.drawTexture(playerTex, x - 32, y - 27, 64, 96);
    }

}
