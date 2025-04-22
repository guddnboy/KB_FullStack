package ch09.sec05.exam02;
public class A {
    //A 인스턴스필드
    String field = "A-field";
    //A 인스턴스메소드
    void method() {
        System.out.println("A-method");
    }
    //인스턴스멤버클래스
    class B {
        //B 인스턴스필드
        String field = "B-field";
        //B 인스턴스메소드
        void method() {
            System.out.println("B-method");
        }
        //B 인스턴스메소드
        void print() {
//B 객체의필드와메소드사용
            System.out.println(this.field);
            this.method();
//A 객체의필드와메소드사용
            System.out.println(A.this.field)
            ;
            A.this.method();
        }
    }
    //A의인스턴스메소드
    void useB() {
        B b = new B();
        b.print();
    }
}