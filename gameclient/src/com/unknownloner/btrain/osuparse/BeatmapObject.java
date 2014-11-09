package com.unknownloner.btrain.osuparse;

public abstract class BeatmapObject {

    public int x;
    public int y;
    public long time;
    public int comboType;

    public BeatmapObject(int x, int y, long time, int comboType) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.comboType = comboType;
    }


    public static BeatmapObject parse(String line) {
        line = line.trim();
        if (line.isEmpty()) {
            return null;
        }
        String[] args = line.split(",");
        int x = (int)Double.parseDouble(args[0]);
        int y = (int)Double.parseDouble(args[1]);
        long time = Long.parseLong(args[2]) * 1000L; //Convert to microseconds
        int comboType = Integer.parseInt(args[3]);

        int length;

        if (args.length < 5)
            return null;

        switch (args.length) {
            case 5:
                return new BeatmapCircle(x, y, time, comboType);
            case 6:
                length = Integer.parseInt(args[5]);
                return new BeatmapSpinner(x, y, time, comboType, length);
            default:
                int repeats = Integer.parseInt(args[6]);
                length = (int)Double.parseDouble(args[7]);
                return new BeatmapSlider(x, y, time, comboType, repeats, length);
        }
    }

}
