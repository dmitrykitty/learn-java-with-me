package oop.figures;

public interface WithPerimeter {
    double getPerimeter();

    default double getSemiPerimeter(){
       return getPerimeter()/2;
    }
}
