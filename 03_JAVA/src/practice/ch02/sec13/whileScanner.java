package ch02.sec13;

import java.util.Scanner;

public class whileScanner {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("입력 문자열:");
            String word = scanner.nextLine();

            if (word.equals("q")){
                System.out.println("종료");
                break;
            }

            System.out.print("출력 문자열:");
            System.out.println(word);
        }
    }
}
