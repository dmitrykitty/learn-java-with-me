package oop.figures;

public class ShapeRunner {
    static void main() {
        Shape rec1 = new Rectangle(new Point(0, 0), new Point(0, 2), new Point(6, 2), new Point(6, 0));
        Shape circ1 = new Circle(2.56, new Point(0, 0));




        System.out.println(rec1);
        System.out.println(circ1);
        System.out.println(ShapeUtils.isRectangle(rec1));
        System.out.println(circ1.isEqualArea(rec1));
        System.out.println(circ1.isInside(new Point(1, 1)));
        System.out.println(circ1.area());
    }


}
