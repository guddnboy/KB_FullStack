package ch09.sec03.exam01;
public class A {
    //static 멤버클래스
    static class B {}
    //인스턴스필드값으로B 객체대입
    B field1 = new B();
    //정적필드값으로B 객체대입
    static B field2 = new B();
    //생성자
    A() {
        B b = new B();
    }
    //인스턴스메소드
    void method1() {
        B b = new B();
    }
    //정적메소드
    static void method2() {
        B b = new B();
    }
}