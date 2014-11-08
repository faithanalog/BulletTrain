package com.unknownloner.btrain.math;

public class Mat3 {

    public double[] m = new double[9];

    /**
     * Creates a new identity matrix
     */
    public Mat3() {
        m[0] = 1;
        m[4] = 1;
        m[8] = 1;
    }

    public double get(int row, int col) {
        return m[col * 3 + row];
    }

    public void translate(double x, double y) {
        mult(translation(x, y));
    }

    public void scale(double sclx, double scly) {
        mult(makeScale(sclx, scly));
    }

    public void rotate(double theta) {
        mult(rotation(theta));
    }

    public void mult(Mat3 o) {
        double m00 = get(0, 0) * o.get(0, 0) + get(0, 1) * o.get(1, 0) + get(0, 2) * o.get(2, 0);
        double m10 = get(1, 0) * o.get(0, 0) + get(1, 1) * o.get(1, 0) + get(1, 2) * o.get(2, 0);
        double m20 = get(2, 0) * o.get(0, 0) + get(2, 1) * o.get(1, 0) + get(2, 2) * o.get(2, 0);

        double m01 = get(0, 0) * o.get(0, 1) + get(0, 1) * o.get(1, 1) + get(0, 2) * o.get(2, 1);
        double m11 = get(1, 0) * o.get(0, 1) + get(1, 1) * o.get(1, 1) + get(1, 2) * o.get(2, 1);
        double m21 = get(2, 0) * o.get(0, 1) + get(2, 1) * o.get(1, 1) + get(2, 2) * o.get(2, 1);

        double m02 = get(0, 0) * o.get(0, 2) + get(0, 1) * o.get(1, 2) + get(0, 2) * o.get(2, 2);
        double m12 = get(1, 0) * o.get(0, 2) + get(1, 1) * o.get(1, 2) + get(1, 2) * o.get(2, 2);
        double m22 = get(2, 0) * o.get(0, 2) + get(2, 1) * o.get(1, 2) + get(2, 2) * o.get(2, 2);

        m[0] = m00;
        m[1] = m10;
        m[2] = m20;
        m[3] = m01;
        m[4] = m11;
        m[5] = m21;
        m[6] = m02;
        m[7] = m12;
        m[8] = m22;
    }

    public void multVec(Vec2 v) {
        double nx = get(0, 0) * v.x + get(0, 1) * v.y + get(0, 2);
        double ny = get(1, 0) * v.x + get(1, 1) * v.y + get(1, 2);
        double nw = get(2, 0) * v.x + get(2, 1) * v.y + get(2, 2);
        v.x = nx / nw;
        v.y = ny / nw;
    }

    public static Mat3 translation(double x, double y) {
        Mat3 m = new Mat3();
        m.m[6] = x;
        m.m[7] = y;
        return m;
    }

    public static Mat3 rotation(double theta) {
        Mat3 m = new Mat3();
        double c = Math.cos(theta);
        double s = Math.sin(theta);

        m.m[0] = c;
        m.m[1] = s;
        m.m[3] = -s;
        m.m[4] = c;
        return m;
    }

    public static Mat3 makeScale(double sclx, double scly) {
        Mat3 m = new Mat3();
        m.m[0] = sclx;
        m.m[4] = scly;
        return m;
    }

}
