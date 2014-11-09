package com.unknownloner.btrain.osuparse;

public class BeatmapSpinner extends BeatmapObject {

    public int length;
    public BeatmapSpinner(int x, int y, long time, int comboType, int length) {
        super(x, y, time, comboType);
        this.length = length;
    }

}
