package oop.figures;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
