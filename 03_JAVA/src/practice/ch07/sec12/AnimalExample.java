package ch07.sec12;

public class AnimalExample {
    public static void main(String[] args) {
        Animal[] animals = new Animal[3];
        animals[0] = new Cat();
        animals[1] = new Dog();
        animals[2] = new Cat();

        animals[0].sound();

        System.out.println("\n\n\n\n동적 바인딩 사용 0");

        for(Animal a : animals) {
            a.sound();
        }
    }
}