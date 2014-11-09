package com.unknownloner.btrain.ui;

import com.unknownloner.btrain.gfx.SpriteBatch;

public class MenuText {
    public String string;
    public int scale;
    public float red;
    public float green;
    public float blue;
    public float alpha;
    public int x;
    public int y;

    public MenuText(String string, int scale, int x, int y, float red, float green, float blue, float alpha){
        this.string = string;
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public MenuText(String string, int scale, int x, int y){
        this(string, scale, x, y, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public MenuText(String string, int x, int y, float red, float green, float blue, float alpha){
        this(string, 1, x, y, red, green, blue, alpha);
    }

    public void draw(SpriteBatch batch){
        batch.setColor(red, green, blue, alpha);
        batch.drawString(string, x, y, scale);
    }


}
