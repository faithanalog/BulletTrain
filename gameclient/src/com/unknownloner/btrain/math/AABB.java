package com.unknownloner.btrain.math;

/**
 * Axis aligned bounding box
 */
public class AABB {


    public Vec2 pos;
    public Vec2 size;

    /**
     * Creates an AABB linked to the provided position and size vectors.
     * Any modifications to the provided vectors will be reflected in the AABB
     * @param pos
     * @param size
     */
    public AABB(Vec2 pos, Vec2 size) {
        this.pos = pos;
        this.size = size;
    }


    public AABB(double width, double height) {
        this(new Vec2(0.0, 0.0), new Vec2(width, height));
    }

    public double minX() {
        return pos.x;
    }

    public double minY() {
        return pos.y;
    }

    public double maxX() {
        return pos.x + size.x;
    }

    public double maxY() {
        return pos.y + size.y;
    }

    /**
     * Checks if this AABB intserects another AABB
     * @param o Other AABB to check intersection with
     * @return
     */
    public boolean intersects(AABB o) {
        return maxX() > o.minX()
                && maxY() > o.minY()
                && o.maxX() > minX()
                && o.maxY() > minY();
    }

}
