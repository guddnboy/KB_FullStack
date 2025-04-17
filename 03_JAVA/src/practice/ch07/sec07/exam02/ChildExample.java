package ch07.sec07.exam02;
public class ChildExample {
    public static void main(String[] args) {
        Child child = new Child();
        Parent parent = child;
        parent.method1();
        parent.method2();
//        parent.method3(); 자식 클래스를 부모 클래스 타입에 할당 (업캐스팅)
        // 실제 객체는 Child지만 Parent 타입으로 참조하므로 Parent의 메소드만 접근 가능

    }
}