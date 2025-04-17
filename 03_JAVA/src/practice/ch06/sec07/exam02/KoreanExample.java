package ch06.sec07.exam02;

public class KoreanExample {
    public static void main(String[] args) {
        Korean k1 = new Korean( "박자바","011225-1234567");
        Korean k2 = new Korean( "김자바","930525-0654321");

        System.out.printf("k1.nation : %s\n", k1.nation);
        System.out.printf("k2.name : %s\n", k2.name);
        System.out.printf("k1.ssn : %s\n", k1.ssn);
        System.out.println();
        System.out.printf("k2.nation : %s\n", k2.nation);
        System.out.printf("k2.name : %s\n", k2.name);
        System.out.printf("k2.ssn : %s\n", k2.ssn);
    }
}
