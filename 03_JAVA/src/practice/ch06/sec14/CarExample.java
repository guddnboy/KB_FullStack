package ch06.sec14;
public class CarExample {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.setSpeed(-50);
        System.out.println("현재 속도: " + myCar.getSpeed());

        myCar.setSpeed(100);
        System.out.println("현재 속도: " + myCar.getSpeed());

        if(!myCar.isStop()) {
            myCar.setStop(true);
        }

        System.out.println("현재 속도: " + myCar.getSpeed());
    }
}