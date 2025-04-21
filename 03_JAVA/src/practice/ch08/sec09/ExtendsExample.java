package ch08.sec09;

public class ExtendsExample {
    public static void main(String[] args) {
        InterfaceClmpl impl = new InterfaceClmpl();

        InterfaceA ia = impl;
        ia.methodA();
        System.out.println();

        InterfaceB ib = impl;
        ib.methodB();
        System.out.println();

        InterfaceC ic = impl;
        ic.methodA();
        ic.methodB();
        ic.methodC();
    }
}
