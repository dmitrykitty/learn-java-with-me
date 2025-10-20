package oop.figures;

public class ShapeUtils {
    public static boolean isRectangle(Shape shape){
        return shape instanceof Rectangle;
    }
    public static boolean isCircle(Shape shape){
        return shape instanceof Circle;
    }
}
