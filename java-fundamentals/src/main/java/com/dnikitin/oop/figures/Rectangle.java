package com.dnikitin.oop.figures;

public class Rectangle extends Shape {

    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    @Override
    public double area() {
        return (p1.distance(p2) * p1.distance(p4));
    }

    @Override
    public String toString(){
        return "Rectangle{" + "p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + '}';
    }

    @Override
    public boolean isInside(Point p) {
        return false;
    }
}
