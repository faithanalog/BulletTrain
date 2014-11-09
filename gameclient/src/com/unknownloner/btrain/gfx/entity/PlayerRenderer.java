package com.unknownloner.btrain.gfx.entity;

import com.unknownloner.btrain.gfx.SpriteBatch;
import com.unknownloner.btrain.gl.Texture;
import com.unknownloner.btrain.logic.EntityPlayer;

import java.io.IOException;

public class PlayerRenderer extends EntityRenderer<EntityPlayer> {

    private Texture playerTex;

    public PlayerRenderer() {
        try {
            playerTex = Texture.load("/textures/entities/player.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch batch, EntityPlayer entityPlayer, double x, double y) {
        batch.drawTexture(playerTex, x - 18 - 14, y - 25 - 12);
    }

}
