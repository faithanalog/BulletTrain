package com.unknownloner.btrain.osuparse;

public class BeatmapSlider extends BeatmapObject {

    public int repeats;
    public int length;

    public BeatmapSlider(int x, int y, long time, int comboType, int repeats, int length) {
        super(x, y, time, comboType);
        this.repeats = repeats;
        this.length = length;
    }

}
