package oop.figures;

public class Circle extends Shape implements WithPerimeter{

    private double radius;
    private Point center;


    public Circle(double radius, Point p) {
        this.radius = radius;
        this.center = p;

    }


    @Override
    public boolean isInside(Point p) {
        return center.distance(p) <= radius;
    }


    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + ", center:" + center + '}';
    }


    public double getRadius() {
        return radius;
    }
}
