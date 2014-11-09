package com.unknownloner.btrain.math;

public class Vec2 {

    public double x = 0, y = 0;

    public Vec2() {

    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void scale(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2 o) {
        set(o.x, o.y);
    }

    public void normalize() {
        scale(1 / length());
    }

    public double lengthSq() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSq());
    }

    public double distTo(Vec2 o) {
        double dx = o.x - x;
        double dy = o.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void add(Vec2 o) {
        x += o.x;
        y += o.y;
    }

    public void sub(Vec2 o) {
        x -= o.x;
        y -= o.y;
    }

    public void mult(Vec2 o) {
        x *= o.x;
        y *= o.y;
    }

    public void div(Vec2 o) {
        x /= o.x;
        y /= o.y;
    }

    public double dot(Vec2 o) {
        return x * o.x + y * o.y;
    }

    /**
     * Gets the angle of the direction of this vector
     * @return direction of vector
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Rotates a vector around the origin
     * @param theta How much to rotate, in radians
     */
    public void rotate(double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        double nx = cos * x - sin * y;
        double ny = sin * x + cos * y;
        x = nx;
        y = ny;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
