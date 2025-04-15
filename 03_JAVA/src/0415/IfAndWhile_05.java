import java.util.Scanner;

public class IfAndWhile_05 {
    public static void main(String[] args) {
        int total = 0;
        int deposit = 0;
        int withdraw = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
        System.out.println("--------------------");
        System.out.print("선택>");
            int select = scanner.nextInt();
        switch (select) {
            case 1:
                System.out.print("예금액>");
                deposit = scanner.nextInt();
                total += deposit;
            case 2:
                withdraw = scanner.nextInt();
                total -= withdraw;
            case 3:
                System.out.println(total);
            case 4, default:
                break;
        }
    }
}