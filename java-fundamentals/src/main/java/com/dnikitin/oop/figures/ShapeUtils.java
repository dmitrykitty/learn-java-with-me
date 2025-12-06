package com.dnikitin.oop.figures;

public final class ShapeUtils {
    private ShapeUtils() {
    }

    public static boolean isRectangle(Shape shape) {
        return shape instanceof Rectangle;
    }

    public static boolean isCircle(Shape shape) {
        return shape instanceof Circle;
    }
}
