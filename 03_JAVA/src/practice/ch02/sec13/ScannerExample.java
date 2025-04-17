package ch02.sec13;
import java.util.Scanner;
public class ScannerExample {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("x 값 입력: ");
        String strX = scanner.nextLine();

        int x = Integer.parseInt(strX);
        System.out.print("y 값 입력: ");
        String strY = scanner.nextLine();

        int y = Integer.parseInt(strY);
        int result = x + y;

        System.out.println("x + y: " + result);
        System.out.println();
    }
}