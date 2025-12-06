package com.dnikitin.reflectionAPI.practise.task1;

public class CarRunner {
    static void main() {
        Car car = new Car("BMW", "x5");

        System.out.println(DataBaseUtils.insertInto(car));
    }
}
