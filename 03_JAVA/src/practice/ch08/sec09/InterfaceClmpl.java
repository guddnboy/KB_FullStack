package ch08.sec09;

public class InterfaceClmpl implements InterfaceC{
    @Override
    public void methodC() {
        System.out.println("InterfaceCImpl-methodC() 실행");
    }

    @Override
    public void methodA() {
        System.out.println("InterfaceCImpl-methodA() 실행");
    }

    @Override
    public void methodB() {
        System.out.println("InterfaceCImpl-methodB() 실행");
    }
}