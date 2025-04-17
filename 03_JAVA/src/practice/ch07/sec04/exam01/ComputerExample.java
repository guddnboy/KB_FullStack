package ch07.sec04.exam01;

public class ComputerExample {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Computer computer = new Computer();

        System.out.println(calc.areaCircle(3));
        System.out.println(computer.areaCircle(3));
    }
}
