package ch09.sec07.exam02;
public class Home {
    //필드에익명구현객체대입
    RemoteControl rc = new RemoteControl(){
        @Override
        public void turnOn() {
            System.out.println("TV를 켭니다.");
        }

        @Override
        public void turnOff() {
            System.out.println("TV를 끕니다.");
        }
    };
//메소드(필드이용)
    public void use1() {
        rc.turnOn();
        rc.turnOff();
    }
}