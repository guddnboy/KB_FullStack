package ch07.sec04.exam01;

public class Computer extends Calculator{
    public Computer() {
    }
    @Override
    public double areaCircle(double radius){
        return Math.PI * radius * radius;
    }
}