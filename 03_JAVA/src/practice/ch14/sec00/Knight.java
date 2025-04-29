package ch14.sec00;
public class Knight extends Thread {
    Bridge bridge; // 공유자원
    String name; // 기사 이름
    String address; // 기사 주소

    public Knight(Bridge bridge, String name, String address) {
        this.bridge = bridge;
        this.name = name;
        this.address = address;
    }
    @Override
    public void run() {
        System.out.println(name + " 기사가 도전한다.");
        while(true) {
            bridge.across(name, address);
        }
    }
}