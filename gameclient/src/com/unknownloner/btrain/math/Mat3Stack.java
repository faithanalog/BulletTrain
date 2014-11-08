package com.unknownloner.btrain.math;

import java.util.LinkedList;

public class Mat3Stack {

    private LinkedList<Mat3> matrices = new LinkedList<>();

    public void push(Mat3 m) {
        Mat3 toPush = new Mat3();
        System.arraycopy(m.m, 0, toPush.m, 0, 9);
        matrices.push(toPush);
    }

    public Mat3 pop() {
        return matrices.pop();
    }

    public void pop(Mat3 dest) {
        Mat3 m = pop();
        System.arraycopy(m.m, 0, dest.m, 0, 9);
    }

}
