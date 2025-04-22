package ch09.sec07.exam01;
public class CarExample {
    public static void main(String[] args) {
//Car 객체생성
        Car car = new Car();
//익명자식객체가대입된필드사용
        car.run1();
//익명자식객체가대입된로컬변수사용
        car.run2();
//익명자식객체가대입된매개변수사용
        car.run3(new Tire() {
            @Override
            public void roll() {
                System.out.println("익명 자식 Tire 객체3이 굴러갑니다.");
            }
        });
    }
}