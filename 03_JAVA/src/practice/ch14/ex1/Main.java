package ch14.ex1;

import java.util.Scanner;

public class Main {
    public static int finalNumber;
    public static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("참가할 인원 수: ");
        finalNumber = sc.nextInt();  // 변수 선언 추가

        for(int i = 1; i <= finalNumber; i++){
            new Thread(new Player("플레이어" + i)).start();
        }
    }
}