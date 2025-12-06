package com.dnikitin.oop.figures;

public abstract class Shape implements Internal {

    public abstract double area();

    public boolean isEqualArea(Shape shape) {
        return this.area() == shape.area();
    }
}
